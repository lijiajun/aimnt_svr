package com.ai.mnt.persistence.inst;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.mnt.model.inst.MntProdBaseRel;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntProdBaseRelMapper 
 * @Description: MntProdBaseRelMapper Persistence
 * @Author: Auto Generate. HE
 * @Date: 2016-10-13
 */
@Repository
public interface MntProdBaseRelMapper extends CrudMapper<MntProdBaseRel, Integer>{
    
    
    public List<MntProdBaseRel> findProdJoinBaseInfoList();
    
}