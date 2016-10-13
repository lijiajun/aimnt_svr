package com.ai.mnt.service.inst.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.model.inst.MntProdBaseRel;
import com.ai.mnt.persistence.inst.MntProdBaseRelMapper;
import com.ai.mnt.service.inst.MntProdBaseRelService;

/**
 * @Title: MntProdBaseRelServiceImpl 
 * @Description: MntProdBaseRelServiceImpl ServiceImplPackage
 * @Author: Auto Generate.
 * @Date: 2016-10-13
 */
@Service
public class MntProdBaseRelServiceImpl implements MntProdBaseRelService{

    @Autowired
    MntProdBaseRelMapper mntProdBaseRelMapper;
    
    /**
     * 获取所有产品安装点映射列表
     * @return List<MntProdBaseRel>
     */
    @Override
    public List<MntProdBaseRel> findAllMntProdBaseRel() {
        List<MntProdBaseRel> mntProdBaseRelList = mntProdBaseRelMapper.findAll();
        //cvtContentList(mntProdBaseRelList);
        return mntProdBaseRelList;
    }

    /**
     * 获取产品安装点映射列表
     * @param inst
     * @return List<MntProdBaseRel>
     */
    @Override
    public List<MntProdBaseRel> findMntProdBaseRelList(MntProdBaseRel mntProdBaseRel) {
        List<MntProdBaseRel> mntProdBaseRelList = mntProdBaseRelMapper.findList(mntProdBaseRel);
        //cvtContentList(mntProdBaseRelList);
        return mntProdBaseRelList;
    }

    /**
     * 通过主键ID获取产品安装点映射
     * @param id Primary key
     * @return MntProdBaseRel
     */
    @Override
    public MntProdBaseRel findMntProdBaseRelById(Integer id) {
        MntProdBaseRel mntProdBaseRel = mntProdBaseRelMapper.findByPrimaryKey(id);
        return mntProdBaseRel;
    }

    /**
     * 添加产品安装点映射
     * @param MntProdBaseRel
     */
    @Override
    public void saveMntProdBaseRel(MntProdBaseRel mntProdBaseRel) {
        mntProdBaseRelMapper.save(mntProdBaseRel);
        
    }

    /**
     * 更新产品安装点映射
     * 根据MntProdBaseRel的主键更新主键以外的其他字段
     * @param MntProdBaseRel
     */
    @Override
    public void updateMntProdBaseRelById(MntProdBaseRel mntProdBaseRel) {
        mntProdBaseRelMapper.updateByPrimaryKey(mntProdBaseRel);
        
    }

    /**
     * 根据主键批量删除产品安装点映射 
     * 非物理删除 修改删除标识
     * @param id Primary key
     */
    @Override
    public void deleteMntProdBaseRelByIds(String ids) {
        MntProdBaseRel mntProdBaseRel = new MntProdBaseRel();
        String[] idAry = ids.split(",");
        for(String id : idAry) {
            mntProdBaseRel.setId(Integer.parseInt(id));
            mntProdBaseRelMapper.updateByPrimaryKey(mntProdBaseRel);
        }
    }

    @Override
    public List<MntProdBaseRel> findProdJoinBaseInfoList() {
        List<MntProdBaseRel> mntProdBaseRelList = mntProdBaseRelMapper.findProdJoinBaseInfoList();
        //cvtContentList(mntProdBaseRelList);
        return mntProdBaseRelList;
    }
    
    //private void cvtContentList(List<MntProdBaseRel> MntProdBaseRelList) {
    //    for(MntProdBaseRel mntProdBaseRel : MntProdBaseRelList) {
    //    }
    //}
}

