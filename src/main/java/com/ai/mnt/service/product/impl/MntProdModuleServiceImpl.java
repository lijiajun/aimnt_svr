package com.ai.mnt.service.product.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.exception.MntDataAccessException;
import com.ai.mnt.model.product.MntProdModule;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.product.MntProdModuleMapper;
import com.ai.mnt.service.product.MntProdModuleService;

@Service
public class MntProdModuleServiceImpl implements MntProdModuleService{

    @Autowired
    MntProdModuleMapper mntProdModuleMapper;
    
//    @Autowired
//    UserRealm userRealm;
    
    @Override
    public List<MntProdModule> findAllProdModule() {
        List<MntProdModule> mntProdModuleList = mntProdModuleMapper.findAll();
        return mntProdModuleList;
    }

    @Override
    public List<MntProdModule> findProdModuleList(MntProdModule prodModule) {
        List<MntProdModule> mntProdModuleList = mntProdModuleMapper.findList(prodModule);
        cvtContentList(mntProdModuleList);
        return mntProdModuleList;
    }

    @Override
    public MntProdModule findProdModuleById(Integer mid) {
        MntProdModule mntProdModule = mntProdModuleMapper.findByPrimaryKey(mid);
        return mntProdModule;
    }

    @Override
    public void saveMntProdModule(MntProdModule prodModule) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        prodModule.setDeleteFlag("0");
        prodModule.setCreator(currentUser.getUserName());
        prodModule.setModifier(currentUser.getUserName());
        prodModule.setCreateDate(new Date());
        prodModule.setModifyDate(new Date());
        mntProdModuleMapper.save(prodModule);
    }

    @Override
    public void saveMntProdModuleList(List<MntProdModule> prodModuleList) {
        mntProdModuleMapper.saveAll(prodModuleList);
    }
    
    /**
     * 保存导入数据
     */
    @Transactional
    @Override
    public void saveModuleImportData(List<List<String>> excelData, Integer prodId, String verCode) throws MntDataAccessException {
        
        List<MntProdModule> moduleList = new ArrayList<>();
        int index = 0;
        for(List<String> rowList : excelData) {
            if(index == 0) {
                index++;
                continue;
            }
            
            MntProdModule module = new MntProdModule();
            module.setProdId(prodId);
            module.setVerCode(verCode);
            module.setIsUsed("1");
            module.setModuleNameEn(rowList.get(0));
            
            //数据存在校验
            if(StringUtils.isEmpty(rowList.get(0)) || StringUtils.isEmpty(rowList.get(1))) {
                throw new MntDataAccessException("第" + (index+1) + "行该产品模块名称为必填，请检查后重新导入！" );
            }
            
            //数据存在校验
            List<MntProdModule> parentModuleList = findProdModuleList(module);
            if(parentModuleList != null && parentModuleList.size() > 0) {
                throw new MntDataAccessException("第" + (index+1) + "行该产品模块已经存在，请检查后重新导入！" );
            }
            
            module.setModuleNameCn(rowList.get(1));
            module.setSvnPath(rowList.get(3));
            module.setModuleDesc(rowList.get(4));
            
            saveMntProdModule(module);
            moduleList.add(module);
            index++;
        }
        
        //更新上级模块字段
        for(int i=0; i<moduleList.size(); i++) {
            List<String> list = excelData.get(i + 1);
            String parantModule = list.get(2);
            if(StringUtils.isEmpty(parantModule)) {
                continue;
            }
            //查询上级模块ID
            MntProdModule parentProdModule = new MntProdModule();
            parentProdModule.setProdId(prodId);
            parentProdModule.setVerCode(verCode);
            parentProdModule.setModuleNameEn(parantModule);
            List<MntProdModule> parentModuleList = findProdModuleList(parentProdModule);
            if(parentModuleList == null || parentModuleList.size() == 0) {
                throw new MntDataAccessException("第" + (i+1) + "行上级模块在该产品对应版本中不存在，请检查后重新导入！" );
            }
            //更新上级模块字段
            MntProdModule module = new MntProdModule();
            module.setModuleId(moduleList.get(i).getModuleId());
            module.setParentModuleId(parentModuleList.get(0).getModuleId());
            updateMntProdById(module);
        }
    }
    
    @Override
    public void updateMntProdById(MntProdModule prodModule) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        prodModule.setModifier(currentUser.getUserName());
        prodModule.setModifyDate(new Date());
        mntProdModuleMapper.updateByPrimaryKey(prodModule);
        
    }

    @Override
    public void deleteMntProdByIds(String moduleIds) {
        
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntProdModule mntProdModule = new MntProdModule();
        mntProdModule.setDeleteFlag("1");
        mntProdModule.setModifier(currentUser.getUserName());
        mntProdModule.setModifyDate(new Date());
        String[] moduleIdAry = moduleIds.split(",");
        for(String moduleId : moduleIdAry) {
            mntProdModule.setModuleId(Integer.parseInt(moduleId));
            mntProdModuleMapper.updateByPrimaryKey(mntProdModule);
        }
    }
    

    private void cvtContentList(List<MntProdModule> prodModuleList) {
        for(MntProdModule mntProdModule : prodModuleList) {
            mntProdModule.setProdName(BaseDataCache.getDataName("PROD_INFO", mntProdModule.getProdId()));
            mntProdModule.setVerName(BaseDataCache.getDataName("PROD_VER", mntProdModule.getVerCode()));
            mntProdModule.setParentModuleName(BaseDataCache.getDataName("PROD_MODULE_ENUM", mntProdModule.getParentModuleId()));
            mntProdModule.setIsUsedTxt(BaseDataCache.getDataName("MODULE_IS_USED", mntProdModule.getIsUsed()));
            mntProdModule.setOutputTypeTxt(BaseDataCache.getDataName("MODULE_OUTPUT_TYPE", mntProdModule.getOutputType()));
            mntProdModule.setModuleName(mntProdModule.getModuleNameCn() + "(" +mntProdModule.getModuleNameEn() + ")");
        }
    }
}
