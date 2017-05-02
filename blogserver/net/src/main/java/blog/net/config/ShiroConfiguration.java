package blog.net.config;

import blog.service.model.Rule;
import blog.service.service.RuleService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Epat on 2017/2/11.
 */


@Configuration
public class ShiroConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public EhCacheManager getEhCacheManager () {
        logger.info("===============加载Shiro缓存配置文件===============");
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    //散列加密
    public HashedCredentialsMatcher hashedCredentialsMatcher(EhCacheManager cacheManager){
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(cacheManager);
        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("md5");
        retryLimitHashedCredentialsMatcher.setHashIterations(9841);
        return retryLimitHashedCredentialsMatcher;
    }

    /*设置自定义realm 和缓存*/
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm (EhCacheManager cacheManager) {
        MyShiroRealm realm = new MyShiroRealm();
//        缓存 不知道怎么清理缓存
//        realm.setCachingEnabled(true);
//        realm.setAuthenticationCachingEnabled(true);
//        realm.setAuthenticationCacheName("shiro-authenticationCache");
//        realm.setAuthorizationCachingEnabled(true);
//        realm.setAuthorizationCacheName("shiro-authorizationCache");
        realm.setCredentialsMatcher(hashedCredentialsMatcher(cacheManager));
        realm.setCacheManager(cacheManager);
        return realm;
    }


    /*设置生命周期*/
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor () {
        return new LifecycleBeanPostProcessor();
    }

    /*设置spring自动代理*/
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator () {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        logger.info("==================================注入rememberMe==============================");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
    }

    /*安全管理器*/
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager (MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroRealm);
        dwsm.setCacheManager(getEhCacheManager());
        dwsm.setRememberMeManager(rememberMeManager());
        return dwsm;
    }

    /*授权管理*/
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor (DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /*加载shiro自定义路径规则*/
    private void loadShiroFilterChain (ShiroFilterFactoryBean shiroFilterFactoryBean,RuleService ruleService) {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        logger.info("===============从数据库中读取权限规则，加载到shiroFilter中===============");
        List<Rule> rules = ruleService.all();
        rules.sort((x, y) -> {
            int value = x.getSort().compareTo(y.getSort());
            if (value == 0) {
                value = x.getCreateTime().compareTo(y.getCreateTime());
            }
            return value;
        });
        if (!rules.isEmpty()){
            for (Rule rule:rules) {
                filterChainDefinitionMap.put(rule.getPath(),rule.getRule());
            }
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /*加载自定义Filter*/
    private void loadShiroFilters (ShiroFilterFactoryBean shiroFilterFactoryBean) {
        Map<String,Filter> filters = new LinkedHashMap<String, Filter>();
        AjaxShiroFilter filter = new AjaxShiroFilter();
        filters.put("authc", filter);
        logger.info("===============设置自定义filter===============");
        shiroFilterFactoryBean.setFilters(filters);
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean (DefaultWebSecurityManager securityManager,RuleService ruleService) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        loadShiroFilterChain(shiroFilterFactoryBean,ruleService);
        loadShiroFilters(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }
}
