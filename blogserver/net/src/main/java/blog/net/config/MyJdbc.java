package blog.net.config;

import org.apache.logging.log4j.core.config.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by Epat on 2017/3/10.
 */

@Component
@Order(1)
@ConfigurationProperties(prefix = "myJdbc")
@Validated
public class MyJdbc {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MyJdbc(){
        logger.info("初始化成功");
    }
    // Dao层根目录
    @NotNull
    private String basePackage;

    // Dao层后缀
    @NotNull
    private String daoSuffix;

    // sql文件加载路径
    @NotNull
    private String sqlLoader;

    @NotNull
    private String url;

    @NotNull
    private String userName;

    @NotNull
    private String passWord;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDaoSuffix() {
        return daoSuffix;
    }

    public void setDaoSuffix(String daoSuffix) {
        this.daoSuffix = daoSuffix;
    }

    public String getSqlLoader() {
        return sqlLoader;
    }

    public void setSqlLoader(String sqlLoader) {
        this.sqlLoader = sqlLoader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "MyJdbc{" +
                "basePackage='" + basePackage + '\'' +
                ", daoSuffix='" + daoSuffix + '\'' +
                ", sqlLoader='" + sqlLoader + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
