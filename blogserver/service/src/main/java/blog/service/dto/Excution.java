package blog.service.dto;

/**
 * Created by Epat on 2017/2/11.
 */
public class Excution <T>{

    private boolean success;

    private T data;

    public Excution(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
