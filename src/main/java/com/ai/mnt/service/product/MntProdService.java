package com.ai.mnt.service.product;

import java.util.List;

import com.ai.mnt.model.product.MntProd;

public interface MntProdService {
    
    /**
     * 获取产品列表
     * @return
     */
    public List<MntProd> findAllMntProd();
    
    
    /**
     * 获取产品列表
     * @return
     */
    public List<MntProd> findMntProdList(MntProd mntProd);
    
    /**
     * 获取产品列表
     * @return
     */
    public MntProd findMntProdById(Integer pid);
    
    /**
     * 添加产品
     * @return
     */
    public void saveMntProd(MntProd mntProd);
    
    /**
     * 添加产品
     * @return
     */
    public void updateMntProdById(MntProd mntProd);
    
    /**
     * 删除产品
     * @return
     */
    public void deleteMntProdById(Integer pid);
}
