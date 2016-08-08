package com.ai.mnt.service.task;

import java.util.List;

import com.ai.mnt.model.task.MntProcessTaskFact;

/**
 * @Title: MntProcessTaskFactService 
 * @Description: MntProcessTaskFactService ServicePackage
 * @Author: Auto Generate.
 * @Date: 2016-8-4
 */

public interface MntProcessTaskFactService {
    
    /**
     * 获取所有进程任务执行情况列表
     * @return List<MntProcessTaskFact>
     */
    public List<MntProcessTaskFact> findAllMntProcessTaskFact();
    
    
    /**
     * 获取进程任务执行情况列表
     * @param inst
     * @return List<MntProcessTaskFact>
     */
    public List<MntProcessTaskFact> findMntProcessTaskFactList(MntProcessTaskFact mntProcessTaskFact);
    
    /**
     * 通过主键ID获取进程任务执行情况
     * @param id Primary key
     * @return MntProcessTaskFact
     */
    public MntProcessTaskFact findMntProcessTaskFactById(Integer id);
    
    /**
     * 添加进程任务执行情况
     * @param MntProcessTaskFact
     */
    public void saveMntProcessTaskFact(MntProcessTaskFact mntProcessTaskFact);
    
    /**
     * 更新进程任务执行情况
     * 根据MntProcessTaskFact的主键更新主键以外的其他字段
     * @param MntProcessTaskFact
     */
    public void updateMntProcessTaskFactById(MntProcessTaskFact mntProcessTaskFact);
    
    /**
     * 根据主键批量删除进程任务执行情况 
     * 非物理删除 修改删除标识
     * @param id Primary key
     */
    public void deleteMntProcessTaskFactByIds(String ids);
}
