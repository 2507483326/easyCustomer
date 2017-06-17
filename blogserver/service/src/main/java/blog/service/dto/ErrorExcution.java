package blog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Epat on 2017/3/13.
 */

@Data
@AllArgsConstructor
public class ErrorExcution {

    private Integer code;

    private String message;

    public ErrorExcution(Integer code) {
        this.code = code;
    }
}
