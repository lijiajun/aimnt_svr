package com.ai.mnt.service.product;

import java.util.List;

import com.ai.mnt.exception.MntDataAccessException;
import com.ai.mnt.model.product.MntLibRelation;
import com.ai.mnt.model.product.MntModuleLib;

/**
 * @Title: MntModuleLibService 
 * @Description: MntModuleLibService ServicePackage
 * @Author: Auto Generate. HE
 * @Date: 2016-5-12
 */

public interface MntModuleLibService {
    
    /**
     * 获取所有库文件信息列表
     * @return List<MntModuleLib>
     */
    public List<MntModuleLib> findAllMntModuleLib();
    
    
    /**
     * 获取库文件信息列表
     * @param product
     * @return List<MntModuleLib>
     */
    public List<MntModuleLib> findMntModuleLibList(MntModuleLib mntModuleLib);
    
    /**
     * 获取库文件信息列表
     * @param product
     * @return List<MntModuleLib>
     */
    public List<MntModuleLib> findModuleLibJoinModule(MntModuleLib mntModuleLib);
    
    /**
     * 通过主键ID获取库文件信息
     * @param libId Primary key
     * @return MntModuleLib
     */
    public MntModuleLib findMntModuleLibByLibId(Integer libId);
    
    /**
     * 添加库文件信息
     * @param MntModuleLib
     */
    public void saveMntModuleLib(MntModuleLib mntModuleLib);
    
    /**
     * 更新库文件信息
     * 根据MntModuleLib的主键更新主键以外的其他字段
     * @param MntModuleLib
     */
    public void updateMntModuleLibByLibId(MntModuleLib mntModuleLib);
    
    /**
     * 根据主键批量删除库文件信息 
     * 非物理删除 修改删除标识
     * @param libId Primary key
     */
    public void deleteMntModuleLibByLibIds(String libIds);
    
    /**
     * 获取库文件关联信息列表
     * @param product
     * @return List<MntModuleLib>
     */
    public List<MntLibRelation> findMntLibRelationList(MntLibRelation mntLibRelation);

    /**
     * 保存库文件导入信息
     * @param excelData
     * @return 
     */
    public void saveLibImportData(List<List<String>> excelData, Integer prodId,
            String verCode) throws MntDataAccessException;
}
