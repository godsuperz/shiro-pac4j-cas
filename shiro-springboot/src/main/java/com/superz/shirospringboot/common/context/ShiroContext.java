package com.superz.shirospringboot.common.context;

import com.superz.shirospringboot.common.shiro.realm.CasRealm;
import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.config.Config;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhang.chao
 * @date 2021/1/2
 */
@Configuration
public class ShiroContext {

    /** 项目工程路径 */
    @Value("${cas.project.url}")
    private String projectUrl;

    /** 项目cas服务路径 */
    @Value("${cas.server.url}")
    private String casServerUrl;

    @Value("${cas.server.login-url}")
    private String casServerLoginUrl;

    /** 客户端名称 */
    @Value("${cas.client-name}")
    private String clientName;

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(Pac4jSubjectFactory subjectFactory, SessionManager sessionManager, CasRealm casRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(casRealm);
        manager.setSubjectFactory(subjectFactory);
        manager.setSessionManager(sessionManager);
        return manager;
    }

    @Bean
    public CasRealm casRealm(){
        CasRealm realm = new CasRealm();
        // 使用自定义的realm
        realm.setClientName(clientName);
        realm.setCachingEnabled(false);
        //暂时不使用缓存
        realm.setAuthenticationCachingEnabled(false);
        realm.setAuthorizationCachingEnabled(false);
        //realm.setAuthenticationCacheName("authenticationCache");
        //realm.setAuthorizationCacheName("authorizationCache");
        return realm;
    }

    /**
     * 使用 pac4j 的 subjectFactory
     * @return
     */
    @Bean
    public Pac4jSubjectFactory subjectFactory(){
        return new Pac4jSubjectFactory();
    }

    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean() {
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
        return filterRegistration;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
        /*下面这些规则配置最好配置到配置文件中 */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/", "securityFilter");
        filterChainDefinitionMap.put("/index", "securityFilter");
        filterChainDefinitionMap.put("/api/**", "securityFilter");
        filterChainDefinitionMap.put("/callback", "callbackFilter");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


    /**
     * shiroFilter
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager, Config config) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // 添加casFilter到shiroFilter中
        loadShiroFilterChain(shiroFilterFactoryBean);
        Map<String, Filter> filters = new HashMap<>(3);
        //cas 资源认证拦截器
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setConfig(config);
        securityFilter.setClients(clientName);
        filters.put("securityFilter", securityFilter);
        //cas 认证后回调拦截器
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(config);
        callbackFilter.setDefaultUrl(projectUrl);
        filters.put("callbackFilter", callbackFilter);
        // 注销 拦截器
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(config);
        logoutFilter.setCentralLogout(true);
        logoutFilter.setLocalLogout(true);
        logoutFilter.setDefaultUrl(projectUrl + "/callback?client_name=" + clientName);
        filters.put("logout",logoutFilter);
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SessionDAO sessionDAO(){
        return new MemorySessionDAO();
    }

    /**
     * 自定义cookie名称
     */
    @Bean
    public SimpleCookie sessionIdCookie(){
        SimpleCookie cookie = new SimpleCookie("sid");
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        return cookie;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(SimpleCookie sessionIdCookie, SessionDAO sessionDAO){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdCookieEnabled(true);
        //30分钟
        sessionManager.setGlobalSessionTimeout(180000);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter() {
        FilterRegistrationBean<SingleSignOutFilter> bean = new FilterRegistrationBean<>();
        bean.setName("singleSignOutFilter");
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setLogoutParameterName(casServerUrl);
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        bean.setFilter(singleSignOutFilter);
        bean.addUrlPatterns("/*");
        bean.setEnabled(true);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    /**
     *  pac4j配置
     */
    @Bean
    public Config config(CasClient casClient, ShiroSessionStore shiroSessionStore) {
        Config config = new Config(casClient);
        config.setSessionStore(shiroSessionStore);
        return config;
    }

    /**
     * 自定义存储
     */
    @Bean
    public ShiroSessionStore shiroSessionStore(){
        return new ShiroSessionStore();
    }

    /**
     * cas 客户端配置
     */
    @Bean
    public CasClient casClient(CasConfiguration casConfig){
        CasClient casClient = new CasClient(casConfig);
        //客户端回调地址
        casClient.setCallbackUrl(projectUrl + "/callback?client_name=" + clientName);
        casClient.setName(clientName);
        return casClient;
    }

    /**
     * 请求cas服务端配置
     */
    @Bean
    public CasConfiguration casConfig() {
        final CasConfiguration configuration = new CasConfiguration();
        //CAS server登录地址
        configuration.setLoginUrl(casServerLoginUrl);
        configuration.setAcceptAnyProxy(true);
        configuration.setPrefixUrl(casServerUrl + "/");
        return configuration;
    }
}
