package com.ai.mnt.service.product;

import java.util.List;

import com.ai.mnt.model.product.MntProdVersion;

public interface MntProdVerService {
    
    /**
     * 获取所有产品版本列表
     * @return
     */
    public List<MntProdVersion> findAllProdVer();
    
    /**
     * 获取所有产品版本列表
     * @return
     */
    public List<MntProdVersion> findProdVerList(MntProdVersion mntProdVersion);
    
    /**
     * 获取产品版本列表
     * @return
     */
    public List<MntProdVersion> findProdVerListByPid(Integer prodId);
    
    /**
     * 获取产品版本
     * @return
     */
    public MntProdVersion findProdVerListByVerId(Integer verId);
    
    /**
     * 添加产品版本信息
     * @return
     */
    public void saveMntProdVer(MntProdVersion mntProdVer);
    
    /**
     * 更新产品版本信息
     * @return
     */
    public void updateMntProdVer(MntProdVersion mntProdVer);
    
    
    /**
     * 删除产品版本信息
     * @return
     */
    public void deleteMntProdVerById(Integer verId);
    
}
