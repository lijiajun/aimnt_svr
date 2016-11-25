package com.ai.mnt.service.product;

import java.util.List;

import com.ai.mnt.model.product.MntLibRelationExt;

/**
 * @Title: MntLibRelationExtService 
 * @Description: MntLibRelationExtService ServicePackage
 * @Author: Auto Generate.
 * @Date: 2016-7-11
 */

public interface MntLibRelationExtService {
    
    /**
     * 获取所有库文件扩展关联信息列表
     * @return List<MntLibRelationExt>
     */
    public List<MntLibRelationExt> findAllMntLibRelationExt();
    
    
    /**
     * 获取库文件扩展关联信息列表
     * @param lib
     * @return List<MntLibRelationExt>
     */
    public List<MntLibRelationExt> findMntLibRelationExtList(MntLibRelationExt mntLibRelationExt);
    
    /**
     * 通过主键ID获取库文件扩展关联信息
     * @param id Primary key
     * @return MntLibRelationExt
     */
    public MntLibRelationExt findMntLibRelationExtById(Integer id);
    
    /**
     * 添加库文件扩展关联信息
     * @param MntLibRelationExt
     */
    public void saveMntLibRelationExt(MntLibRelationExt mntLibRelationExt);
    
    /**
     * 更新库文件扩展关联信息
     * 根据MntLibRelationExt的主键更新主键以外的其他字段
     * @param MntLibRelationExt
     */
    public void updateMntLibRelationExtById(MntLibRelationExt mntLibRelationExt);
    
    /**
     * 根据主键批量删除库文件扩展关联信息 
     * 非物理删除 修改删除标识
     * @param id Primary key
     */
    public void deleteMntLibRelationExtByIds(String ids);
    
    /**
     * 导入依赖库
     * @return
     */
    public void saveLibRelationImportData(List<List<String>> excelData, Integer prodId, String verCode) throws Exception;
}
