package com.qf.j2003.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by jeffrey on 2020/9/3.
 */
@ControllerAdvice   //标识此类为一个捕获controller的通知（aop）
public class GlobalExceptionController {
    @ExceptionHandler(value={UnauthorizedException.class})
    public String handlerException(){

        return "/unauth";
    }
        @ExceptionHandler(value = {AuthorizationException.class})
    public  String hander1Exception(){
        return "/unauth";
    }
}
