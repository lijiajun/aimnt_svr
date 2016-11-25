package com.ai.mnt.persistence.product;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.mnt.model.product.MntProdProc;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntProdProcMapper 
 * @Description: MntProdProcMapper Persistence
 * @Author: Auto Generate 
 * @Date: 2016-4-28
 */
@Repository
public interface MntProdProcMapper extends CrudMapper<MntProdProc, Integer>{
    
    /**
     * 模块进程关联查询
     * @param mntProdProc
     * @return
     */
    public List<MntProdProc> findModuleAndProcJoinList(MntProdProc mntProdProc);

    /**
     * 根据模块获取对应的进程列表
     * @param moduleId
     */
    public List<MntProdProc> findProdProcListByModuleId(Integer moduleId);
    
    
}