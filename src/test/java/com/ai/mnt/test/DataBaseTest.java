package com.ai.mnt.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.mnt.model.stat.MntReqTrack;
import com.ai.mnt.model.stat.ReqSummaryStat;
import com.ai.mnt.persistence.base.MultipleDataSource;
import com.ai.mnt.service.stat.MntReqTrackService;

public class DataBaseTest {

    
    private static ApplicationContext ctx = null; 
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        ctx = new ClassPathXmlApplicationContext("spring-core.xml");
        
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void test() {
        
//        DmpBizBillingService dmpBizbllingService = ctx.getBean(DmpBizBillingService.class);
//        
//        MultipleDataSource.setDataSourceKey("dmpDataSource");
//        
//        List<DmpBizBilling> bizBillings = dmpBizbllingService.findAllDmpBizBilling();
//        for(DmpBizBilling dmpBizBilling : bizBillings) {
//            
//            System.out.println(dmpBizBilling.getBizName());
//            
//            
//        }
        
        MultipleDataSource.setDataSourceKey("mntDataSource");
        MntReqTrackService mntReqTrackService = ctx.getBean(MntReqTrackService.class);
        
//        List<MntReqTrack> reqTracks = mntReqTrackService.findAllMntReqTrack();
//        for(MntReqTrack reqTrack : reqTracks) {
//            System.out.println(reqTrack.getBaseName());
//        }
        
        List<MntReqTrack> summaryStat = mntReqTrackService.getReqSummaryStat();
        Map<String, List<ReqSummaryStat>> statMap = new HashMap<>();
        
        Map<String, List<MntReqTrack>> trackMap = new HashMap<>();
        for(MntReqTrack reqTrack : summaryStat) {
            List<MntReqTrack> mntReqTracks = trackMap.get(reqTrack.getBaseName());
            if(mntReqTracks == null) {
                mntReqTracks = new ArrayList<>();
                mntReqTracks.add(reqTrack);
                trackMap.put(reqTrack.getBaseName(), mntReqTracks);
            }else {
                mntReqTracks.add(reqTrack);
            }
        }
        
        for (Map.Entry<String, List<MntReqTrack>> entry : trackMap.entrySet()) {
            
            List<MntReqTrack> mntReqTracks = entry.getValue();
            
            ReqSummaryStat zgStat = new ReqSummaryStat();
            zgStat.setProdOrder(1);
            ReqSummaryStat zcStat = new ReqSummaryStat();
            zcStat.setProdOrder(2);
            ReqSummaryStat jfStat = new ReqSummaryStat();
            jfStat.setProdOrder(3);
            ReqSummaryStat zfVbStat = new ReqSummaryStat();
            zfVbStat.setProdOrder(4);
            ReqSummaryStat zcVbStat = new ReqSummaryStat();
            zcVbStat.setProdOrder(5);
            ReqSummaryStat otherStat = new ReqSummaryStat();
            otherStat.setProdOrder(6);
            
            List<ReqSummaryStat> stats = new ArrayList<>();
            stats.add(zgStat);
            stats.add(zcStat);
            stats.add(jfStat);
            stats.add(zfVbStat);
            stats.add(zcVbStat);
            stats.add(otherStat);
            for(MntReqTrack reqTrack : mntReqTracks) {
                if("帐管".equals(reqTrack.getProdName())) {
                    setValue(zgStat, reqTrack);
                }else if("帐处".equals(reqTrack.getProdName())) {
                    setValue(zcStat, reqTrack);
                }else if("OpenBilling".equals(reqTrack.getProdName())) {
                    setValue(jfStat, reqTrack);
                }else if("VB60计费系统".equals(reqTrack.getProdName())) {
                    setValue(zfVbStat, reqTrack);
                }else if("VB60帐务处理".equals(reqTrack.getProdName())) {
                    setValue(zcVbStat, reqTrack);
                }else {
                    setValue(otherStat, reqTrack);
                }
            }
            statMap.put(entry.getKey(), stats);
        }
        
        for (Map.Entry<String, List<ReqSummaryStat>> entry : statMap.entrySet()) {
            
            System.out.println("base= " + entry.getKey() + " and list= " + entry.getValue());        
        
        }
        
        //System.out.println(statMap);
//        MntInstallBaseInfoService mntInstallBaseInfoService = ctx.getBean(MntInstallBaseInfoService.class);
//        
//        MntInstallBaseInfo mntInstallBaseInfo = new MntInstallBaseInfo();
//        mntInstallBaseInfoService.updateInstallBaseInfo(mntInstallBaseInfo);
        
        
        
//        List<MntInstallBaseInfo> infos = mntInstallBaseInfoService.findAllMntInstallBaseInfo();
//        
//        for(MntInstallBaseInfo info : infos) {
//            System.out.println(info.getBaseName());
//        }
//        
//        
//        MultipleDataSource.setDataSourceKey("oracleDataSource");
////        MultipleDataSource.setDataSourceKey("mySqlDataSource");
//        
//        SysUserService sysUserService = ctx.getBean(SysUserService.class);
//        
//        List<SysUser> users = sysUserService.findAllUser();
//        
//        for(SysUser user : users) {
//            System.out.println(user.getUserName());
//        }
        
    }

    private void setValue(ReqSummaryStat stat, MntReqTrack reqTrack) {
        stat.setProdName(reqTrack.getProdName());
        stat.setBaseName(reqTrack.getBaseName());
        if("产品部SCCB".equals(reqTrack.getBizSts())) {
            stat.setSccbCount(stat.getSccbCount() + reqTrack.getCount());
        }else if("需求分析".equals(reqTrack.getBizSts())) {
            stat.setReqAnalyCount(stat.getReqAnalyCount() + reqTrack.getCount());
        }else if("开发".equals(reqTrack.getBizSts())) {
            stat.setDevCount(stat.getDevCount() + reqTrack.getCount());
        }else if("测试".equals(reqTrack.getBizSts())) {
            stat.setQaCount(stat.getQaCount() + reqTrack.getCount());
        }
    }
    
    
}
