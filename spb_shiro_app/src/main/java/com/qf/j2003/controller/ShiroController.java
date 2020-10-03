package com.qf.j2003.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jeffrey on 2020/9/2.
 */
@Controller
public class ShiroController {
    @RequestMapping("/login")
    public String showLogin(){
        return "login";
    }
    @RequestMapping("/one")
    @RequiresPermissions(value={"user_edit"})  //此请求需要user_edit权限才可访问
    public String showOne(){
        return "one";
    }
    @RequiresPermissions(value={"user_forbidden"})  //此请求需要user_forbidden权限才可访问
    @RequestMapping("/two")
    public String showTwo(){
        return "two";
    }
    @RequestMapping("/unauth")
    public String showUnauth(){
        return "unauth";
    }
//    @RequestMapping("/error")
//    public String showError(){
//        return "error";
//    }
    @RequestMapping("/main")
    @RequiresRoles(value={"authc"})
    public String showMain(){
        return "main";
    }
//    登陆处理
    @RequestMapping(value="/dealLogin",method = RequestMethod.POST)
   public String dealLogin(@RequestParam("username") String username,
                           @RequestParam("password") String password ){
     System.out.println(username+"--"+password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            if(subject.isAuthenticated()){
                return "main";
            }
        }catch (AuthenticationException ae){
//            .....
        }
        return "login";
    }
    //    登出处理
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();//登出处理
        return "login";
    }
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "before/index";
    }

}
