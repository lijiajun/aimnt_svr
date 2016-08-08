package com.ai.mnt.service.sys;

import java.util.List;

import com.ai.mnt.model.sys.SysUser;

public interface SysUserService {

    /**
     * 通过用户名获取用户
     * @return
     */
    public SysUser findUserByUserName(String userName);

    /**
     * 通过用户名获取用户
     * @return
     */
    public SysUser findUserByUserId(Integer userId);
    
    /**
     * 通过用户名获取用户
     * @return
     */
    public List<SysUser> findAllUser();
    
    /**
     * 通过用户名获取用户
     * @return
     */
    public List<SysUser> findUserList(SysUser sysUser);
    
    /**
     * 更新用户
     * @param sysUser
     */
    public void updateUser(SysUser sysUser);
    
    
    
}
