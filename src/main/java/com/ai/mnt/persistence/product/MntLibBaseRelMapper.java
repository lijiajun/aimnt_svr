package com.ai.mnt.persistence.product;


import org.springframework.stereotype.Repository;

import com.ai.mnt.model.product.MntLibBaseRel;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntLibBaseRelMapper 
 * @Description: MntLibBaseRelMapper Persistence
 * @Author: Auto Generate. HE
 * @Date: 2016-5-19
 */
@Repository
public interface MntLibBaseRelMapper extends CrudMapper<MntLibBaseRel, Integer>{
    
    public void deleteMntLibBaseRel(MntLibBaseRel mntLibBaseRel);
    
}