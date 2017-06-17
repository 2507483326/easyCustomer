package blog.service.enums;

/**
 * Created by Epat on 2017/6/13.
 */

public enum ResultEnum {
    UNKNOW_ERROR(600, "未知错误"),
    USER_NOLOGIN(601, "用户未登录"),
    USER_NOAUTHORITY(602, "用户未授权"),
    USER_PASSWORDERROR(603, "密码错误"),
    USER_NOACCOUNT(604, "账号不存在"),
    USER_LOCKACCOUNT(605, "账号被锁定"),
    USER_ABNORMALACOUNT(606, "账号异常登录"),
    USER_OVERDUEACOUNT(607, "账号session过期"),
    EMAIL_ERROR(608, "邮件发送未知异常"),
    EMAIL_TIMEERROR(609, "邮件发送过于频繁"),
    EMAIL_HASEMAIL(610, "邮件已注册"),
    VALIDATECODE_MISMATCH(611, "验证码不匹配");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
