package com.ai.mnt.service.sys.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.sys.SysUserMapper;
import com.ai.mnt.service.sys.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    
    
    @Override
    public SysUser findUserByUserName(String userName) {
        SysUser sysUser = sysUserMapper.findUserByUserName(userName);
        return sysUser;
    }

    @Override
    public SysUser findUserByUserId(Integer userId) {
        SysUser sysUser = sysUserMapper.findByPrimaryKey(userId);
        return sysUser;
    }

    @Override
    public List<SysUser> findAllUser() {
        List<SysUser> sysUserList = sysUserMapper.findAll();
        return sysUserList;
    }

    @Override
    public List<SysUser> findUserList(SysUser sysUser) {
        List<SysUser> sysUserList = sysUserMapper.findList(sysUser);
        return sysUserList;
    }

    /**
     * 更新用户
     * @param sysUser
     */
    @Override
    public void updateUser(SysUser sysUser) {
        
        //SysUser currentUser = userRealm.getCurrentUser();
        sysUser.setModifier("jack");
        sysUser.setModifyDate(new Date());
        
        sysUserMapper.updateByPrimaryKey(sysUser);
    }


    
}
