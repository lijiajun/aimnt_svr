package com.ai.mnt.service.inst;

import java.util.List;
import com.ai.mnt.model.inst.MntProdBaseRel;

/**
 * @Title: MntProdBaseRelService 
 * @Description: MntProdBaseRelService ServicePackage
 * @Author: Auto Generate.
 * @Date: 2016-10-13
 */

public interface MntProdBaseRelService {
    
    /**
     * 获取所有产品安装点映射列表
     * @return List<MntProdBaseRel>
     */
    public List<MntProdBaseRel> findAllMntProdBaseRel();
    
    
    /**
     * 获取产品安装点映射列表
     * @param inst
     * @return List<MntProdBaseRel>
     */
    public List<MntProdBaseRel> findMntProdBaseRelList(MntProdBaseRel mntProdBaseRel);
    
    /**
     * 通过主键ID获取产品安装点映射
     * @param id Primary key
     * @return MntProdBaseRel
     */
    public MntProdBaseRel findMntProdBaseRelById(Integer id);
    
    /**
     * 添加产品安装点映射
     * @param MntProdBaseRel
     */
    public void saveMntProdBaseRel(MntProdBaseRel mntProdBaseRel);
    
    /**
     * 更新产品安装点映射
     * 根据MntProdBaseRel的主键更新主键以外的其他字段
     * @param MntProdBaseRel
     */
    public void updateMntProdBaseRelById(MntProdBaseRel mntProdBaseRel);
    
    /**
     * 根据主键批量删除产品安装点映射 
     * 非物理删除 修改删除标识
     * @param id Primary key
     */
    public void deleteMntProdBaseRelByIds(String ids);
    
    public List<MntProdBaseRel> findProdJoinBaseInfoList();
}
