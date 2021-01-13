package com.superz.shirospringboot.common.context;

import com.superz.shirospringboot.common.shiro.realm.CasRealm;
import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author zhang.chao
 * @date 2021/1/2
 */
@Configuration
public class SsoAuthenticationContext {

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

    @Bean
    public CasRealm casRealm() {
        CasRealm realm = new CasRealm();
        // 使用自定义的realm
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
     */
    @Bean
    public Pac4jSubjectFactory subjectFactory(){
        return new Pac4jSubjectFactory();
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
    public Config config() {
        Config config = new Config(casClient());
        config.setSessionStore(shiroSessionStore());
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
    public CasClient casClient(){
        CasClient casClient = new CasClient(casConfig());
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
