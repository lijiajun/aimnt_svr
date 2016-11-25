package com.ai.mnt.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.model.product.MntProd;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.product.MntProdMapper;
import com.ai.mnt.service.product.MntProdService;

@Service
public class MntProdServiceImpl implements MntProdService{
    
    @Autowired
    MntProdMapper mntProdMapper;
    
//    @Autowired
//    UserRealm userRealm;
    
    @Override
    public List<MntProd> findAllMntProd() {
        List<MntProd> mntProds = mntProdMapper.findAll();
        return mntProds;
    }
    
    @Override
    public void saveMntProd(MntProd mntProd) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntProd.setDeleteFlag("0");
        mntProd.setProdType(1);
        mntProd.setCreator(currentUser.getUserName());
        mntProd.setModifier(currentUser.getUserName());
        mntProd.setCreateDate(new Date());
        mntProd.setModifyDate(new Date());
        mntProdMapper.save(mntProd);
    }

    @Override
    public MntProd findMntProdById(Integer pid) {
        return mntProdMapper.findByPrimaryKey(pid);
    }
    
    @Override
    public void updateMntProdById(MntProd mntProd) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntProd.setModifier(currentUser.getUserName());
        mntProd.setModifyDate(new Date());
        mntProdMapper.updateByPrimaryKey(mntProd);
    }
    
    @Override
    public void deleteMntProdById(Integer pid) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntProd mntProd = new MntProd();
        mntProd.setProdId(pid);
        mntProd.setModifier(currentUser.getUserName());
        mntProd.setModifyDate(new Date());
        mntProd.setDeleteFlag("1"); //已删除
        mntProdMapper.updateByPrimaryKey(mntProd);
    }

    @Override
    public List<MntProd> findMntProdList(MntProd mntProd) {
        List<MntProd> list = mntProdMapper.findList(mntProd);
        return list;
    }
}
