package com.ai.mnt.service.product.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.exception.MntDataAccessException;
import com.ai.mnt.model.product.MntLibRelationExt;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.product.MntLibRelationExtMapper;
import com.ai.mnt.service.product.MntLibRelationExtService;

/**
 * @Title: MntLibRelationExtServiceImpl 
 * @Description: MntLibRelationExtServiceImpl ServiceImplPackage
 * @Author: Auto Generate.
 * @Date: 2016-7-11
 */
@Service
public class MntLibRelationExtServiceImpl implements MntLibRelationExtService{

    @Autowired
    MntLibRelationExtMapper mntLibRelationExtMapper;
    
//    @Autowired
//    UserRealm userRealm;
    
    /**
     * 获取所有库文件扩展关联信息列表
     * @return List<MntLibRelationExt>
     */
    @Override
    public List<MntLibRelationExt> findAllMntLibRelationExt() {
        List<MntLibRelationExt> mntLibRelationExtList = mntLibRelationExtMapper.findAll();
        //cvtContentList(mntLibRelationExtList);
        return mntLibRelationExtList;
    }

    /**
     * 获取库文件扩展关联信息列表
     * @param lib
     * @return List<MntLibRelationExt>
     */
    @Override
    public List<MntLibRelationExt> findMntLibRelationExtList(MntLibRelationExt mntLibRelationExt) {
        List<MntLibRelationExt> mntLibRelationExtList = mntLibRelationExtMapper.findList(mntLibRelationExt);
        cvtContentList(mntLibRelationExtList);
        return mntLibRelationExtList;
    }

    /**
     * 通过主键ID获取库文件扩展关联信息
     * @param id Primary key
     * @return MntLibRelationExt
     */
    @Override
    public MntLibRelationExt findMntLibRelationExtById(Integer id) {
        MntLibRelationExt mntLibRelationExt = mntLibRelationExtMapper.findByPrimaryKey(id);
        return mntLibRelationExt;
    }

    /**
     * 添加库文件扩展关联信息
     * @param MntLibRelationExt
     */
    @Override
    public void saveMntLibRelationExt(MntLibRelationExt mntLibRelationExt) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntLibRelationExt.setDeleteFlag("0");
        mntLibRelationExt.setCreator(currentUser.getUserName());
        mntLibRelationExt.setModifier(currentUser.getUserName());
        mntLibRelationExt.setCreateDate(new Date());
        mntLibRelationExt.setModifyDate(new Date());
        mntLibRelationExtMapper.save(mntLibRelationExt);
        
    }

    /**
     * 更新库文件扩展关联信息
     * 根据MntLibRelationExt的主键更新主键以外的其他字段
     * @param MntLibRelationExt
     */
    @Override
    public void updateMntLibRelationExtById(MntLibRelationExt mntLibRelationExt) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntLibRelationExt.setModifier(currentUser.getUserName());
        mntLibRelationExt.setModifyDate(new Date());
        mntLibRelationExtMapper.updateByPrimaryKey(mntLibRelationExt);
        
    }

    /**
     * 根据主键批量删除库文件扩展关联信息 
     * 非物理删除 修改删除标识
     * @param id Primary key
     */
    @Override
    public void deleteMntLibRelationExtByIds(String ids) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntLibRelationExt mntLibRelationExt = new MntLibRelationExt();
        mntLibRelationExt.setDeleteFlag("1");
        mntLibRelationExt.setModifier(currentUser.getUserName());
        mntLibRelationExt.setModifyDate(new Date());
        String[] idAry = ids.split(",");
        for(String id : idAry) {
            mntLibRelationExt.setId(Integer.parseInt(id));
            mntLibRelationExtMapper.updateByPrimaryKey(mntLibRelationExt);
        }
    }

    
    @Override
    public void saveLibRelationImportData(List<List<String>> excelData,
            Integer prodId, String verCode) throws MntDataAccessException {
        
        List<MntLibRelationExt> mntLibRelationExts = new ArrayList<>();
        int index = 0;
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        for(List<String> rowList : excelData) {
            if(index == 0) {
                index++;
                continue;
            }
            
            MntLibRelationExt mntLibRelationExt = new MntLibRelationExt();
            mntLibRelationExt.setLibName(rowList.get(0));
            mntLibRelationExt.setRelLibName(rowList.get(1));
            mntLibRelationExt.setRelDesc(rowList.get(2));
            
            mntLibRelationExt.setProdId(prodId);
            mntLibRelationExt.setVerCode(verCode);
            
            
            mntLibRelationExt.setDeleteFlag("0");
            mntLibRelationExt.setCreator(currentUser.getUserName());
            mntLibRelationExt.setModifier(currentUser.getUserName());
            mntLibRelationExt.setCreateDate(new Date());
            mntLibRelationExt.setModifyDate(new Date());
            
            mntLibRelationExts.add(mntLibRelationExt);
        }
        
        if(mntLibRelationExts.size() > 0) {
            mntLibRelationExtMapper.saveAll(mntLibRelationExts);
        }
        
        
    }
    
    private void cvtContentList(List<MntLibRelationExt> mntLibRelationExtList) {
        for(MntLibRelationExt mntLibRelationExt : mntLibRelationExtList) {
            mntLibRelationExt.setProdName(BaseDataCache.getDataName("PROD_INFO", mntLibRelationExt.getProdId()));
        }
    }
    
}

