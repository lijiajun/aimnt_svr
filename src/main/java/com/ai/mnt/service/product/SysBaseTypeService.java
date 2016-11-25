package com.ai.mnt.service.product;

import java.util.List;
import com.ai.mnt.model.product.SysBaseType;

/**
 * @Title: SysBaseTypeService 
 * @Description: SysBaseTypeService ServicePackage
 * @Author: Auto Generate. HE
 * @Date: 2016-5-17
 */

public interface SysBaseTypeService {
    
    /**
     * 获取所有系统枚举类型表列表
     * @return List<SysBaseType>
     */
    public List<SysBaseType> findAllSysBaseType();
    
    
    /**
     * 获取系统枚举类型表列表
     * @param product
     * @return List<SysBaseType>
     */
    public List<SysBaseType> findSysBaseTypeList(SysBaseType sysBaseType);
    
    /**
     * 通过主键ID获取系统枚举类型表
     * @param id Primary key
     * @return SysBaseType
     */
    public SysBaseType findSysBaseTypeById(Integer id);
    
    /**
     * 添加系统枚举类型表
     * @param SysBaseType
     */
    public void saveSysBaseType(SysBaseType sysBaseType);
    
    /**
     * 更新系统枚举类型表
     * 根据SysBaseType的主键更新主键以外的其他字段
     * @param SysBaseType
     */
    public void updateSysBaseTypeById(SysBaseType sysBaseType);
    
    /**
     * 根据主键批量删除系统枚举类型表 
     * 非物理删除 修改删除标识
     * @param id Primary key
     */
    public void deleteSysBaseTypeByIds(String ids);
}
