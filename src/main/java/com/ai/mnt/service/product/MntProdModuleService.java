package com.ai.mnt.service.product;

import java.util.List;

import com.ai.mnt.model.product.MntProdModule;

public interface MntProdModuleService {
    
    /**
     * 获取所有产品模块列表
     * @return
     */
    public List<MntProdModule> findAllProdModule();
    
    /**
     * 获取产品模块列表
     * @return
     */
    public List<MntProdModule> findProdModuleList(MntProdModule prodModule);
    
    /**
     * 通过ID获取产品模块
     * @return
     */
    public MntProdModule findProdModuleById(Integer mid);
    
    /**
     * 添加产品模块
     * @return
     */
    public void saveMntProdModule(MntProdModule prodModule);
    
    /**
     * 添加产品模块
     * @return
     */
    public void saveMntProdModuleList(List<MntProdModule> prodModuleList);
    
    /**
     * 保存导入的产品模块
     * @return
     */
    public void saveModuleImportData(List<List<String>> excelData, Integer prodId, String verCode) throws Exception;
    
    /**
     * 更新产品模块
     * @return
     */
    public void updateMntProdById(MntProdModule prodModule);
    
    /**
     * 删除产品模块
     * @return
     */
    public void deleteMntProdByIds(String moduleIds);
}
