package blog.net.controll;

import blog.net.Tools.EpatEmail;
import blog.net.Tools.EpatString;
import blog.net.Tools.EpatUtils;
import blog.net.config.MyJdbc;
import blog.net.config.ShiroToken;
import blog.service.dto.ErrorExcution;
import blog.service.dto.Excution;
import blog.service.dto.UserExcution;
import blog.service.global.GlobalError;
import blog.service.model.User;
import blog.service.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qing.tool.StringTool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Epat on 2017/2/16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserControll {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    MyJdbc myJdbc;

    @Autowired
    EpatEmail epatEmail;

    @Autowired
    EpatUtils epatUtils;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Qualifier("freeMarkerConfiguration")
    @Autowired
    Configuration configuration;

    @Resource
    RedisTemplate redisTemplate;


    @GetMapping(value = "/jdbc")
    public Excution getJdbc(){
        return new Excution(true,myJdbc);
    }
    // 得到盐
    @GetMapping(value = "/salt")
    public Excution getSalt(String loginName){
        logger.info("========================参数"+loginName+"========================");
        String salt = userService.getSalt(loginName);
        logger.info("========================参数"+salt+"========================");
        return new Excution(true, salt);
    }

    // login 登录
    @PostMapping(value = "/login")
    public Excution login(User  user) throws AuthenticationException {
        Subject currentUser = SecurityUtils.getSubject();
        ErrorExcution excution = new ErrorExcution();
        logger.info("对象为"+user.toString());
        if (StringTool.IsTrimEmpty(user.getLoginName())){
            excution.setCode(GlobalError.USER_NOACCOUNT);
            excution.setMessage("账号不存在");
            return new Excution(false,excution);
        }
        if (StringTool.IsTrimEmpty(user.getPassword())){
            excution.setCode(GlobalError.USER_PASSWORDERROR);
            excution.setMessage("密码错误");
            return new Excution(false,excution);
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
            excution.setCode(GlobalError.USER_PASSWORDERROR);
            excution.setMessage("密码错误");
            return new Excution(false,excution);
        }catch (UnknownAccountException e){
            excution.setCode(GlobalError.USER_NOACCOUNT);
            excution.setMessage("账号不存在");
            return new Excution(false,excution);
        }catch (LockedAccountException e){
            excution.setCode(GlobalError.USER_LOCKACCOUNT);
            excution.setMessage("账号被锁定");
            return new Excution(false,excution);
        }catch (ExcessiveAttemptsException e){
            excution.setCode(GlobalError.USER_ABNORMALACOUNT);
            excution.setMessage("账号异常登录");
            return new Excution(false,excution);
        }catch (Exception e){
            throw new RuntimeException("用户登录时未知错误");
        }
        User sessionUser = (User)currentUser.getSession().getAttribute("user");
        logger.info("==========::::::::::"+sessionUser.toString());
        if (sessionUser == null) {
            excution.setCode(GlobalError.USER_OVERDUEACOUNT);
            excution.setMessage("账号过期");
            return new Excution(false,excution);
        } else {
            UserExcution userExcution = userService.getUser(sessionUser,false);
            return new Excution(true,userExcution);
        }
    }

    // 退出登录
    @PostMapping("/loginOut")
    public void loginOut() {
        logger.info("===================退出登录=======================");
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
            currentUser.logout();
        }
    }

    // 记住我获取登录信息
    @PostMapping("/rememberLogin")
    public Excution rememberLogin(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User sessionUser = (User)subject.getSession().getAttribute("user");
        // 如果session存在
        if (sessionUser != null) {
            UserExcution userExcution = userService.getUser(sessionUser,false);
            return  new Excution(true,userExcution);
        }
        ErrorExcution excution = new ErrorExcution();
        // 获取用户名称
        logger.info(subject.isRemembered()+"-------------------");
        try {
            if (subject.isRemembered()) {
                String userName  = subject.getPrincipal().toString();
                User user = userService.Login(userName);
                if (user == null) {
                    excution.setCode(GlobalError.USER_NOACCOUNT);
                    excution.setMessage("账号不存在");
                    logger.error("======================IP:  "+ epatUtils.getIpAddress(request)+"  进行cookie注入==============");
                    return new Excution(false, excution);
                }
                // 存入session
                Session session = subject.getSession();
                session.setAttribute("user",user);
                UserExcution userExcution = userService.getUser(user,true);
                return new Excution(true, userExcution);
            }else {
                excution.setCode(GlobalError.USER_NOLOGIN);
                excution.setMessage("账号未登录");
                return new Excution(false,excution);
            }
        }catch (Exception e) {
            logger.error("=======================cookie登录异常======================");
            logger.error(e.getMessage());
            excution.setCode(GlobalError.ERROR_CODE);
            excution.setMessage(e.getMessage());
            return new Excution(false,excution);
        }
    }

    // 发送验证码
    @PostMapping(value = "/getValidateCode")
    public Excution getValidateCode(String email, HttpServletRequest request) {
        User user = null;
        // 存储email 1分钟后失效
        ErrorExcution excution = new ErrorExcution();
        user = userService.getUserByEmail(email);
        if (user != null) {
            logger.error("================此邮箱已注册================");
            excution.setCode(GlobalError.EMAIL_HASEMAIL);
            excution.setMessage("该邮箱已注册");
            return new Excution(false,excution);
        }
        logger.info("================" + email);
        String result = (String)redisTemplate.opsForValue().get(email);
        if (StringTool.IsTrimEmpty(result)) {
            try {
                String randomCode = EpatString.getRandomString(5);
                redisTemplate.opsForValue().set(email,email,1, TimeUnit.MINUTES);
                redisTemplate.opsForValue().set(email + "code", randomCode, 10, TimeUnit.MINUTES);
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
                logger.error("发送邮件未知异常", e);
                excution.setCode(GlobalError.EMAIL_ERROR);
                excution.setMessage("邮件发送未知错误");
                return new Excution(false,excution);
            }
        } else {
            logger.error("=============== IP :::" + epatUtils.getIpAddress(request) + " 短时间内重复发送邮件");
            excution.setCode(GlobalError.EMAIL_TIMEERROR);
            excution.setMessage("邮件发送过于频繁，请稍后");
            return new Excution(false,excution);
        }
        return new Excution(true,"发送验证码成功");
    }

    @PostMapping(value = "/register")
    public Excution register(User user, String validateCode){
        //进行判空操作
        //验证码判断
        userService.register(user);
        return new Excution(true,"注册成功");
    }

    @GetMapping(value = "/getAll")
    public Excution getAll(){
        return new Excution(true,userService.all());
    }
}
