package com.ai.mnt.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.model.product.MntProdVersion;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.product.MntProdVersionMapper;
import com.ai.mnt.service.product.MntProdVerService;

@Service
public class MntProdVerServiceImpl implements MntProdVerService{
    
//    @Autowired
//    UserRealm userRealm;
    
    @Autowired
    MntProdVersionMapper mntProdVersionMapper;

    @Override
    public List<MntProdVersion> findAllProdVer() {
        List<MntProdVersion> prodVerList = mntProdVersionMapper.findAll();
        cvtContentList(prodVerList);
        return prodVerList;
    }

    @Override
    public void saveMntProdVer(MntProdVersion mntProdVer) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntProdVer.setCreator(currentUser.getUserName());
        mntProdVer.setCreateDate(new Date());
        mntProdVer.setModifier(currentUser.getUserName());
        mntProdVer.setModifyDate(new Date());
        mntProdVersionMapper.save(mntProdVer);
    }

    @Override
    public List<MntProdVersion> findProdVerListByPid(Integer prodId) {
        MntProdVersion mntProdVersion = new MntProdVersion();
        mntProdVersion.setProdId(prodId);
        mntProdVersion.setDeleteFlag("0");
        List<MntProdVersion> list = mntProdVersionMapper.findList(mntProdVersion);
        cvtContentList(list);
        return list;
    }
    
    private void cvtContentList(List<MntProdVersion> prodVerList) {
        for(MntProdVersion MntProdVersion : prodVerList) {
            MntProdVersion.setProdName(BaseDataCache.getDataName("PROD_INFO", MntProdVersion.getProdId()));
        }
    }

    @Override
    public void updateMntProdVer(MntProdVersion mntProdVer) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntProdVer.setModifier(currentUser.getUserName());
        mntProdVer.setModifyDate(new Date());
        mntProdVersionMapper.updateByPrimaryKey(mntProdVer);
    }

    @Override
    public void deleteMntProdVerById(Integer verId) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntProdVersion mntProdVer = new MntProdVersion();
        mntProdVer.setVerId(verId);
        mntProdVer.setModifier(currentUser.getUserName());
        mntProdVer.setModifyDate(new Date());
        mntProdVer.setDeleteFlag("1"); //已删除
        mntProdVersionMapper.deleteByPrimaryKey(verId);
    }

    @Override
    public MntProdVersion findProdVerListByVerId(Integer verId) {
        MntProdVersion prodVersion = mntProdVersionMapper.findByPrimaryKey(verId);
        return prodVersion;
    }

    @Override
    public List<MntProdVersion> findProdVerList(MntProdVersion mntProdVersion) {
        List<MntProdVersion> prodVerList = mntProdVersionMapper.findList(mntProdVersion);
        cvtContentList(prodVerList);
        return prodVerList;
    }
    
    
}
