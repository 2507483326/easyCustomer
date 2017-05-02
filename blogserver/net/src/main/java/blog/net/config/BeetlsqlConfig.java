package blog.net.config;

import org.apache.logging.log4j.core.config.Order;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

/**
 * Created by Epat on 2017/3/10.
 */

@Configuration
public class BeetlsqlConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean(name = "beetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer(MyJdbc myJdbc){
        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
        conf.setBasePackage("blog.db.dao");
        conf.setDaoSuffix("Dao");
        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
        return conf;
    }

    @Bean(name = "sqlManagerFactoryBean")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DataSource dataSource) {
        SqlManagerFactoryBean factoryBean = new SqlManagerFactoryBean();
        BeetlSqlDataSource sqlDataSource = new BeetlSqlDataSource();
        sqlDataSource.setMasterSource(dataSource);
        factoryBean.setCs(sqlDataSource);
        factoryBean.setDbStyle(new MySqlStyle());
        factoryBean.setInterceptors(new Interceptor[]{new DebugInterceptor()});
        factoryBean.setNc(new DefaultNameConversion());
        factoryBean.setSqlLoader(new ClasspathLoader("/sql"));
        return factoryBean;
    }

    @Bean(name = "datasource")
    public DataSource getDataSource() {
        System.out.println("-------------------- primaryDataSource init ---------------------");
        return DataSourceBuilder.create().url("jdbc:mysql://localhost/article").username("root").password("123456").build();
    }

    @Bean(name="txManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("datasource") DataSource dataSource){
        DataSourceTransactionManager dsm = new DataSourceTransactionManager();
        dsm.setDataSource(dataSource);
        return dsm;
    }
}
