package com.ai.mnt.persistence.product;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.mnt.model.product.MntReleaseRec;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntReleaseRecMapper 
 * @Description: MntReleaseRecMapper Persistence
 * @Author: Auto Generate 
 * @Date: 2016-4-21
 */
@Repository
public interface MntReleaseRecMapper extends CrudMapper<MntReleaseRec, Integer>{
    
    /**
     * 统计各安装点的需求量
     * @param mntReleaseRecDtl
     * @return
     */
    public List<MntReleaseRec> getStatResForBaseId(MntReleaseRec mntReleaseRec);
    
    /**
     * 统计各产品的需求量
     * @param mntReleaseRecDtl
     * @return
     */
    public List<MntReleaseRec> getStatResForProdId(MntReleaseRec mntReleaseRec);

    public List<MntReleaseRec> findByRelCode(String relCode);
    
}