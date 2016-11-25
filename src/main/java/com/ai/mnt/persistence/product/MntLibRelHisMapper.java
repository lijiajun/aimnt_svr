package com.ai.mnt.persistence.product;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.mnt.model.product.MntLibRelHis;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntLibRelHisMapper 
 * @Description: MntLibRelHisMapper Persistence
 * @Author: Auto Generate. HE
 * @Date: 2016-5-16
 */
@Repository
public interface MntLibRelHisMapper extends CrudMapper<MntLibRelHis, Integer>{
    
    
    public List<MntLibRelHis> findLibRelHisJoinList(MntLibRelHis mntLibRelHis);
    
}