package com.ai.mnt.persistence.inst;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.mnt.model.inst.MntInstallOnlineInfo;
import com.ai.mnt.persistence.base.CrudMapper;

/**
 * @Title: MntInstallOnlineInfoMapper 
 * @Description: MntInstallOnlineInfoMapper Persistence
 * @Author: Auto Generate. HE
 * @Date: 2016-5-23
 */
@Repository
public interface MntInstallOnlineInfoMapper extends CrudMapper<MntInstallOnlineInfo, Integer>{
    
    public List<MntInstallOnlineInfo> getDistinctOnlineDateList(MntInstallOnlineInfo mntInstallOnlineInfo);

    public List<MntInstallOnlineInfo> findOnlineInfoListJoinRelDtl(
            MntInstallOnlineInfo mntInstallOnlineInfo);
    
    
    public List<MntInstallOnlineInfo> findNotOnlineInfoListJoinRelDtl(
            MntInstallOnlineInfo mntInstallOnlineInfo);

    public void updateRelIdByDtlId(MntInstallOnlineInfo mntInstallOnlineInfo);
}