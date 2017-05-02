package blog.service.global;

/**
 * Created by Epat on 2017/3/13.
 */
public class GlobalError {

    // 统一错误码
    public static final Integer ERROR_CODE = 600;

    // 用户未登录状态码
    public static final Integer USER_NOLOGIN = 601;

    // 用户未授权状态码
    public static final Integer USER_NOAUTHORITY = 602;

    // 密码错误
    public static final Integer USER_PASSWORDERROR = 603;

    // 账号不存在
    public static final Integer USER_NOACCOUNT = 604;

    // 账号被锁定
    public static final Integer USER_LOCKACCOUNT = 605;

    // 账号异常登录
    public static final Integer USER_ABNORMALACOUNT = 606;

    // 账号session过期
    public static final Integer USER_OVERDUEACOUNT = 607;

    // 邮件发送未知异常
    public static final Integer EMAIL_ERROR = 608;

    // 邮件发送1分钟时间未到，疑似破解
    public static final Integer EMAIL_TIMEERROR = 609;

    // 邮件已注册
    public static final Integer EMAIL_HASEMAIL = 610;
}
