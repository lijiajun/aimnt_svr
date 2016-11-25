package com.ai.mnt.persistence.product;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.mnt.model.product.MntModuleLib;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntModuleLibMapper 
 * @Description: MntModuleLibMapper Persistence
 * @Author: Auto Generate. HE
 * @Date: 2016-5-12
 */
@Repository
public interface MntModuleLibMapper extends CrudMapper<MntModuleLib, Integer>{
    
    
    public List<MntModuleLib> findModuleLibJoinModule(MntModuleLib mntModuleLib);
    
}