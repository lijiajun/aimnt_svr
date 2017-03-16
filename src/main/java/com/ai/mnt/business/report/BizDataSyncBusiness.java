package com.ai.mnt.business.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.mnt.business.base.BaseBusiness;
import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.common.util.DateUtil;
import com.ai.mnt.common.util.EmailUtil;
import com.ai.mnt.common.util.ExcelUtil;
import com.ai.mnt.model.common.EnumObject;
import com.ai.mnt.model.inst.MntProdBaseRel;
import com.ai.mnt.model.stat.DmpBizBilling;
import com.ai.mnt.model.stat.EmailAttachment;
import com.ai.mnt.model.stat.EmailInfo;
import com.ai.mnt.model.stat.MntReqTrack;
import com.ai.mnt.model.stat.ReqSummaryStat;
import com.ai.mnt.model.task.MntProcessTaskFact;
import com.ai.mnt.persistence.base.MultipleDataSource;
import com.ai.mnt.service.inst.MntProdBaseRelService;
import com.ai.mnt.service.stat.DmpBizBillingService;
import com.ai.mnt.service.stat.MntReqTrackService;
import com.ai.mnt.service.task.MntProcessTaskFactService;

@Component
public class BizDataSyncBusiness implements BaseBusiness{

    private static Logger logger = Logger.getLogger(BizDataSyncBusiness.class);
    
    @Autowired
    DmpBizBillingService dmpBizBillingService;
    
    @Autowired
    MntReqTrackService mntReqTrackService;
    
    @Autowired
    MntProcessTaskFactService mntProcessTaskFactService;
    
    @Autowired
    BaseDataCache baseDataCache;
    
    @Autowired
    MntProdBaseRelService mntProdBaseRelService;
    
    private String errorMsg = "";
    
    public void run() {
        
        MntProcessTaskFact mntProcessTaskFact = null;
        
        //记录错误次数
        int errorTime = 0;
        
        while(true) {
            try {
                
                boolean isCheckDate = true;
                
                baseDataCache.refreshCache();
                
                mntProcessTaskFact = new MntProcessTaskFact();
                mntProcessTaskFact.setTaskKey(1001);
                
                mntProcessTaskFact.setTaskDate(DateUtil.getCurrDateStr());
                
                Date currentDate = DateUtil.getCurrDate();
                //int day = Integer.parseInt(DateUtil.getDay(currentDate));
                String dayOfWeek = DateUtil.getWeekDay(currentDate);
                int hour = Integer.parseInt(DateUtil.getHour(currentDate));
                
                //先查询 手工调用有用
                List<MntProcessTaskFact> factList = mntProcessTaskFactService.findMntProcessTaskFactList(mntProcessTaskFact);
                
                if(factList == null || factList.size() == 0) {
                    errorTime = 0;
                    if(("星期一".equals(dayOfWeek) || "星期三".equals(dayOfWeek) 
                            || "星期五".equals(dayOfWeek)) && hour >= 6) {
                        mntProcessTaskFact.setTaskSts("2");
                        mntProcessTaskFact.setCreateDate(currentDate);
                        mntProcessTaskFact.setBeginTime(currentDate);
                        mntProcessTaskFactService.saveMntProcessTaskFact(mntProcessTaskFact);
                    }else {
                        logger.info("==========还没有到任务执行时间且没有可执行任务=======休眠1小时============");
                        Thread.sleep(3600 * 1000);
                        continue;
                    }
                }else {
                    mntProcessTaskFact = factList.get(0);
                    //初始化状态立即执行  可用于手工插入及时调用
                    if("1".equals(mntProcessTaskFact.getTaskSts())) {
                        errorTime = 0;
                        mntProcessTaskFact.setTaskSts("2");
                        mntProcessTaskFact.setBeginTime(currentDate);
                        mntProcessTaskFactService.updateMntProcessTaskFactById(mntProcessTaskFact);
                        
                        isCheckDate = false;
                        
                    //失败状态重复调用3次
                    }else if("4".equals(mntProcessTaskFact.getTaskSts())) {
                        
                        if(errorTime == 3) {
                            logger.info("==========本周期已经执行错误3次，等待下周期任务=======休息1小时============");
                            Thread.sleep(3600 * 1000);
                            continue;
                        }
                        
                        mntProcessTaskFact.setTaskSts("2");
                        mntProcessTaskFact.setBeginTime(currentDate);
                        mntProcessTaskFactService.updateMntProcessTaskFactById(mntProcessTaskFact);
                        
                    }else {
                        errorTime = 0;
                        logger.info("==========当日任务已经执行完成=======休息1小时============");
                        Thread.sleep(3600 * 1000);
                        continue;
                    }
                }
                
                //MNT_REQ_TRACK数据同步
                bizDataSync(isCheckDate);
                
                //导出excel
                String exportPath = BaseDataCache.getDataName("FILE_PATH", "req_track_excel_path");
                
                //exportPath = "D:\\biz\\";
                
                File dir = new File(exportPath);
                if(!dir.exists()) {
                    dir.mkdir();
                }
                exportPath += "部门需求故障跟踪列表_" + DateUtil.getCurrDate_yyyyMMdd() + ".xls";
                exportReqTrackExcel(exportPath);
                
                //发邮件
                List<String> attachPaths = new ArrayList<>();
                attachPaths.add(exportPath);
                sendEmail(attachPaths);
                
                //任务执行成功
                mntProcessTaskFact.setTaskSts("3");
                mntProcessTaskFact.setEndTime(DateUtil.getCurrDate());
                mntProcessTaskFact.setResult("PERFECT!");
                mntProcessTaskFactService.updateMntProcessTaskFactById(mntProcessTaskFact);
                
            }catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                try {
                    //任务执行失败
                    errorTime++;
                    mntProcessTaskFact.setTaskSts("4");
                    mntProcessTaskFact.setEndTime(DateUtil.getCurrDate());
                    errorMsg += "已经" + errorTime + "次执行错误！" + e.getMessage();
                    mntProcessTaskFact.setResult(errorMsg);
                    MultipleDataSource.setDataSourceKey("mntDataSource");
                    mntProcessTaskFactService.updateMntProcessTaskFactById(mntProcessTaskFact);
                    logger.error(errorMsg);
                    logger.error("休息5分钟！！！！！！！！！！！！！");
                    Thread.sleep(300 * 1000);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    logger.error(e1.getMessage());
                }
            }
        }
        
    }
    
    
    /**
     * 数据同步
     * @throws Exception 
     */
    public void bizDataSync(boolean isCheckDate) throws Exception {
       
        try {
            //DmpBizBillingService dmpBizbllingService = ctx.getBean(DmpBizBillingService.class);
            
            MultipleDataSource.setDataSourceKey("dmpDataSource");
            logger.info("===========开始获取DMP数据===============");
            
            List<DmpBizBilling> bizBillings = dmpBizBillingService.findAllDmpBizBilling();
            
            //MntReqTrackService trackService = ctx.getBean(MntReqTrackService.class);
            MultipleDataSource.setDataSourceKey("mntDataSource");
            if(bizBillings != null && bizBillings.size() > 0) {
                DmpBizBilling bizBilling = bizBillings.get(0);
                
                MntReqTrack mntReqTrack = new MntReqTrack();
                mntReqTrack.setDmpUpdateTime(bizBilling.getDmpUpdateTime());
                mntReqTrack.setDeleteFlag("0");
                List<MntReqTrack> reqTrackList = mntReqTrackService.findMntReqTrackList(mntReqTrack);
                if(reqTrackList != null && reqTrackList.size() > 0 && isCheckDate) {
                    String msg = "===========该时间" + DateUtil.dateToString(bizBilling.getDmpUpdateTime()) + " DMP数据已经存在，不用再同步！===============";
                    logger.info(msg);
                    throw new Exception(msg);
                }
            }else {
                throw new Exception("===========未获取到DMP数据===============");
            }
            
            List<MntReqTrack> mntReqTracks = new ArrayList<MntReqTrack>();
            
            for(DmpBizBilling dmpBizBilling : bizBillings) {
                MntReqTrack mntReqTrack = new MntReqTrack();
                copyDmpBiz2MntReqTrack(dmpBizBilling, mntReqTrack);
                mntReqTrack.setDeleteFlag("0");
                mntReqTrack.setCreator("auto");
                mntReqTrack.setModifier("auto");
                mntReqTrack.setCreateDate(new Date());
                mntReqTrack.setModifyDate(new Date());
                mntReqTracks.add(mntReqTrack);
            }
            
            logger.info("===========开始同步MNT_REQ_TRACK数据===============");
            if(mntReqTracks.size() > 0) {
                MntReqTrack mntReqTrack = new MntReqTrack();
                mntReqTrack.setDeleteFlag("1");
                mntReqTrack.setModifier("auto");
                mntReqTrack.setModifyDate(new Date());
                mntReqTrackService.updateAndSaveReqTrack(mntReqTrack, mntReqTracks);
            }
            logger.info("===========同步MNT_REQ_TRACK数据成功===============");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("===========同步MNT_REQ_TRACK数据失败===============" + e.getMessage());
            throw new Exception("同步MNT_REQ_TRACK数据失败!" + e.getMessage());
        }
    }
    
    private void copyDmpBiz2MntReqTrack(DmpBizBilling bizBilling, MntReqTrack mntReqTrack) {
        
        String baseName = BaseDataCache.getDataName("PRJERCT_TO_BASENAME", bizBilling.getBaseName());
        baseName = ("".equals(baseName) || baseName == null) ? bizBilling.getBaseName() : baseName;
        mntReqTrack.setBaseName(baseName);
        
        String prodName = bizBilling.getProdName();
        int prodId = -1;
        if(prodName.contains("OpenBilling")) {
            prodName = "OpenBilling";
            prodId = 3;
        }else if(prodName.contains("VerisBilling_CMC") && prodName.contains("计费")) {
            prodName = "VB60计费系统";
            prodId = 3;
        }else if(prodName.contains("VerisBilling_CMC") && (prodName.contains("帐务") || prodName.contains("账务"))) {
            prodName = "VB60帐务处理";
            prodId = 2;
        }else if(prodName.contains("VerisBilling_CMC") && prodName.contains("ABM余额管理")) {
            prodName = "VB60帐务处理";
            prodId = 2;
        }else if(prodName.contains("BIZ_BILLING") && prodName.contains("帐务处理")) {
            prodName = "帐处";
            prodId = 2;
        }else if(prodName.contains("综合帐务管理系统")) {
            prodName = "帐管";
            prodId = 1;
        }else if(prodName.contains("VerisBilling_CMC") && prodName.contains("西藏BOSS")) {
            prodName = "其他";
        }
        mntReqTrack.setProdId(prodId);
        mntReqTrack.setProdName(prodName);
        mntReqTrack.setDmpBaseName(bizBilling.getBaseName());
        mntReqTrack.setDmpProdName(bizBilling.getProdName());
        mntReqTrack.setBizName(bizBilling.getBizName());
        mntReqTrack.setBizNo(bizBilling.getBizNo());
        mntReqTrack.setBizType(bizBilling.getBizType());
        mntReqTrack.setBizSrc(bizBilling.getBizSrc());
        mntReqTrack.setBizSts(bizBilling.getBizSts());
        mntReqTrack.setSubmitPerson(bizBilling.getSubmitPerson());
        mntReqTrack.setNodePerson(bizBilling.getNodePerson());
        mntReqTrack.setPriority(bizBilling.getPriority());
        mntReqTrack.setUrgentLevel(bizBilling.getUrgentLevel());
        mntReqTrack.setSubmitDate(bizBilling.getSubmitDate());
        mntReqTrack.setDealDays(bizBilling.getDealDays());
        mntReqTrack.setAskEndDate(DateUtil.stringToDate(bizBilling.getAskEndDate(), "yyyy-MM-dd"));
        mntReqTrack.setPlanReleaseDate(DateUtil.stringToDate(bizBilling.getPlanReleaseDate(), "yyyy-MM-dd"));
        mntReqTrack.setPlanOnlineDate(DateUtil.stringToDate(bizBilling.getPlanOnlineDate(), "yyyy-MM-dd"));
        mntReqTrack.setDmpUpdateTime(bizBilling.getDmpUpdateTime());
    }
    
    /**
     * 生成EXCEL报表
     * @return 
     * @throws Exception 
     */
    public void exportReqTrackExcel(String exportPath) throws Exception {
        logger.info("===========开始生成EXCEL报表===============");
        Workbook wb = null;
        FileOutputStream fos = null;
        try {
            String filePath = "template/req_track_templet_v3.xls";
            String fullFilePath = this.getClass().getClassLoader().getResource(filePath).getPath();
            wb = ExcelUtil.getWorkBook(fullFilePath);
            int number = wb.getNumberOfSheets();
            
            //MntReqTrackService trackService = ctx.getBean(MntReqTrackService.class);
            MultipleDataSource.setDataSourceKey("mntDataSource");
            MntReqTrack mntReqTrack = new MntReqTrack();
            for(int i=0; i<number; i++) {
                Sheet sheet = wb.getSheetAt(i);
                if(sheet.getSheetName().contains("部门未完成需求跟踪")) {
                    mntReqTrack.setBizType("客户需求");
                    mntReqTrack.setDeleteFlag("0");
                    List<MntReqTrack> reqTrackList = mntReqTrackService.findMntReqTrackList(mntReqTrack);
                    fillDataForExcelSheet(reqTrackList, sheet);
                }else if(sheet.getSheetName().contains("本部门需求跟踪")) {
                    mntReqTrack.setBizType("客户需求");
                    mntReqTrack.setDeleteFlag("0");
                    List<MntReqTrack> reqTrackList = mntReqTrackService.findMntReqTrackList(mntReqTrack);
                    //过滤本部门的需求
                    List<MntReqTrack> reqTrackListTemp = new ArrayList<>();
                    if(reqTrackList !=null && reqTrackList.size() > 0) {
                        List<MntProdBaseRel> relList = mntProdBaseRelService.findProdJoinBaseInfoList();
                        for(MntReqTrack mntReqTrackTemp : reqTrackList) {
                            for(MntProdBaseRel mntProdBaseRel : relList) {
                                if(mntReqTrackTemp.getProdId() == mntProdBaseRel.getProdId()
                                        && mntProdBaseRel.getMntInstallBaseInfo().getBaseName().equals(mntReqTrackTemp.getBaseName())) {
                                    reqTrackListTemp.add(mntReqTrackTemp);
                                }
                            }
                        }
                    }
                    fillDataForExcelSheet(reqTrackListTemp, sheet);
                }else if(sheet.getSheetName().contains("部门故障跟踪")) {
                    mntReqTrack.setBizType("故障管理");
                    mntReqTrack.setDeleteFlag("0");
                    mntReqTrack.setSubmitDate(DateUtil.getYearBegin(DateUtil.getCurrDate()));
                    List<MntReqTrack> reqTrackList = mntReqTrackService.findMntReqTrackList(mntReqTrack);
                    wb.setSheetName(i, DateUtil.getYear(DateUtil.getCurrDate())+ "年部门故障跟踪");
                    fillDataForExcelSheet(reqTrackList, sheet);
                }else if(sheet.getSheetName().contains("汇总结果")) {
                    List<MntReqTrack> summaryStat = mntReqTrackService.getReqSummaryStat();
                    Map<String, List<ReqSummaryStat>> statMap = new HashMap<>();
                    buildSummaryData(statMap, summaryStat);
                    fillSummaryDataForExcelSheet(statMap, sheet);
                }else if(sheet.getSheetName().contains("超长期需求列表")) {
                    mntReqTrack.setBizType("客户需求");
                    mntReqTrack.setDeleteFlag("0");
                    mntReqTrack.setDealDays(120); //>=120天
                    mntReqTrack.setSubmitDate(null);
                    List<MntReqTrack> reqTrackList = mntReqTrackService.findMntReqTrackList(mntReqTrack);
                    fillDataForExcelSheet(reqTrackList, sheet);
                }else if(sheet.getSheetName().contains("部门未完成BUG跟踪列表")) {
                    mntReqTrack.setBizType("测试缺陷");
                    mntReqTrack.setDeleteFlag("0");
                    mntReqTrack.setSubmitDate(DateUtil.getYearBegin(DateUtil.getCurrDate()));
                    List<MntReqTrack> reqTrackList = mntReqTrackService.findMntReqTrackList(mntReqTrack);
                    fillDataForBugExcelSheet(reqTrackList, sheet);
                }else {
                    continue;
                }
            }
            fos = new FileOutputStream(exportPath);
            wb.write(fos);
            logger.info("EcelExportPath : " + exportPath);
            logger.info("===========生成EXCEL报表成功===============");
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("===========生成EXCEL报表失败===============");
            throw new Exception("生成EXCEL报表失败!" + e.getMessage());
        }finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 汇总结果sheet填充
     * @param statMap
     * @param sheet
     */
    private void fillSummaryDataForExcelSheet(Map<String, List<ReqSummaryStat>> statMap, Sheet sheet) {
        if(statMap == null || statMap.size() == 0) {
            return;
        }
        List<EnumObject> baseNames = BaseDataCache.getDataList("PRJERCT_TO_BASENAME");
        
        Set<String> baseNameSets = new LinkedHashSet<>();
        for(EnumObject enumObject : baseNames) {
            baseNameSets.add(enumObject.getValue());
        }
        int i = 0;
        for(String baseName : baseNameSets) {
            Row row = sheet.getRow(i + 2);
            row.getCell(0).setCellValue(baseName);
            i++;
            List<ReqSummaryStat> stats = statMap.get(baseName);
            if(stats == null) {
              continue;
            }
            int j = 1;
            for(ReqSummaryStat stat : stats) {
                row.getCell(++j).setCellValue(stat.getSccbCount());
                row.getCell(++j).setCellValue(stat.getReqAnalyCount());
                row.getCell(++j).setCellValue(stat.getDevCount());
                row.getCell(++j).setCellValue(stat.getQaCount());
            }
        }
        
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = ExcelUtil.getCellValue(cell);
                if("0.0".equals(cellValue)) {
                    cell.setCellValue("");
                }
            }
        }
        
        sheet.setForceFormulaRecalculation(true);
    }


    /**
     * 构建汇总数据
     * @param statMap
     * @param summaryStat
     */
    private void buildSummaryData(Map<String, List<ReqSummaryStat>> statMap,
            List<MntReqTrack> summaryStat) {
        
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
        
//        for (Map.Entry<String, List<ReqSummaryStat>> entry : statMap.entrySet()) {
//            
//            System.out.println("base= " + entry.getKey() + " and list= " + entry.getValue());        
//        
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
    
    /**
     * excel填充数据BUG
     * @param mntReqTracks
     * @param sheet
     */
    private void fillDataForBugExcelSheet(List<MntReqTrack> mntReqTracks, Sheet sheet) {
        if(mntReqTracks == null || mntReqTracks.size() == 0) {
            return;
        }
        for(int i=0, len=mntReqTracks.size(); i<len; i++) {
            MntReqTrack mntReqTrack = mntReqTracks.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(mntReqTrack.getProdName());
            row.createCell(1).setCellValue(mntReqTrack.getBizNo());
            row.createCell(2).setCellValue(mntReqTrack.getBizName());
            row.createCell(3).setCellValue(mntReqTrack.getBizSts());
            row.createCell(4).setCellValue(DateUtil.dateToStringShort(mntReqTrack.getSubmitDate()));
            row.createCell(5).setCellValue(mntReqTrack.getDealDays());
            row.createCell(6).setCellValue(mntReqTrack.getSubmitPerson());
            row.createCell(7).setCellValue(mntReqTrack.getNodePerson());
            row.createCell(8).setCellValue(mntReqTrack.getBizSrc());
            row.createCell(9).setCellValue(mntReqTrack.getUrgentLevel());
        }
    }
    
    /**
     * excel填充数据
     * @param mntReqTracks
     * @param sheet
     */
    public void fillDataForExcelSheet(List<MntReqTrack> mntReqTracks, Sheet sheet) {
        if(mntReqTracks == null || mntReqTracks.size() == 0) {
            return;
        }
        for(int i=0, len=mntReqTracks.size(); i<len; i++) {
            MntReqTrack mntReqTrack = mntReqTracks.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(mntReqTrack.getDmpBaseName());
            row.createCell(1).setCellValue(mntReqTrack.getProdName());
            row.createCell(2).setCellValue(mntReqTrack.getBizNo());
            row.createCell(3).setCellValue(mntReqTrack.getBizName());
            row.createCell(4).setCellValue(mntReqTrack.getBizSrc());
            row.createCell(5).setCellValue(mntReqTrack.getBizSts());
            row.createCell(6).setCellValue(mntReqTrack.getNodePerson());
            if(sheet.getSheetName().contains("部门故障跟踪")) {
                row.createCell(7).setCellValue(DateUtil.dateToStringShort(mntReqTrack.getSubmitDate()));
                row.createCell(8).setCellValue(mntReqTrack.getDealDays());
                row.createCell(9).setCellValue(DateUtil.dateToStringShort(mntReqTrack.getAskEndDate()));
            }else {
                row.createCell(7).setCellValue(mntReqTrack.getPriority());
                row.createCell(8).setCellValue(DateUtil.dateToStringShort(mntReqTrack.getSubmitDate()));
                row.createCell(9).setCellValue(mntReqTrack.getDealDays());
                row.createCell(10).setCellValue(DateUtil.dateToStringShort(mntReqTrack.getAskEndDate()));
                row.createCell(11).setCellValue(DateUtil.dateToStringShort(mntReqTrack.getPlanOnlineDate()));
                row.createCell(12).setCellValue(DateUtil.dateToStringShort(mntReqTrack.getPlanReleaseDate()));
            }
        }
    }
    
    /**
     * 发邮件
     * @param filePath
     * @throws Exception
     */
    public void sendEmail(List<String> attachPaths) throws Exception {
        
        try {
            
            String host     = BaseDataCache.getDataName("EMAIL_SEND_PARAM", "host");
            String protocol = BaseDataCache.getDataName("EMAIL_SEND_PARAM", "protocol");
            String fromAddr = BaseDataCache.getDataName("EMAIL_SEND_PARAM", "fromAddr");
            String username = BaseDataCache.getDataName("EMAIL_SEND_PARAM", "username");
            String password = BaseDataCache.getDataName("EMAIL_SEND_PARAM", "password");
            
            EmailInfo mailInfo = new EmailInfo();
            mailInfo.setHost(host);
            mailInfo.setProtocol(protocol);
            mailInfo.setSmtpAuth(true);
            mailInfo.setFromAddr(fromAddr);
            mailInfo.setUsername(username);
            mailInfo.setPassword(password);
            
            List<EnumObject> addrEnumObjects = BaseDataCache.getDataList("EMAIL_TO_ADDR_REQ_TRACK");
            if(addrEnumObjects == null || addrEnumObjects.size() == 0) {
                throw new Exception("收件人[EMAIL_TO_ADDR_REQ_TRACK]未配置！");
            }
            List<String> toAddrs = new ArrayList<>();
            for(EnumObject addr : addrEnumObjects) {
                toAddrs.add(addr.getValue());
            }
            mailInfo.setToAddrs(toAddrs);
            
            List<EnumObject> ccEnumObjects = BaseDataCache.getDataList("EMAIL_TO_CC_REQ_TRACK");
            if(ccEnumObjects.size() > 0) {
                List<String> ccs = new ArrayList<>();
                for(EnumObject cc : ccEnumObjects) {
                    ccs.add(cc.getValue());
                }
                mailInfo.setCCs(ccs);
            }
            
            //附件
            List<EmailAttachment> emailAttachs = new ArrayList<>();
            if(attachPaths != null && attachPaths.size() > 0) {
                for(String attachPath : attachPaths) {
                    EmailAttachment emailAttachment = new EmailAttachment();
                    emailAttachment.setAttachPath(attachPath);
                    emailAttachs.add(emailAttachment);
                }
                mailInfo.setEmailAttachs(emailAttachs);
            }
            
            
            String subject = "部门需求故障跟踪列表_" + DateUtil.getCurrDate_yyyyMMdd();
            mailInfo.setSubject(subject);
            mailInfo.setSubType("mixed");
            
            mailInfo.setContent(createContent(subject));
            
            logger.info("邮件信息：" + mailInfo);
            
            EmailUtil.sendEmail(mailInfo);
            
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("邮件发送失败" + e.getMessage());
            throw new Exception("邮件发送失败" + e.getMessage());
        }
    }

    /**
     * 组装邮件内容
     * @param subject
     * @return
     */
    private String createContent(String subject) {
        
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append("<div style='font-family:微软雅黑'>");
        sbuffer.append("大家好: <br/>");
        sbuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;截止");
        sbuffer.append(DateUtil.getCurrDateStr());
        sbuffer.append("计费、帐处、帐管侧未完成需求");
        sbuffer.append(DateUtil.getYear(DateUtil.getCurrDate()));
        sbuffer.append("年故障跟踪列表以及超长期需求列表，请参考附件:");
        sbuffer.append(subject);
        sbuffer.append("。（注：数据为数据库视图查询结果导出）<br/>");
        sbuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请teamleader及节点责任人及时关注，有问题可以联系倩倩处理。谢谢！<br/><br/><br/>");
        sbuffer.append("################################################<br/>");
        sbuffer.append("<span style='font-style:italic;'>");
        sbuffer.append("该邮件为运维平台自动发送,请勿回复！<br/>");
        sbuffer.append("运维平台网址： <a href='http://10.10.25.172:8080/aimnt'>http://10.10.25.172:8080/aimnt</a><br/>");
        sbuffer.append("您的满意是我们工作最大的动力！！！<br/>");
        sbuffer.append("</span>");
        sbuffer.append("################################################<br/><br/>");
        sbuffer.append("</div>");
        
        return sbuffer.toString();
    }
    
}
