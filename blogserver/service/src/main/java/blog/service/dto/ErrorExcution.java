package blog.service.dto;

/**
 * Created by Epat on 2017/3/13.
 */
public class ErrorExcution {

    private Integer code;

    private String message;

    public ErrorExcution() {}

    public ErrorExcution(Integer code) {
        this.code = code;
    }

    public ErrorExcution(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
