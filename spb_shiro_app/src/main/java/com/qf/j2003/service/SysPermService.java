package com.qf.j2003.service;

import com.qf.j2003.po.SysPerm;
import com.qf.j2003.po.SysUser;

import java.util.List;

/**
 * Created by jeffrey on 2020/9/2.
 */
public interface SysPermService {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public SysUser findUserByName(String username);

    /**
     * 根据用户名查询权限信息
     * @param username
     * @return
     */
    public List<SysPerm> findPermsByName(String username);
}
