package com.ai.mnt.service.product;

import java.util.List;

import com.ai.mnt.model.product.MntReleaseRecDtl;

public interface MntReleaseRecDtlService {
    
    /**
     * 获取发布明细
     * @return
     */
    public List<MntReleaseRecDtl> findRelDtlList(MntReleaseRecDtl mntReleaseRecDtl);
    
    /**
     * 保存发布明细 多安装点
     * @return
     */
    public void saveReleaseRecWithBaseIds(MntReleaseRecDtl mntReleaseRecDtl, String[] baseIdAry);
    
    
    /**
     * 保存发布明细包含发布信息
     * @return
     */
    public MntReleaseRecDtl getRelDtlByDtlId(Integer dtlId);
    
    /**
     * 保存发布明细包含发布信息
     * @return
     */
    public void saveRelDtl(MntReleaseRecDtl mntReleaseRecDtl);
    
    /**
     * 更新发布明细包含发布信息
     * @return
     */
    public void updateRelDtlById(MntReleaseRecDtl mntReleaseRecDtl);
    
    
    /**
     * 删除发布明细包含发布信息
     * @return
     */
    public void deleteRelDtlByIds(String dtlIds);
    
    /**
     * 获取发布明细包含发布信息
     * @return
     */
    public List<MntReleaseRecDtl> findRecAndDtlJoinList(MntReleaseRecDtl mntReleaseRecDtl);
    
    
    /**
     * 统计安装点的需求量
     * @param mntReleaseRecDtl
     * @return
     */
    public List<MntReleaseRecDtl> getStatResForBaseId(MntReleaseRecDtl mntReleaseRecDtl);
    
    //导入
    public void importRelData(List<List<String>> excelData) throws Exception;
    
}
