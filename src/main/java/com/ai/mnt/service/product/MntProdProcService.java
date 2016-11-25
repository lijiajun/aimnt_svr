package com.ai.mnt.service.product;

import java.util.List;

import com.ai.mnt.model.product.MntModuleProcRelation;
import com.ai.mnt.model.product.MntProdProc;

public interface MntProdProcService {
    
    /**
     * 获取全部进程列表
     * @return
     */
    public List<MntProdProc> findAllProdProc();
    
    
    /**
     * 获取进程列表
     * @return
     */
    public List<MntProdProc> findProdProcList(MntProdProc mntProdProc);
    
    /**
     * 模块进程关联查询
     * @param mntProdProc
     * @return
     */
    public List<MntProdProc> findModuleAndProcJoinList(MntProdProc mntProdProc);
    
    /**
     * 通过ID获取进程信息
     * @return
     */
    public MntProdProc findProdProcById(Integer procId);
    
    /**
     * 添加进程
     * @return
     */
    public void saveMntProdProc(MntProdProc mntProdProc);
    
    /**
     * 修改进程
     * @return
     */
    public void updateMntProdProcById(MntProdProc mntProdProc);
    
    /**
     * 删除进程  只是修改删除标识  非物理删除
     * @return
     */
    public void deleteMntProdProcByIds(String procIds);

    /**
     * 多模块添加进程
     * @param mntReleaseRecDtl
     * @param moduleIds
     */
    public void saveModuleProcWithModuleIds(MntProdProc mntProdProc,
            String[] moduleIds);

    /**
     * 多模块更新进程
     * @param mntReleaseRecDtl
     * @param moduleIds
     */
    public void updateModuleProcWithModuleIds(MntProdProc mntProdProc,
            String[] moduleIds);

    /**
     * 获取模块进程关系
     * @param prodId
     */
    public List<MntModuleProcRelation> getModuleProcRelationList(MntModuleProcRelation moduleProcRelation);


    /**
     * 根据模块获取对应的进程列表
     * @param moduleId
     */
    public List<MntProdProc> findProdProcListByModuleId(Integer moduleId);
}
