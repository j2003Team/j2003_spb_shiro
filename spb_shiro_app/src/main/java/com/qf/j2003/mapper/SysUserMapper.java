package com.qf.j2003.mapper;

import com.qf.j2003.po.SysUser;

/**
 * Created by jeffrey on 2020/9/2.
 */
public interface SysUserMapper {
    public  SysUser findUserByLoginName(String username);
}
