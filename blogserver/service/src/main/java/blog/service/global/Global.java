package blog.service.global;

/**
 * Created by Epat on 2017/3/9.
 */
public class Global {

    // 用户盐
    public static final Integer SALT_LENGTH = 20;

    // 发送邮件验证码存储时间
    public static final int EMAIL_REPEAT_TIME = 1;

    // 发送邮件验证码信息存储时间
    public static final int EMAIL_CODE_TIME = 10;

    // 发送邮件别名
    public static final String EMAIL_REPEAT_NAME = "code";

    public static final String EMAIL_CODE_NAME = "user";


}
