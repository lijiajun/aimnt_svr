package com.ai.mnt.persistence.product;


import org.springframework.stereotype.Repository;

import com.ai.mnt.model.product.MntModuleProcRelation;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntModuleProcRelationMapper 
 * @Description: MntModuleProcRelationMapper Persistence
 * @Author: Auto Generate 
 * @Date: 2016-5-8
 */
@Repository
public interface MntModuleProcRelationMapper extends CrudMapper<MntModuleProcRelation, Integer>{
    
    public void deleteModuleProcRelation(MntModuleProcRelation mntModuleProcRelation);
    
}