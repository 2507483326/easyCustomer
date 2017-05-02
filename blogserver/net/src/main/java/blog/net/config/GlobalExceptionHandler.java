package blog.net.config;

import blog.service.dto.ErrorExcution;
import blog.service.dto.Excution;
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

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Excution exceptionHandler(RuntimeException e, HttpServletResponse response) {
        ErrorExcution errorExcution = new ErrorExcution(GlobalError.ERROR_CODE,e.getMessage());
        logger.info("错误："+e.toString());
        Excution excution = new Excution(false, errorExcution);
        return excution;
    }
}
