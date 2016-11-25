package com.ai.mnt.business.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.ai.mnt.model.inst.MntInstallOnlineInfo;
import com.ai.mnt.model.stat.EmailAttachment;
import com.ai.mnt.model.stat.EmailInfo;
import com.ai.mnt.model.task.MntProcessTaskFact;
import com.ai.mnt.persistence.base.MultipleDataSource;
import com.ai.mnt.service.inst.MntInstallOnlineInfoService;
import com.ai.mnt.service.inst.MntProdBaseRelService;
import com.ai.mnt.service.task.MntProcessTaskFactService;

@Component
public class OnlineInfoBusiness implements BaseBusiness{

    private static Logger logger = Logger.getLogger(OnlineInfoBusiness.class);
    
    @Autowired
    MntInstallOnlineInfoService mntInstallOnlineInfoService;
    
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
                
//                boolean isCheckDate = true;
                
                baseDataCache.refreshCache();
                
                mntProcessTaskFact = new MntProcessTaskFact();
                mntProcessTaskFact.setTaskKey(1002);
                
                mntProcessTaskFact.setTaskDate(DateUtil.getCurrDateStr());
                
                Date currentDate = DateUtil.getCurrDate();
                //int day = Integer.parseInt(DateUtil.getDay(currentDate));
                String dayOfWeek = DateUtil.getWeekDay(currentDate);
                int hour = Integer.parseInt(DateUtil.getHour(currentDate));
                
                //先查询 手工调用有用
                List<MntProcessTaskFact> factList = mntProcessTaskFactService.findMntProcessTaskFactList(mntProcessTaskFact);
                
                if(factList == null || factList.size() == 0) {
                    errorTime = 0;
                    if("星期五".equals(dayOfWeek) && hour >= 13) {
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
                
//                //MNT_REQ_TRACK数据同步
//                bizDataSync(isCheckDate);
                
                //导出excel
                String exportPath = BaseDataCache.getDataName("FILE_PATH", "online_info_excel_path");
                
                exportPath = "D:\\biz\\";
                
                File dir = new File(exportPath);
                if(!dir.exists()) {
                    dir.mkdir();
                }
                exportPath += "现场上线情况_" + DateUtil.getCurrDate_yyyyMMdd() + ".xls";
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
     * 生成EXCEL报表
     * @return 
     * @throws Exception 
     */
    public void exportReqTrackExcel(String exportPath) throws Exception {
        logger.info("===========开始生成EXCEL报表===============");
        Workbook wb = null;
        FileOutputStream fos = null;
        try {
            String filePath = "template/online_info_templet_v1.xls";
            String fullFilePath = this.getClass().getClassLoader().getResource(filePath).getPath();
            wb = ExcelUtil.getWorkBook(fullFilePath);
            int number = wb.getNumberOfSheets();
            
            MultipleDataSource.setDataSourceKey("mntDataSource");
            MntInstallOnlineInfo onlineInfo = new MntInstallOnlineInfo();
            onlineInfo.setDeleteFlag("0");
            for(int i=0; i<number; i++) {
                Sheet sheet = wb.getSheetAt(i);
                if(sheet.getSheetName().contains("本周现场上线情况")) {
                    onlineInfo.setBeginDate(DateUtil.getWeekStartDate(DateUtil.getCurrDate()));
                    onlineInfo.setEndDate(DateUtil.getWeekEndDate(DateUtil.getCurrDate()));
                    List<MntInstallOnlineInfo> mntInstallOnlineInfos = mntInstallOnlineInfoService.findOnlineInfoListJoinRelDtl(onlineInfo);
                    wb.setSheetName(i, "本周现场上线情况(" + DateUtil.FormatDate(onlineInfo.getBeginDate(), "yyyyMMdd") + "-" + DateUtil.FormatDate(onlineInfo.getEndDate(), "yyyyMMdd") + ")");
                    fillDataForExcelSheet(mntInstallOnlineInfos, sheet);
                }else if(sheet.getSheetName().contains("下周现场计划上线")) {
                    onlineInfo.setBeginDate(DateUtil.getWeekStartDate(DateUtil.addDay(DateUtil.getWeekEndDate(DateUtil.getCurrDate()), 1)));
                    onlineInfo.setEndDate(DateUtil.getWeekEndDate(onlineInfo.getBeginDate()));
                    List<MntInstallOnlineInfo> mntInstallOnlineInfos = mntInstallOnlineInfoService.findOnlineInfoListJoinRelDtl(onlineInfo);
                    wb.setSheetName(i, "下周现场计划上线(" + DateUtil.FormatDate(onlineInfo.getBeginDate(), "yyyyMMdd") + "-" + DateUtil.FormatDate(onlineInfo.getEndDate(), "yyyyMMdd") + ")");
                    fillDataForExcelSheet(mntInstallOnlineInfos, sheet);
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
     * excel填充数据
     * @param mntReqTracks
     * @param sheet
     */
    private void fillDataForExcelSheet(List<MntInstallOnlineInfo> mntInstallOnlineInfos, Sheet sheet) {
        if(mntInstallOnlineInfos == null || mntInstallOnlineInfos.size() == 0) {
            return;
        }
        for(int i=0, len=mntInstallOnlineInfos.size(); i<len; i++) {
            MntInstallOnlineInfo mntInstallOnlineInfo = mntInstallOnlineInfos.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(DateUtil.dateToStringShort(mntInstallOnlineInfo.getPlanOnlineDate()));
            row.createCell(1).setCellValue(DateUtil.dateToStringShort(mntInstallOnlineInfo.getDelayOnlineDate()));
            row.createCell(2).setCellValue(DateUtil.dateToStringShort(mntInstallOnlineInfo.getOnlineDate()));
            row.createCell(3).setCellValue(mntInstallOnlineInfo.getIsOnlinedTxt());
            row.createCell(4).setCellValue(mntInstallOnlineInfo.getBaseName());
            row.createCell(5).setCellValue(mntInstallOnlineInfo.getProdName());
            row.createCell(6).setCellValue(mntInstallOnlineInfo.getVerCode());
            row.createCell(7).setCellValue(mntInstallOnlineInfo.getRelCode());
            row.createCell(8).setCellValue(mntInstallOnlineInfo.getMntReleaseRecDtl().getDtlCode());
            row.createCell(9).setCellValue(mntInstallOnlineInfo.getMntReleaseRecDtl().getDtlName());
            row.createCell(10).setCellValue(mntInstallOnlineInfo.getModuleName());
            row.createCell(11).setCellValue(mntInstallOnlineInfo.getIsOnsiteSupportTxt());
            row.createCell(12).setCellValue(mntInstallOnlineInfo.getIsRemoteSupportTxt());
            row.createCell(13).setCellValue(mntInstallOnlineInfo.getIsFaultTxt());
            row.createCell(14).setCellValue(mntInstallOnlineInfo.getUnOnlineReason());
            row.createCell(15).setCellValue(mntInstallOnlineInfo.getOnlineRemark());
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
            
            List<EnumObject> addrEnumObjects = BaseDataCache.getDataList("EMAIL_TO_ADDR_ONLINE_INFO");
            if(addrEnumObjects == null || addrEnumObjects.size() == 0) {
                throw new Exception("收件人[EMAIL_TO_ADDR_ONLINE_INFO]未配置！");
            }
            List<String> toAddrs = new ArrayList<>();
            for(EnumObject addr : addrEnumObjects) {
                toAddrs.add(addr.getValue());
            }
            mailInfo.setToAddrs(toAddrs);
            
            List<EnumObject> ccEnumObjects = BaseDataCache.getDataList("EMAIL_TO_CC_ONLINE_INFO");
            if(ccEnumObjects != null && ccEnumObjects.size() > 0) {
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
            
            
            String subject = "现场上线情况_" + DateUtil.getCurrDate_yyyyMMdd();
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
        sbuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        sbuffer.append("本周现场上线情况和下周现场上线计划，请参考附件:");
        sbuffer.append(subject);
        sbuffer.append("。<br/>");
        sbuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请及时关注，有问题可以联系倩倩处理。谢谢！<br/><br/><br/>");
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
