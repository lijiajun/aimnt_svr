package com.ai.mnt.service.product;

import java.util.List;
import com.ai.mnt.model.product.SysPara;

/**
 * @Title: SysParaService 
 * @Description: SysParaService ServicePackage
 * @Author: Auto Generate. HE
 * @Date: 2016-5-17
 */

public interface SysParaService {
    
    /**
     * 获取所有系统参数表XG.SYSPARA列表
     * @return List<SysPara>
     */
    public List<SysPara> findAllSysPara();
    
    
    /**
     * 获取系统参数表XG.SYSPARA列表
     * @param product
     * @return List<SysPara>
     */
    public List<SysPara> findSysParaList(SysPara sysPara);
    
    /**
     * 通过主键ID获取系统参数表XG.SYSPARA
     * @param paraId Primary key
     * @return SysPara
     */
    public SysPara findSysParaByParaId(Integer paraId);
    
    /**
     * 添加系统参数表XG.SYSPARA
     * @param SysPara
     */
    public void saveSysPara(SysPara sysPara);
    
    /**
     * 更新系统参数表XG.SYSPARA
     * 根据SysPara的主键更新主键以外的其他字段
     * @param SysPara
     */
    public void updateSysParaByParaId(SysPara sysPara);
    
    /**
     * 根据主键批量删除系统参数表XG.SYSPARA 
     * 非物理删除 修改删除标识
     * @param paraId Primary key
     */
    public void deleteSysParaByParaIds(String paraIds);
}
