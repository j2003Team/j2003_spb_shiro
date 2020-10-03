package com.qf.j2003.shiro;

import com.qf.j2003.po.SysPerm;
import com.qf.j2003.po.SysUser;
import com.qf.j2003.service.SysPermService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffrey on 2020/9/2.
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired(required = false)
    private SysPermService sysPermService;
    //    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object principal = principalCollection.getPrimaryPrincipal();
//        根据用户名查询用户权限
        List<SysPerm> permsList = sysPermService.findPermsByName((String) principal);
        if(permsList!=null){
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();


//           权限去重
            Set<String> perms = new HashSet<String>();
            for (SysPerm perm: permsList) {
                perms.add(perm.getPermName());
            }
//            给shiro的安全管理器授权
            authorizationInfo.setStringPermissions(perms);
            return authorizationInfo;
        }
        return null;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Object principal = token.getPrincipal();
//        根据用户名查询用户信息（密码）
        SysUser sysUser = sysPermService.findUserByName((String) principal);
        if(sysUser!=null){
            String salt=sysUser.getLoginName();
            ByteSource mysalt = ByteSource.Util.bytes(salt);

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, sysUser.getPassword(), mysalt, getName());

            System.out.println(sysUser.getPassword()+"==="+sysUser.getLoginName());
            return simpleAuthenticationInfo;
        }
        return null;
    }

}
