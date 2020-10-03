import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

public class TestShiroBase {
    @Test
    public void  testShiroFirst() {
        //		根据ini初始化文件创建SecurityManager工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
//		使用工厂创建securityManager实例
        SecurityManager securityManager = factory.getInstance();
//		使用工具类设置securityManager运行环境
        SecurityUtils.setSecurityManager(securityManager);
//		获取Subject实例
        Subject subject = SecurityUtils.getSubject();
//		封装一个用户信息的令牌（用户名，密码），相当于在controller获得用户的登陆信息并封装
        String username = "zhangsan";
        AuthenticationToken token = new UsernamePasswordToken(username, "1234");
        try {
//			用户登录
            subject.login(token);
        } catch (UnknownAccountException ue) {
            System.out.println("账号：" + username + "不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("不正确的用户凭证！");
        }
        if (subject.isAuthenticated()) {
            System.out.println("zhangsan认证通过！");
        } else {
            System.out.println("zhangsan认证未通过！");
        }
    }
        @Test
         public void testShiroPerms(){
            Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-perms.ini");
            SecurityManager securityManager = factory.getInstance();
            SecurityUtils.setSecurityManager(securityManager);
            Subject subject = SecurityUtils.getSubject();
            String username="zhangsan";
            String password="123";
            AuthenticationToken token = new UsernamePasswordToken(username, password);
            try {
//			用户登陆认证
                subject.login(token);
            } catch (AuthenticationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("用户认证失败");
            }
            boolean bl =subject.hasRole("role1");
            System.out.println(username+"含有role1角色吗？："+bl);
            boolean[] hasRoles = subject.hasRoles(Arrays.asList("role1","role2"));
            System.out.println(username+"含有role1,role2角色吗？："+Arrays.toString(hasRoles));
            boolean permitted1 = subject.isPermitted("user:create");
            System.out.println(username+"是否含有user：create权限："+permitted1);
            boolean permitted2 = subject.isPermittedAll("user:create","user:query");
            System.out.println(username+"是否含有user：create和user:update权限："+permitted2);
        }



}
