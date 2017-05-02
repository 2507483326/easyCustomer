package blog.net.Tools;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Epat on 2017/3/13.
 */
@Service
public class EpatUtils {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    //获取IP方法
    public String getIpAddress(HttpServletRequest request) {
        try {
            String ip = request.getHeader("X-Real-IP");
            if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
            ip = request.getHeader("X-Forwarded-For");
            if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个IP值，第一个为真实IP。
                int index = ip.indexOf(',');
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            } else {
                return request.getRemoteAddr();
            }
        } catch (Exception e) {
            logger.error("============获取用户ip失败");
        }
        return "=====无效IP=====";
    }
}
