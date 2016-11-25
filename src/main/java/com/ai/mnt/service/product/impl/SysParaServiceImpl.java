package com.ai.mnt.service.product.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.model.product.SysPara;
import com.ai.mnt.persistence.product.SysParaMapper;
import com.ai.mnt.service.product.SysParaService;

/**
 * @Title: SysParaServiceImpl 
 * @Description: SysParaServiceImpl ServiceImplPackage
 * @Author: Auto Generate. HE
 * @Date: 2016-5-17
 */
@Service
public class SysParaServiceImpl implements SysParaService{

    @Autowired
    SysParaMapper sysParaMapper;
    
//    @Autowired
//    UserRealm userRealm;
    
    /**
     * 获取所有系统参数表XG.SYSPARA列表
     * @return List<SysPara>
     */
    @Override
    public List<SysPara> findAllSysPara() {
        List<SysPara> sysParaList = sysParaMapper.findAll();
        //cvtContentList(sysParaList);
        return sysParaList;
    }

    /**
     * 获取系统参数表XG.SYSPARA列表
     * @param product
     * @return List<SysPara>
     */
    @Override
    public List<SysPara> findSysParaList(SysPara sysPara) {
        List<SysPara> sysParaList = sysParaMapper.findList(sysPara);
        //cvtContentList(sysParaList);
        return sysParaList;
    }

    /**
     * 通过主键ID获取系统参数表XG.SYSPARA
     * @param paraId Primary key
     * @return SysPara
     */
    @Override
    public SysPara findSysParaByParaId(Integer paraId) {
        SysPara sysPara = sysParaMapper.findByPrimaryKey(paraId);
        return sysPara;
    }

    /**
     * 添加系统参数表XG.SYSPARA
     * @param SysPara
     */
    @Override
    public void saveSysPara(SysPara sysPara) {
        sysParaMapper.save(sysPara);
        
    }

    /**
     * 更新系统参数表XG.SYSPARA
     * 根据SysPara的主键更新主键以外的其他字段
     * @param SysPara
     */
    @Override
    public void updateSysParaByParaId(SysPara sysPara) {
        sysParaMapper.updateByPrimaryKey(sysPara);
        
    }

    /**
     * 根据主键批量删除系统参数表XG.SYSPARA 
     * 非物理删除 修改删除标识
     * @param paraId Primary key
     */
    @Override
    public void deleteSysParaByParaIds(String paraIds) {
        String[] paraIdAry = paraIds.split(",");
        for(String paraId : paraIdAry) {
            sysParaMapper.deleteByPrimaryKey(Integer.parseInt(paraId));
        }
    }
    
    //private void cvtContentList(List<SysPara> SysParaList) {
    //    for(SysPara sysPara : SysParaList) {
    //    }
    //}
}

