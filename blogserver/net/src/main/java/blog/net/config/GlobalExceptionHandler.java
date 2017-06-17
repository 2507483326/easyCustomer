package blog.net.config;

import blog.net.Tools.EpatResultUtil;
import blog.service.dto.Excution;
import blog.service.exception.GlobalException;
import blog.service.global.GlobalError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获
 * Created by Epat on 2017/3/13.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Excution exceptionHandler(Exception e, HttpServletResponse response) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return EpatResultUtil.error(globalException.getCode(), globalException.getMessage());
        }
        return EpatResultUtil.error(GlobalError.ERROR_CODE, e.getMessage());
    }
}
