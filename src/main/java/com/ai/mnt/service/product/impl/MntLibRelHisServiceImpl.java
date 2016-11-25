package com.ai.mnt.service.product.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.model.product.MntLibBaseRel;
import com.ai.mnt.model.product.MntLibRelHis;
import com.ai.mnt.model.product.MntModuleLib;
import com.ai.mnt.model.product.MntProdModule;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.product.MntLibBaseRelMapper;
import com.ai.mnt.persistence.product.MntLibRelHisMapper;
import com.ai.mnt.service.product.MntLibRelHisService;

/**
 * @Title: MntLibRelHisServiceImpl 
 * @Description: MntLibRelHisServiceImpl ServiceImplPackage
 * @Author: Auto Generate. HE
 * @Date: 2016-5-16
 */
@Service
public class MntLibRelHisServiceImpl implements MntLibRelHisService{

    @Autowired
    MntLibRelHisMapper mntLibRelHisMapper;
    
    @Autowired
    MntLibBaseRelMapper mntLibBaseRelMapper;
    
//    @Autowired
//    UserRealm userRealm;
    
    /**
     * 获取所有库文件发布历史明细列表
     * @return List<MntLibRelHis>
     */
    @Override
    public List<MntLibRelHis> findAllMntLibRelHis() {
        List<MntLibRelHis> mntLibRelHisList = mntLibRelHisMapper.findAll();
        //cvtContentList(mntLibRelHisList);
        return mntLibRelHisList;
    }

    /**
     * 获取库文件发布历史明细安装点列表
     * @param product
     * @return List<MntLibRelHis>
     */
    @Override
    public List<MntLibBaseRel> findMntLibBaseRelList(MntLibBaseRel mntLibBaseRel) {
        List<MntLibBaseRel> mntLibBaseRels = mntLibBaseRelMapper.findList(mntLibBaseRel);
        //cvtContentList(mntLibRelHisList);
        return mntLibBaseRels;
    }

    /**
     * 获取库文件发布历史明细列表
     * @param product
     * @return List<MntLibRelHis>
     */
    @Override
    public List<MntLibRelHis> findMntLibRelHisList(MntLibRelHis mntLibRelHis) {
        List<MntLibRelHis> mntLibRelHisList = mntLibRelHisMapper.findList(mntLibRelHis);
        //cvtContentList(mntLibRelHisList);
        return mntLibRelHisList;
    }
    
    
    /**
     * 通过主键ID获取库文件发布历史明细
     * @param id Primary key
     * @return MntLibRelHis
     */
    @Override
    public MntLibRelHis findMntLibRelHisById(Integer id) {
        MntLibRelHis mntLibRelHis = mntLibRelHisMapper.findByPrimaryKey(id);
        return mntLibRelHis;
    }

    /**
     * 添加库文件发布历史明细
     * @param MntLibRelHis
     */
    @Override
    public void saveMntLibRelHis(MntLibRelHis mntLibRelHis) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntLibRelHis.setDeleteFlag("0");
        mntLibRelHis.setCreator(currentUser.getUserName());
        mntLibRelHis.setModifier(currentUser.getUserName());
        mntLibRelHis.setCreateDate(new Date());
        mntLibRelHis.setModifyDate(new Date());
        mntLibRelHisMapper.save(mntLibRelHis);
        
    }

    /**
     * 更新库文件发布历史明细
     * 根据MntLibRelHis的主键更新主键以外的其他字段
     * @param MntLibRelHis
     */
    @Override
    public void updateMntLibRelHisById(MntLibRelHis mntLibRelHis) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntLibRelHis.setModifier(currentUser.getUserName());
        mntLibRelHis.setModifyDate(new Date());
        mntLibRelHisMapper.updateByPrimaryKey(mntLibRelHis);
        
    }

    /**
     * 根据主键批量删除库文件发布历史明细 
     * 非物理删除 修改删除标识
     * @param id Primary key
     */
    @Override
    public void deleteMntLibRelHisByIds(String ids) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntLibRelHis mntLibRelHis = new MntLibRelHis();
        mntLibRelHis.setDeleteFlag("1");
        mntLibRelHis.setModifier(currentUser.getUserName());
        mntLibRelHis.setModifyDate(new Date());
        String[] idAry = ids.split(",");
        for(String id : idAry) {
            mntLibRelHis.setId(Integer.parseInt(id));
            mntLibRelHisMapper.updateByPrimaryKey(mntLibRelHis);
        }
    }

    @Override
    public List<MntLibRelHis> findLibRelHisJoinList(MntLibRelHis mntLibRelHis) {
        List<MntLibRelHis> relHisList = mntLibRelHisMapper.findLibRelHisJoinList(mntLibRelHis);
        cvtContentList(relHisList);
        return relHisList;
    }
    
    private void cvtContentList(List<MntLibRelHis> MntLibRelHisList) {
        for(MntLibRelHis mntLibRelHis : MntLibRelHisList) {
            
            MntModuleLib mntModuleLib = mntLibRelHis.getMntModuleLib();
            MntProdModule mntProdModule = mntModuleLib.getMntProdModule();
            mntLibRelHis.setBaseIdTxt(BaseDataCache.getDataName("BASE_NAME_ENUM",  mntLibRelHis.getBaseId()));
            mntProdModule.setProdName(BaseDataCache.getDataName("PROD_INFO",  mntProdModule.getProdId()));
            mntProdModule.setVerName(BaseDataCache.getDataName("PROD_VER",  mntProdModule.getVerCode()));
            mntProdModule.setModuleName(mntProdModule.getModuleNameCn() + "(" + mntProdModule.getModuleNameEn() +")");
        }
    }

    /**
     * 添加库文件发布历史明细以及相关安装点
     * @param MntLibRelHis
     */
    @Override
    public void saveMntLibRelHisAndBaseId(MntLibRelHis mntLibRelHis,
            String[] baseIds) {
        saveMntLibRelHis(mntLibRelHis);
//        List<MntLibBaseRel> mntLibBaseRels = new ArrayList<>();
//        for(String baseId : baseIds) {
//            MntLibBaseRel mntLibBaseRel = new MntLibBaseRel();
//            SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
//            mntLibBaseRel.setDeleteFlag("0");
//            mntLibBaseRel.setCreator(currentUser.getUserName());
//            mntLibBaseRel.setModifier(currentUser.getUserName());
//            mntLibBaseRel.setCreateDate(new Date());
//            mntLibBaseRel.setModifyDate(new Date());
//            mntLibBaseRel.setBaseId(Integer.parseInt(baseId));
//            mntLibBaseRel.setLibRelHisId(mntLibRelHis.getId());
//            mntLibBaseRels.add(mntLibBaseRel);
//        }
//        if(mntLibBaseRels.size() > 0) {
//            mntLibBaseRelMapper.saveAll(mntLibBaseRels);
//        }
    }

    /**
     * 获取历史明细的安装点关联
     * @param MntLibRelHis
     */
    @Override
    public List<MntLibBaseRel> getLibAndBaseRelList(MntLibBaseRel mntLibBaseRel) {
        
        return mntLibBaseRelMapper.findList(mntLibBaseRel);
        
    }

    /**
     * 更新库文件发布历史明细以及相关安装点
     * @param MntLibRelHis
     * @param baseIds
     */
    @Override
    public void updateMntLibRelHisAndInstBase(MntLibRelHis mntLibRelHis,
            String[] baseIds) {
        updateMntLibRelHisById(mntLibRelHis);
//        if(baseIds != null && baseIds.length > 0) {
//            //先删除该条明细的所对应的安装点
//            MntLibBaseRel temp = new MntLibBaseRel();
//            temp.setLibRelHisId(mntLibRelHis.getId());
//            mntLibBaseRelMapper.deleteMntLibBaseRel(temp);
//            //插入新的安装点
//            for(String baseId : baseIds) {
//                MntLibBaseRel mntLibBaseRel = new MntLibBaseRel();
//                SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
//                mntLibBaseRel.setDeleteFlag("0");
//                mntLibBaseRel.setCreator(currentUser.getUserName());
//                mntLibBaseRel.setModifier(currentUser.getUserName());
//                mntLibBaseRel.setCreateDate(new Date());
//                mntLibBaseRel.setModifyDate(new Date());
//                mntLibBaseRel.setBaseId(Integer.parseInt(baseId));
//                mntLibBaseRel.setLibRelHisId(mntLibRelHis.getId());
//                mntLibBaseRelMapper.save(mntLibBaseRel);
//            }
//        }
    }
}

