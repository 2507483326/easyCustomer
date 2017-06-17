package blog.service.exception;

import blog.service.enums.ResultEnum;
import lombok.Data;

/**
 * Created by Epat on 2017/6/13.
 */
@Data
public class GlobalException extends RuntimeException{

    private Integer code;

    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
