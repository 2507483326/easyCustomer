package blog.net.Tools;

import java.util.Random;

/**
 * Created by Epat on 2017/3/11.
 */
public class EpatString {

    // 获取随机字符方法
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
