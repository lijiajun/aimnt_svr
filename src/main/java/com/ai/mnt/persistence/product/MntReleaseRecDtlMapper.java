package com.ai.mnt.persistence.product;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.mnt.model.product.MntReleaseRecDtl;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntReleaseRecDtlMapper 
 * @Description: MntReleaseRecDtlMapper Persistence
 * @Author: Auto Generate 
 * @Date: 2016-4-21
 */
@Repository
public interface MntReleaseRecDtlMapper extends CrudMapper<MntReleaseRecDtl, Integer>{
    
    
    public List<MntReleaseRecDtl> findRecAndDtlJoinList(MntReleaseRecDtl mntReleaseRecDtl);
    
    /**
     * 统计安装点的需求量
     * @param mntReleaseRecDtl
     * @return
     */
    public List<MntReleaseRecDtl> getStatResForBaseId(MntReleaseRecDtl mntReleaseRecDtl);
    
}