package com.ai.mnt.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.model.product.MntModuleProcRelation;
import com.ai.mnt.model.product.MntProdModule;
import com.ai.mnt.model.product.MntProdProc;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.product.MntModuleProcRelationMapper;
import com.ai.mnt.persistence.product.MntProdProcMapper;
import com.ai.mnt.service.product.MntProdProcService;

@Service
public class MntProdProcServiceImpl implements MntProdProcService{

    @Autowired
    MntProdProcMapper mntProdProcMapper;

    @Autowired
    MntModuleProcRelationMapper mntModuleProcRelationMapper;
    
//    @Autowired
//    UserRealm userRealm;
    
    @Override
    public List<MntProdProc> findAllProdProc() {
        List<MntProdProc> mntProdProcList = mntProdProcMapper.findAll();
        return mntProdProcList;
    }

    @Override
    public List<MntProdProc> findProdProcList(MntProdProc mntProdProc) {
        List<MntProdProc> mntProdProcList = mntProdProcMapper.findList(mntProdProc);
        return mntProdProcList;
    }

    /**
     * 模块进程关联查询
     * @param mntProdProc
     * @return
     */
    @Override
    public List<MntProdProc> findModuleAndProcJoinList(MntProdProc mntProdProc) {
        List<MntProdProc> mntProdProcList = mntProdProcMapper.findModuleAndProcJoinList(mntProdProc);
        cvtContentList(mntProdProcList);
        return mntProdProcList;
    }
    
    @Override
    public MntProdProc findProdProcById(Integer procId) {
        MntProdProc mntProdProc = mntProdProcMapper.findByPrimaryKey(procId);
        return mntProdProc;
    }

    @Override
    public void saveMntProdProc(MntProdProc mntProdProc) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntProdProc.setDeleteFlag("0");
        mntProdProc.setCreator(currentUser.getUserName());
        mntProdProc.setModifier(currentUser.getUserName());
        mntProdProc.setCreateDate(new Date());
        mntProdProc.setModifyDate(new Date());
        mntProdProcMapper.save(mntProdProc);
    }

    @Override
    public void updateMntProdProcById(MntProdProc mntProdProc) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntProdProc.setModifier(currentUser.getUserName());
        mntProdProc.setModifyDate(new Date());
        mntProdProcMapper.updateByPrimaryKey(mntProdProc);
    }

    @Override
    public void deleteMntProdProcByIds(String procIds) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntProdProc mntProdProc = new MntProdProc();
        mntProdProc.setDeleteFlag("1");
        mntProdProc.setModifier(currentUser.getUserName());
        mntProdProc.setModifyDate(new Date());
        String[] procIdAry = procIds.split(",");
        for(String procId : procIdAry) {
            mntProdProc.setProcId(Integer.parseInt(procId));
            mntProdProcMapper.updateByPrimaryKey(mntProdProc);
        }
    }
    
    private void cvtContentList(List<MntProdProc> prodProcList) {
        for(MntProdProc mntProdProc : prodProcList) {
            List<MntProdModule> mntProdModuleList = mntProdProc.getMntProdModuleList();
            String moduleName = "";
            for(int i=0; i<mntProdModuleList.size(); i++) {
                MntProdModule mntProdModule = mntProdModuleList.get(i);
                if(i == 0) {
                    mntProdProc.setMntProdModule(mntProdModule);
                    mntProdModule.setProdName(BaseDataCache.getDataName("PROD_INFO", mntProdModule.getProdId()));
                    mntProdModule.setVerName(BaseDataCache.getDataName("PROD_VER", mntProdModule.getVerCode()));
                    
                }
                moduleName += "," + mntProdModule.getModuleNameCn() + "(" +mntProdModule.getModuleNameEn() + ")";
            }
            mntProdProc.getMntProdModule().setModuleName(moduleName.substring(1));
            mntProdProc.setProcName(mntProdProc.getProcNameCn() + "(" +mntProdProc.getProcNameEn() + ")");
        }
    }

    /**
     * 多模块添加进程
     * @param mntReleaseRecDtl
     * @param moduleIds
     */
    @Override
    public void saveModuleProcWithModuleIds(MntProdProc mntProdProc, String[] moduleIds) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntProdProc.setDeleteFlag("0");
        mntProdProc.setCreator(currentUser.getUserName());
        mntProdProc.setModifier(currentUser.getUserName());
        mntProdProc.setCreateDate(new Date());
        mntProdProc.setModifyDate(new Date());
        //保存进程信息
        mntProdProcMapper.save(mntProdProc);
      //保存模块与进程关联信息
        for(String moduleId : moduleIds) {
            MntModuleProcRelation mntModuleProcRelation = new MntModuleProcRelation();
            mntModuleProcRelation.setModuleId(Integer.parseInt(moduleId));
            mntModuleProcRelation.setProcId(mntProdProc.getProcId());
            mntModuleProcRelationMapper.save(mntModuleProcRelation);
        }
    }

    /**
     * 多模块更新进程
     * @param mntReleaseRecDtl
     * @param moduleIds
     */
    @Override
    public void updateModuleProcWithModuleIds(MntProdProc mntProdProc,
            String[] moduleIds) {
        
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntProdProc.setModifier(currentUser.getUserName());
        mntProdProc.setModifyDate(new Date());
        //保存进程信息
        mntProdProcMapper.updateByPrimaryKey(mntProdProc);
        //更新模块与进程关联信息  先删后插
        MntModuleProcRelation mntModuleProcRelation = new MntModuleProcRelation();
        mntModuleProcRelation.setProcId(mntProdProc.getProcId());
        mntModuleProcRelationMapper.deleteModuleProcRelation(mntModuleProcRelation);
        for(String moduleId : moduleIds) {
            mntModuleProcRelation = new MntModuleProcRelation();
            mntModuleProcRelation.setModuleId(Integer.parseInt(moduleId));
            mntModuleProcRelation.setProcId(mntProdProc.getProcId());
            mntModuleProcRelationMapper.save(mntModuleProcRelation);
        }
        
    }

    /**
     * 获取模块进程关系
     * @param moduleProcRelation
     */
    @Override
    public List<MntModuleProcRelation> getModuleProcRelationList(
            MntModuleProcRelation moduleProcRelation) {
        List<MntModuleProcRelation> relationList = mntModuleProcRelationMapper.findList(moduleProcRelation);
        return relationList;
    }

    /**
     * 根据模块获取对应的进程列表
     * @param moduleId
     */
    @Override
    public List<MntProdProc> findProdProcListByModuleId(Integer moduleId) {
        List<MntProdProc> prodProcList = mntProdProcMapper.findProdProcListByModuleId(moduleId);
        return prodProcList;
    }

}
