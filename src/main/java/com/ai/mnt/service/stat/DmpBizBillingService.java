package com.ai.mnt.service.stat;

import java.util.List;

import com.ai.mnt.model.stat.DmpBizBilling;
import com.ai.mnt.model.stat.MntReqTrack;

/**
 * @Title: MntReqTrackService 
 * @Description: MntReqTrackService ServicePackage
 * @Author: Auto Generate.
 * @Date: 2016-8-3
 */

public interface DmpBizBillingService {
    
    
    /**
     * 获取所有部门需求故障跟踪列表
     * @return List<MntReqTrack>
     */
    public List<DmpBizBilling> findAllDmpBizBilling();
    
    /**
     * 获取DMP部门需求故障跟踪列表
     * @param inst
     * @return List<MntReqTrack>
     */
    public List<MntReqTrack> findMntReqTrackList(DmpBizBilling dmpBizBilling);
    

}
