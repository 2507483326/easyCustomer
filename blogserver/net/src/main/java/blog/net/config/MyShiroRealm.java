package blog.net.config;

import blog.service.model.User;
import blog.service.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import qing.tool.StringTool;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Epat on 2017/2/11.
 */
public class MyShiroRealm extends AuthorizingRealm{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /*权限验证*/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> auth = new HashSet<String>();
        auth.add("user");
        authorizationInfo.setRoles(auth);
        return authorizationInfo;
    }

    /*登录验证*/
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("进入登录");
        ShiroToken token = (ShiroToken) authcToken;
        User userSession = null;
        SimpleAuthenticationInfo authInfo = null;
        if (token == null || StringTool.IsTrimEmpty(token.getUsername())){
            throw new UnknownAccountException("String is null");
        }
        switch (token.getType()){
            case ShiroToken.typeLogin:
                String userName = token.getUsername();
                userSession = userService.Login(userName);
                if (userSession == null) {
                    throw new UnknownAccountException(userName + ":::: login failed");
                }
                ByteSource salt = ByteSource.Util.bytes(userService.getSalt(userName));
                logger.info("userName:::"+userName);
                logger.info(userSession.toString());
                authInfo = new SimpleAuthenticationInfo(userSession, userSession.getPassword(), salt,userSession.getNickName());
                break;
            case ShiroToken.typeRemenber:
                break;
        }
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("user",userSession);
        return authInfo;
    }

    // 缓存处理

    /**
     * 重写获取cachekey方法,key值为"blog" + sessionId
     */
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals){
        return "blog" + SecurityUtils.getSubject().getSession().getId();
    }

    // 权限缓存
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principal) {
        logger.info("清空权限");
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    public static void main(String[] args){
        int hashIterations = 9841;//加密的次数
        Object salt = "123456";//盐值
        Object credentials = "123456";//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
                salt, hashIterations);
        System.out.println("加密后的值----->" + simpleHash);
    }
}
