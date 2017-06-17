package blog.net.Tools;

import blog.service.dto.ErrorExcution;
import blog.service.dto.Excution;

/**
 * 返回工具类
 * Created by Epat on 2017/6/13.
 */
public class EpatResultUtil {

    public static final Integer DEFAULT_ERROR_CODE = 700;

    public static Excution success (Object object) {
        Excution excution = new Excution(true, object);
        return excution;
    }

    public static Excution success () {
        Excution excution = new Excution(true, null);
        return excution;
    }

    public static Excution error (Integer code, String msg) {
        ErrorExcution errorExcution = new ErrorExcution(code, msg);
        Excution excution = new Excution(false, errorExcution);
        return excution;
    }

    public static Excution error (String msg) {
        ErrorExcution errorExcution = new ErrorExcution(DEFAULT_ERROR_CODE, msg);
        Excution excution = new Excution(false, errorExcution);
        return excution;
    }
}
