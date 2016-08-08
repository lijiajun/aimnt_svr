package com.ai.mnt.service.inst.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.model.inst.MntInstallBaseInfo;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.base.MultipleDataSource;
import com.ai.mnt.persistence.inst.MntInstallBaseInfoMapper;
import com.ai.mnt.service.inst.MntInstallBaseInfoService;
import com.ai.mnt.service.sys.SysUserService;

@Service
public class MntInstallBaseInfoServiceImpl implements MntInstallBaseInfoService{

    
    @Autowired
    MntInstallBaseInfoMapper mntInstallBaseInfoMapper;
    
    @Autowired
    SysUserService sysUserService;
    
    @Override
    public MntInstallBaseInfo findInstallBaseInfoByBaseId(Integer baseId) {
        MntInstallBaseInfo installBaseInfo = mntInstallBaseInfoMapper.findByPrimaryKey(baseId);
        return installBaseInfo;
    }

    @Override
    public List<MntInstallBaseInfo> findAllMntInstallBaseInfo() {
        List<MntInstallBaseInfo> list = mntInstallBaseInfoMapper.findAll();
        return list;
    }

    @Override
    public List<MntInstallBaseInfo> findInstallBaseInfoList(MntInstallBaseInfo mntInstallBaseInfo) {
        List<MntInstallBaseInfo> list = mntInstallBaseInfoMapper.findList(mntInstallBaseInfo);
        return list;
    }

    @Override
    public void saveInstallBaseInfo(MntInstallBaseInfo mntInstallBaseInfo) {
        SysUser currentUser = new SysUser();
        mntInstallBaseInfo.setCreator(currentUser.getUserName());
        mntInstallBaseInfo.setCreateDate(new Date());
        mntInstallBaseInfo.setModifier(currentUser.getUserName());
        mntInstallBaseInfo.setModifyDate(new Date());
        mntInstallBaseInfoMapper.save(mntInstallBaseInfo);
    }

    @Override
    public void updateInstallBaseInfo(MntInstallBaseInfo mntInstallBaseInfo) {
        
        MultipleDataSource.setDataSourceKey("mySqlDataSource");
        
        mntInstallBaseInfo.setBaseId(15);
        mntInstallBaseInfo.setBaseDesc("测试一下3333");
        SysUser currentUser = new SysUser();
        mntInstallBaseInfo.setModifier(currentUser.getUserName());
        mntInstallBaseInfo.setModifyDate(new Date());
        mntInstallBaseInfoMapper.updateByPrimaryKey(mntInstallBaseInfo);
        
        MultipleDataSource.setDataSourceKey("oracleDataSource");
        SysUser sysUser = new SysUser();
        sysUser.setUserId(1);
        sysUser.setRemark("测试一下2222333");
        sysUserService.updateUser(sysUser);
        
        
        //int i = 2 / 0;
        
        
    }

}
