package com.ai.mnt.service.product.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.exception.MntDataAccessException;
import com.ai.mnt.model.product.MntLibRelation;
import com.ai.mnt.model.product.MntModuleLib;
import com.ai.mnt.model.product.MntProdModule;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.product.MntLibRelationMapper;
import com.ai.mnt.persistence.product.MntModuleLibMapper;
import com.ai.mnt.persistence.product.MntProdModuleMapper;
import com.ai.mnt.service.product.MntModuleLibService;

/**
 * @Title: MntModuleLibServiceImpl 
 * @Description: MntModuleLibServiceImpl ServiceImplPackage
 * @Author: Auto Generate. HE
 * @Date: 2016-5-12
 */
@Service
public class MntModuleLibServiceImpl implements MntModuleLibService{

    @Autowired
    MntModuleLibMapper mntModuleLibMapper;
    
//    @Autowired
//    UserRealm userRealm;
    
    @Autowired
    MntLibRelationMapper mntLibRelationMapper;
    
    @Autowired
    MntProdModuleMapper mntProdModuleMapper;
    
    /**
     * 获取所有库文件信息列表
     * @return List<MntModuleLib>
     */
    @Override
    public List<MntModuleLib> findAllMntModuleLib() {
        List<MntModuleLib> mntModuleLibList = mntModuleLibMapper.findAll();
        //cvtContentList(mntModuleLibList);
        return mntModuleLibList;
    }

    /**
     * 获取库文件信息列表
     * @param product
     * @return List<MntModuleLib>
     */
    @Override
    public List<MntModuleLib> findMntModuleLibList(MntModuleLib mntModuleLib) {
        List<MntModuleLib> mntModuleLibList = mntModuleLibMapper.findList(mntModuleLib);
        //cvtContentList(mntModuleLibList);
        return mntModuleLibList;
    }

    
    /**
     * 获取库文件信息列表
     * @param product
     * @return List<MntModuleLib>
     */
    @Override
    public List<MntModuleLib> findModuleLibJoinModule(MntModuleLib mntModuleLib) {
        List<MntModuleLib> mntModuleLibList = mntModuleLibMapper.findModuleLibJoinModule(mntModuleLib);
        cvtContentList(mntModuleLibList);
        return mntModuleLibList;
    }
    
    /**
     * 通过主键ID获取库文件信息
     * @param libId Primary key
     * @return MntModuleLib
     */
    @Override
    public MntModuleLib findMntModuleLibByLibId(Integer libId) {
        MntModuleLib mntModuleLib = mntModuleLibMapper.findByPrimaryKey(libId);
        return mntModuleLib;
    }

    /**
     * 添加库文件信息
     * @param MntModuleLib
     */
    @Override
    public void saveMntModuleLib(MntModuleLib mntModuleLib) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntModuleLib.setDeleteFlag("0");
        mntModuleLib.setCreator(currentUser.getUserName());
        mntModuleLib.setModifier(currentUser.getUserName());
        mntModuleLib.setCreateDate(new Date());
        mntModuleLib.setModifyDate(new Date());
        mntModuleLibMapper.save(mntModuleLib);
        
        //保存关联Lib
        List<MntLibRelation> mntLibRelations = mntModuleLib.getMntLibRelations();
        if(mntLibRelations != null && mntLibRelations.size() > 0) {
            for(MntLibRelation mntLibRelation : mntLibRelations) {
                mntLibRelation.setDeleteFlag("0");
                mntLibRelation.setCreator(currentUser.getUserName());
                mntLibRelation.setModifier(currentUser.getUserName());
                mntLibRelation.setCreateDate(new Date());
                mntLibRelation.setModifyDate(new Date());
                mntLibRelation.setLibId(mntModuleLib.getLibId());
                mntLibRelationMapper.save(mntLibRelation);
            }
        }
    }

    /**
     * 更新库文件信息
     * 根据MntModuleLib的主键更新主键以外的其他字段
     * @param MntModuleLib
     */
    @Override
    public void updateMntModuleLibByLibId(MntModuleLib mntModuleLib) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntModuleLib.setModifier(currentUser.getUserName());
        mntModuleLib.setModifyDate(new Date());
        mntModuleLibMapper.updateByPrimaryKey(mntModuleLib);
        
        //更新关联库
        //先删除原有关联
        MntLibRelation temp = new MntLibRelation();
        temp.setLibId(mntModuleLib.getLibId());
        mntLibRelationMapper.deleteMntLibRelation(temp);
        //再插入新有的关联
        //保存关联Lib
        List<MntLibRelation> mntLibRelations = mntModuleLib.getMntLibRelations();
        if(mntLibRelations != null && mntLibRelations.size() > 0) {
            for(MntLibRelation mntLibRelation : mntLibRelations) {
                mntLibRelation.setDeleteFlag("0");
                mntLibRelation.setCreator(currentUser.getUserName());
                mntLibRelation.setModifier(currentUser.getUserName());
                mntLibRelation.setCreateDate(new Date());
                mntLibRelation.setModifyDate(new Date());
                mntLibRelation.setLibId(mntModuleLib.getLibId());
                mntLibRelationMapper.save(mntLibRelation);
            }
        }
    }

    /**
     * 根据主键批量删除库文件信息 
     * 非物理删除 修改删除标识
     * @param libId Primary key
     */
    @Override
    public void deleteMntModuleLibByLibIds(String libIds) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntModuleLib mntModuleLib = new MntModuleLib();
        mntModuleLib.setDeleteFlag("1");
        mntModuleLib.setModifier(currentUser.getUserName());
        mntModuleLib.setModifyDate(new Date());
        String[] libIdAry = libIds.split(",");
        for(String libId : libIdAry) {
            mntModuleLib.setLibId(Integer.parseInt(libId));
            mntModuleLibMapper.updateByPrimaryKey(mntModuleLib);
        }
    }
    
    private void cvtContentList(List<MntModuleLib> MntModuleLibList) {
        for(MntModuleLib mntModuleLib : MntModuleLibList) {
            mntModuleLib.setModuleName(BaseDataCache.getDataName("PROD_MODULE_ENUM", mntModuleLib.getModuleId()));
            mntModuleLib.setIsUsedTxt(BaseDataCache.getDataName("MODULE_IS_USED", mntModuleLib.getIsUsed()));
            mntModuleLib.setLibTypeTxt(BaseDataCache.getDataName("LIB_TYPE", mntModuleLib.getLibType()));
            MntProdModule mntProdModule = mntModuleLib.getMntProdModule();
            mntProdModule.setProdName(BaseDataCache.getDataName("PROD_INFO", mntProdModule.getProdId()));
            mntProdModule.setVerName(BaseDataCache.getDataName("PROD_VER", mntProdModule.getVerCode()));
        }
    }
    
    
    /**
     * 获取库文件关联信息列表
     * @param product
     * @return List<MntModuleLib>
     */
    @Override
    public List<MntLibRelation> findMntLibRelationList(MntLibRelation mntLibRelation) {
        List<MntLibRelation> mntLibRelations = mntLibRelationMapper.findList(mntLibRelation);
        return mntLibRelations;
    }

    /**
     * 保存库文件导入信息
     * @param excelData
     * @param prodId
     * @param verCode
     * @return 
     */
    @Override
    public void saveLibImportData(List<List<String>> excelData, Integer prodId,
            String verCode) throws MntDataAccessException {
        
        List<MntModuleLib> mntModuleLibList = new ArrayList<>();
        for(int i=1; i<excelData.size(); i++) {
            MntModuleLib mntModuleLib = new MntModuleLib();
            
            List<String> cellDataList = excelData.get(i);
            String libName = cellDataList.get(0);//库名称
            String libType = cellDataList.get(1);//类型
            String moduleName = cellDataList.get(2);//所属模块
            String libDesc = cellDataList.get(3);//库说明描述
            
            //数据存在校验
            if(StringUtils.isEmpty(libName) || StringUtils.isEmpty(libType) || StringUtils.isEmpty(moduleName)) {
                throw new MntDataAccessException("第" + i + "行必填项不能为空，请检查后重新导入！" );
            }
            
            MntProdModule mntProdModule = new MntProdModule();
            mntProdModule.setModuleNameEn(moduleName);
            mntProdModule.setProdId(prodId);
            mntProdModule.setVerCode(verCode);
            List<MntProdModule> mntProdModuleList = mntProdModuleMapper.findList(mntProdModule);
            if(mntProdModuleList == null || mntProdModuleList.size() == 0) {
                throw new MntDataAccessException("第" + i + "行模块在该产品对应版本中不存在，请检查后重新导入,或先添加该模块！" );
            }
            
            mntModuleLib.setLibName(libName);
            if("LIB".equals(libType)) {
                mntModuleLib.setLibType("1");
            }else if("BIN".equals(libType)) {
                mntModuleLib.setLibType("2");
            }else if("SVR".equals(libType)) {
                mntModuleLib.setLibType("3");
            }else {
                //mntModuleLib.setLibType("4");
                throw new MntDataAccessException("第" + i + "行库类型有误，请检查后重新导入！" );
            }
            mntModuleLib.setLibDesc(libDesc);
            mntModuleLib.setModuleId(mntProdModuleList.get(0).getModuleId());
            
            SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
            mntModuleLib.setIsUsed("1");
            mntModuleLib.setDeleteFlag("0");
            mntModuleLib.setCreator(currentUser.getUserName());
            mntModuleLib.setModifier(currentUser.getUserName());
            mntModuleLib.setCreateDate(new Date());
            mntModuleLib.setModifyDate(new Date());
            
            mntModuleLibList.add(mntModuleLib);
        }
        mntModuleLibMapper.saveAll(mntModuleLibList);
    }
}

