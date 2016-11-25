package com.ai.mnt.persistence.product;


import org.springframework.stereotype.Repository;

import com.ai.mnt.model.product.MntLibRelation;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntLibRelationMapper 
 * @Description: MntLibRelationMapper Persistence
 * @Author: Auto Generate. HE
 * @Date: 2016-5-20
 */
@Repository
public interface MntLibRelationMapper extends CrudMapper<MntLibRelation, Integer>{
    
    public void deleteMntLibRelation(MntLibRelation mntLibRelation);
    
    
}