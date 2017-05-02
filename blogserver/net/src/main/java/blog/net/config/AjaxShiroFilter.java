package blog.net.config;

import blog.service.global.Global;
import blog.service.global.GlobalError;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Epat on 2017/2/11.
 */

public class AjaxShiroFilter extends FormAuthenticationFilter{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected boolean onAccessDenied (ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request,response);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        logger.info("=========================subject:"+subject+"=============================");
        // 判断用户是否登录 直接返回错误状态码
        if(subject.getPrincipal() == null) {
            httpServletResponse.setStatus(GlobalError.USER_NOLOGIN);
        }else {
            httpServletResponse.setStatus(GlobalError.USER_NOAUTHORITY);
        }
        return false;
    }
}
