package com.qf.j2003.mapper;

import com.qf.j2003.po.SysPerm;

import java.util.List;

/**
 * Created by jeffrey on 2020/9/2.
 */
public interface SysPermMapper {
    /**
     * 根据用户名查询权限清单
     * @param username
     * @return
     */
    public List<SysPerm> findPermsByLoginName(String username);
}
