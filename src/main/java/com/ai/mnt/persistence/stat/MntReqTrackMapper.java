package com.ai.mnt.persistence.stat;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.mnt.model.stat.MntReqTrack;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntReqTrackMapper 
 * @Description: MntReqTrackMapper Persistence
 * @Author: Auto Generate. HE
 * @Date: 2016-8-3
 */
@Repository
public interface MntReqTrackMapper extends CrudMapper<MntReqTrack, Integer>{
    
    public void updateReqTrackDeleteFlag(MntReqTrack mntReqTrack);
    
    public List<MntReqTrack> getReqSummaryStat();
    
}