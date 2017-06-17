package blog.net.controll;

import blog.net.Tools.EpatEmail;
import blog.net.Tools.EpatResultUtil;
import blog.net.Tools.EpatUtils;
import blog.net.config.MyJdbc;
import blog.service.dto.Excution;
import blog.service.enums.ResultEnum;
import blog.service.exception.GlobalException;
import blog.service.model.User;
import blog.service.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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

    @Resource
    RedisTemplate redisTemplate;

    @Value("${shiroMd5Count}")
    int hashIterations;

    // 得到盐
    @GetMapping(value = "/salt")
    public Excution getSalt(String loginName){
        String salt = userService.getSalt(loginName);
        logger.info("========================参数"+salt+"========================");
        return new Excution(true, salt);
    }

    // login 登录
    @PostMapping(value = "/login")
    public Excution login(User  user) throws Exception {
        return userService.Login(user);
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
        return userService.rememberLogin();
    }



    // 判断邮箱是否存在
    @PostMapping(value = "/isExistEmail")
    public Excution isExistEmail (String data) {
        return EpatResultUtil.success(userService.isExistEmail(data));
    }

    // 发送验证码
    @PostMapping(value = "/getValidateCode")
    public Excution getValidateCode(String email, HttpServletRequest request) {
        return userService.getValidateCode(email);
    }

    @PostMapping(value = "/register")
    public Excution register(@Valid User user, BindingResult bindingResult, String validateCode, HttpServletRequest request){
        // 判断是否校验通过
        if (bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                logger.info(error.getCode() + error.getDefaultMessage());
            }
            throw new GlobalException(ResultEnum.VALIDATECODE_MISMATCH);
        }
        return userService.register(user, validateCode, epatUtils.getIpAddress(request),hashIterations);
    }

    @GetMapping(value = "/getAll")
    public Excution getAll(){
        return new Excution(true,userService.all());
    }
}
