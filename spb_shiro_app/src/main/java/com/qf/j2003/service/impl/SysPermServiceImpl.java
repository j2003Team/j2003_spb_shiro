package com.qf.j2003.service.impl;

import com.qf.j2003.mapper.SysPermMapper;
import com.qf.j2003.mapper.SysUserMapper;
import com.qf.j2003.po.SysPerm;
import com.qf.j2003.po.SysUser;
import com.qf.j2003.service.SysPermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeffrey on 2020/9/2.
 */
@Service
public class SysPermServiceImpl implements SysPermService {
    @Autowired(required = false)
    private SysUserMapper userMapper;
    @Autowired(required = false)
    private SysPermMapper permMapper;
    @Override
    public SysUser findUserByName(String username) {
        SysUser sysUser = userMapper.findUserByLoginName(username);
        return sysUser;
    }

    @Override
    public List<SysPerm> findPermsByName(String username) {
        List<SysPerm> perms = permMapper.findPermsByLoginName(username);
        return perms;
    }
}
