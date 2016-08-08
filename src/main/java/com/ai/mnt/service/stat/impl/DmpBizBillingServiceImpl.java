package com.ai.mnt.service.stat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.model.stat.DmpBizBilling;
import com.ai.mnt.model.stat.MntReqTrack;
import com.ai.mnt.persistence.stat.DmpBizBillingMapper;
import com.ai.mnt.service.stat.DmpBizBillingService;

@Service
public class DmpBizBillingServiceImpl implements DmpBizBillingService {

    @Autowired
    DmpBizBillingMapper dmpBizbillingMapper;
    
    @Override
    public List<MntReqTrack> findMntReqTrackList(DmpBizBilling dmpBizBilling) {
        
        return null;
    }

    @Override
    public List<DmpBizBilling> findAllDmpBizBilling() {
        
        List<DmpBizBilling> bizs = dmpBizbillingMapper.findAll();
        
        return bizs;
    }

}
