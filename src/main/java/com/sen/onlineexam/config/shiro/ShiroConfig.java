package com.sen.onlineexam.config.shiro;

import com.sen.onlineexam.config.shiro.realm.PhoneRealm;
import com.sen.onlineexam.config.shiro.realm.UserRealm;
import com.sen.onlineexam.config.shiro.session.UserSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //shrioFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        bean.setLoginUrl("/autherror?code=1");
        bean.setUnauthorizedUrl("/autherror?code=2");

        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login/cellphone","anon");
        filterMap.put("/login","anon");
        filterMap.put("/register","anon");
        filterMap.put("/register/check","anon");
        filterMap.put("/captcha/sent","anon");
        filterMap.put("/captcha/verify","anon");
        filterMap.put("/captcha","anon");
        filterMap.put("/autherror","anon");
        filterMap.put("/user/findPass","anon");
        filterMap.put("/**","authc");

        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    //DefaultWebSecurityManager
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm, @Qualifier("phoneRealm") PhoneRealm phoneRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(phoneRealm);
        securityManager.setRealms(realms);
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    //创建realm 对象
    @Bean("userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        //设置加密方法
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }

    @Bean("phoneRealm")
    public PhoneRealm phoneRealm(){
        return new PhoneRealm();
    }

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;

    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }

    //redis控制器
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host+":"+port);
        return redisManager;
    }

    //sessionDao
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    //会话管理器
    public DefaultWebSessionManager sessionManager(){
        UserSessionManager userSessionManager = new UserSessionManager();
        userSessionManager.setSessionDAO(redisSessionDAO());
        //禁用cookie
        userSessionManager.setSessionIdCookieEnabled(false);
        //禁用URL重写
        userSessionManager.setSessionIdUrlRewritingEnabled(false);
        return userSessionManager;
    }

    //缓存管理器
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }

    //开启shiro对注解的支持
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
