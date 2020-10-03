package com.qf.j2003.config;

import com.qf.j2003.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeffrey on 2020/9/2.
 */
@Configuration  //标注此类为一个配置管理类，自动备spring管理
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager ){
//        创建一个shiro的拦截过滤器对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        给过滤器设定安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
            map.put("/before/*","anon");//匿名可访问
            map.put("/main","authc");//对/main的请求需要认证后才可访问
          //  map.put("/one","perms[user_edit]"); //用户必须登录后且拥有user_edit权限才可访问/one
        //    map.put("/two","perms[user_forbidden ]"); //用户必须登录后且拥有user_forbidden权限才可访问/two
//        配置shiro过滤器拦截策略
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");//用户无权访问时，导航的地址
        shiroFilterFactoryBean.setLoginUrl("/login"); //其他需要用户访问的登录导航

        return shiroFilterFactoryBean;
    }
    @Bean(name="securityManager")
    public DefaultWebSecurityManager  securityManager(@Qualifier("myRealm") AuthorizingRealm authorizingRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        给安全管理器配置安全策略
        securityManager.setRealm(authorizingRealm);
        return securityManager;
    }
    @Bean(name="myRealm")
    public MyShiroRealm myShiroRealm(@Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher){
//        创建自定义的安全策略
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //给自定义的realm增加安全凭证
        myShiroRealm.setCredentialsMatcher(credentialsMatcher);
        return myShiroRealm;
    }
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher(){
        //增加一个凭证匹配器对象
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
       //设置匹配器的算法名称
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //设置匹配器的的hash次数
        hashedCredentialsMatcher.setHashIterations(2);
        return  hashedCredentialsMatcher;


    }




    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor( @Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
