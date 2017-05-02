package blog.net.dbUtil;

import blog.service.model.User;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

/**
 * Created by Epat on 2017/2/16.
 */
public class UserUtil {

    public static void main(String[] args) {
        ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/article", "", "root", "123456");
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的
        DefaultNameConversion nc = new DefaultNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
        try {
            //sqlManager.genSQLTemplateToConsole("user");
            User user = new User();
            user.setLoginName("4656454");
            sqlManager.insert(user);
        } catch (Exception e) {
            System.out.print("Expetion::" + e);
        }
    }
}
