package com.ai.mnt.persistence.sys;


import org.springframework.stereotype.Repository;

import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * 
 * @Title: SysUserMapper 
 * @Description: SysUserMapper Persistence
 * @author Auto Genrate 
 * @date 2016-4-11
 */
@Repository
public interface SysUserMapper extends CrudMapper<SysUser, Integer>{
    
    public SysUser findUserByUserName(String userName);
    
}