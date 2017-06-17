package blog.net.serviceImpl;

import blog.db.dao.UserDao;
import blog.net.Tools.EpatEmail;
import blog.net.Tools.EpatResultUtil;
import blog.net.Tools.EpatString;
import blog.net.Tools.EpatUtils;
import blog.net.config.ShiroToken;
import blog.service.dto.Excution;
import blog.service.dto.UserExcution;
import blog.service.enums.ResultEnum;
import blog.service.exception.GlobalException;
import blog.service.global.Global;
import blog.service.model.User;
import blog.service.service.RuleService;
import blog.service.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import qing.tool.StringTool;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Epat on 2017/2/10.
 */
@Service
public class UserServiceImpl implements UserService{

    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    UserDao userDao;

    @Autowired
    RuleService ruleService;

    @Resource
    RedisTemplate redisTemplate;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    EpatEmail epatEmail;

    @Autowired
    EpatUtils epatUtils;


    @Qualifier("freeMarkerConfiguration")
    @Autowired
    Configuration configuration;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<User> all() {
        return userDao.all();
    }

    public String getSalt(String loginName) {
        return userDao.getSalt(loginName);
    }

    public void setUid(String loginName, String uuid) {
        userDao.setUid(loginName,uuid);
    }

    public User getUserByUid(String uuid) {
        return userDao.getUserByUid(uuid);
    }

    public Excution Login(User  user) {
        Subject currentUser = SecurityUtils.getSubject();
        logger.info("对象为"+user.toString());
        if (StringTool.IsTrimEmpty(user.getLoginName())){
            throw new GlobalException(ResultEnum.USER_NOACCOUNT);
        }
        if (StringTool.IsTrimEmpty(user.getPassword())){
            throw new GlobalException(ResultEnum.USER_PASSWORDERROR);
        }
        try {
            logger.info("是否登录"+currentUser.isAuthenticated());
            if (!currentUser.isAuthenticated()) {
                logger.info("没有登陆");
                ShiroToken token = new ShiroToken(user.getLoginName(),user.getPassword());
                token.setRememberMe(true);
                currentUser.login(token);
            }
        }catch (IncorrectCredentialsException e){
            throw new GlobalException(ResultEnum.USER_PASSWORDERROR);
        }catch (UnknownAccountException e){
            throw new GlobalException(ResultEnum.USER_NOACCOUNT);
        }catch (LockedAccountException e){
            throw new GlobalException(ResultEnum.USER_LOCKACCOUNT);
        }catch (ExcessiveAttemptsException e){
            throw new GlobalException(ResultEnum.USER_ABNORMALACOUNT);
        }catch (Exception e){
            throw new GlobalException(ResultEnum.UNKNOW_ERROR);
        }
        User sessionUser = (User)currentUser.getSession().getAttribute("user");
        logger.info("==========::::::::::"+sessionUser.toString());
        if (sessionUser == null) {
            throw new GlobalException(ResultEnum.USER_OVERDUEACOUNT);
        } else {
            UserExcution userExcution = this.getUser(sessionUser,false);
            return EpatResultUtil.success(userExcution);
        }
    }

    public Excution register(User user, String validateCode, String ipAddress, Integer hashIterations) {
        String oldPassword = user.getPassword();
        //进行判空操作
        //验证码判断
        String randomCode = redisTemplate.opsForValue().get(user.getEmail() + Global.EMAIL_CODE_NAME).toString();
        logger.info("================" + randomCode.toUpperCase());
        logger.info("===============" + validateCode.toUpperCase());
        if (randomCode == null) {
            return  EpatResultUtil.error("验证码过期");
        }
        if (!validateCode.toUpperCase().equals(randomCode.toUpperCase())) {
            return  EpatResultUtil.error("验证码不匹配");
        }
        try {
            user.setLoginName(user.getEmail());
            user.setRegIp(ipAddress);
            user.setRegTime(new Date());
            // 加盐 密码加密
            Object salt = EpatString.getRandomString(Global.SALT_LENGTH);//盐值
            Object credentials = user.getPassword();//密码
            String hashAlgorithmName = "MD5";//加密方式
            Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
                    salt, hashIterations);
            user.setPassword(simpleHash.toString());
            user.setSalt(salt.toString());
            userDao.insert(user);
        }catch (Exception e) {
            logger.error("注册失败， 数据库插入错误 :::" + e);
            return EpatResultUtil.error("注册失败，数据库插入错误");
        }
        logger.info("=========" + user);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            ShiroToken token = new ShiroToken(user.getLoginName(), oldPassword);
            token.setRememberMe(true);
            currentUser.login(token);
        } catch (Exception e) {
            return EpatResultUtil.error("注册后登录异常");
        }
        User sessionUser = (User)currentUser.getSession().getAttribute("user");
        return EpatResultUtil.success(sessionUser);
    }

    @Override
    public User getUserByEmail(String email) {return userDao.selectByEmail(email);}

    @Override
    public UserExcution getUser(User user, Boolean isRemember) {
        UserExcution userExcution = new UserExcution(user.getId(),user.getState(),user.getAvatar(),user.getLoginName(),user.getNickName(),user.getRegCity(),user.getRegIp(),user.getRegProvince(),user.getRegTime(),isRemember);
        return userExcution;
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return userDao.selectByLogin(loginName);
    }

    @Override
    public Excution getValidateCode(String email) {
        User user = null;
        // 存储email 1分钟后失效
        user = this.getUserByEmail(email);
        if (user != null) {
            throw new GlobalException(ResultEnum.EMAIL_HASEMAIL);
        }
        logger.info("================" + email);
        Object result = redisTemplate.opsForValue().get(email + Global.EMAIL_REPEAT_NAME);
        if (result == null || StringTool.IsTrimEmpty(result.toString())) {
            String randomCode = EpatString.getRandomString(4);
            // redis 操作
            try{
                redisTemplate.opsForValue().set(email + Global.EMAIL_REPEAT_NAME, email, Global.EMAIL_REPEAT_TIME, TimeUnit.MINUTES);
                redisTemplate.opsForValue().set(email + Global.EMAIL_CODE_NAME, randomCode, Global.EMAIL_CODE_TIME, TimeUnit.MINUTES);
            } catch (Exception e) {
                throw new GlobalException(ResultEnum.EMAIL_ERROR);
            }
            try {
                logger.info("========验证码为::::" + randomCode);
                // 邮件发送交由线程池操作
                // 加载模板
                Map<String, Object> model = new HashMap<>();
                model.put("email", email);
                model.put("emailCode", randomCode);
                Template t = configuration.getTemplate("email.ftl");
                String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
                // 异步发送邮件
                threadPoolTaskExecutor.execute(() -> {
                    try {
                        epatEmail.sendHtmlMail(email, "易客知道邮件验证码", content);
                    } catch (Exception e) {
                        logger.error("发送邮件失败", e);
                    }
                });
            }  catch (Exception e) {
                throw new GlobalException(ResultEnum.EMAIL_ERROR);
            }
        } else {
            logger.error("邮件超时");
            throw new GlobalException(ResultEnum.EMAIL_TIMEERROR);
        }
        return EpatResultUtil.success("发送验证码成功");
    }

    @Override
    public Boolean isExistEmail(String data) {
        // 判断是否校验通过
        String regEx = "/^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$/";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(data);
        if (data == null || data.isEmpty() || matcher.matches()) {
            return false;
        }
        User user = this.getUserByEmail(data);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Excution rememberLogin() {
        Subject subject = SecurityUtils.getSubject();
        User sessionUser = (User)subject.getSession().getAttribute("user");
        // 如果session存在 直接返回user
        if (sessionUser != null) {
            UserExcution userExcution = this.getUser(sessionUser,false);
            return EpatResultUtil.success(userExcution);
        }
        // 获取用户名称
        logger.info(subject.isRemembered()+"-------------------");
        try {
            // 判断用户是否被记住
            if (subject.isRemembered()) {
                String userName  = subject.getPrincipal().toString();
                User user = this.getUserByLoginName(userName);
                if (user == null) {
                    throw new GlobalException(ResultEnum.USER_NOACCOUNT);
                }
                // 存入session
                Session session = subject.getSession();
                session.setAttribute("user",user);
                return EpatResultUtil.success(this.getUser(user, true));
            }else {
                throw new GlobalException(ResultEnum.USER_NOLOGIN);
            }
        }catch (Exception e) {
            logger.error("=======================cookie登录异常======================");
            logger.error(e.getMessage());
            throw new GlobalException(ResultEnum.UNKNOW_ERROR);
        }
    }
}
