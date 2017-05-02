package blog.net.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by Epat on 2017/3/9.
 */
public class ShiroToken extends UsernamePasswordToken{

    public final static int typeLogin = 0;

    public final static int typeRemenber = 1;

    private int type;

    public ShiroToken(String userName, String passWord){
        super(userName, passWord);
        this.type = typeLogin;
    }

    public ShiroToken(String userName, String passWord,int type){
        super(userName, passWord);
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
