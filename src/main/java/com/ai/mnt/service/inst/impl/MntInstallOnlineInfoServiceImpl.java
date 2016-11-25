package com.ai.mnt.service.inst.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.common.util.DateUtil;
import com.ai.mnt.exception.MntDataAccessException;
import com.ai.mnt.model.inst.MntInstallOnlineInfo;
import com.ai.mnt.model.product.MntProd;
import com.ai.mnt.model.product.MntProdModule;
import com.ai.mnt.model.product.MntProdVersion;
import com.ai.mnt.model.product.MntReleaseRec;
import com.ai.mnt.model.product.MntReleaseRecDtl;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.inst.MntInstallOnlineInfoMapper;
import com.ai.mnt.persistence.product.MntProdMapper;
import com.ai.mnt.persistence.product.MntProdModuleMapper;
import com.ai.mnt.persistence.product.MntProdVersionMapper;
import com.ai.mnt.persistence.product.MntReleaseRecDtlMapper;
import com.ai.mnt.persistence.product.MntReleaseRecMapper;
import com.ai.mnt.service.inst.MntInstallOnlineInfoService;

/**
 * @Title: MntInstallOnlineInfoServiceImpl 
 * @Description: MntInstallOnlineInfoServiceImpl ServiceImplPackage
 * @Author: Auto Generate. HE
 * @Date: 2016-5-23
 */
@Service
public class MntInstallOnlineInfoServiceImpl implements MntInstallOnlineInfoService{

    @Autowired
    MntInstallOnlineInfoMapper mntInstallOnlineInfoMapper;
    
//    @Autowired
//    UserRealm userRealm;
    
    @Autowired
    MntProdMapper mntProdMapper;
    
    @Autowired
    MntProdVersionMapper mntProdVersionMapper;
    
    @Autowired
    MntProdModuleMapper mntProdModuleMapper;
    
    @Autowired
    MntReleaseRecMapper mntReleaseRecMapper;
    
    @Autowired
    MntReleaseRecDtlMapper mntReleaseRecDtlMapper;
    
    /**
     * 获取所有安装点上线信息列表
     * @return List<MntInstallOnlineInfo>
     */
    @Override
    public List<MntInstallOnlineInfo> findAllMntInstallOnlineInfo() {
        List<MntInstallOnlineInfo> mntInstallOnlineInfoList = mntInstallOnlineInfoMapper.findAll();
        //cvtContentList(mntInstallOnlineInfoList);
        return mntInstallOnlineInfoList;
    }

    /**
     * 获取安装点上线信息列表
     * @param inst
     * @return List<MntInstallOnlineInfo>
     */
    @Override
    public List<MntInstallOnlineInfo> findMntInstallOnlineInfoList(MntInstallOnlineInfo mntInstallOnlineInfo) {
        List<MntInstallOnlineInfo> mntInstallOnlineInfoList = mntInstallOnlineInfoMapper.findList(mntInstallOnlineInfo);
        cvtContentList(mntInstallOnlineInfoList);
        return mntInstallOnlineInfoList;
    }
    
    /**
     * 获取安装点上线信息列表
     * @param inst
     * @return List<MntInstallOnlineInfo>
     */
    @Override
    public List<MntInstallOnlineInfo> findOnlineInfoListJoinRelDtl(MntInstallOnlineInfo mntInstallOnlineInfo) {
        List<MntInstallOnlineInfo> mntInstallOnlineInfoList = mntInstallOnlineInfoMapper.findOnlineInfoListJoinRelDtl(mntInstallOnlineInfo);
        cvtContentList(mntInstallOnlineInfoList);
        return mntInstallOnlineInfoList;
    }

    
    /**
     * 获取安装点未上线信息列表
     * @param inst
     * @return List<MntInstallOnlineInfo>
     */
    @Override
    public List<MntInstallOnlineInfo> findNotOnlineInfoListJoinRelDtl(MntInstallOnlineInfo mntInstallOnlineInfo) {
        List<MntInstallOnlineInfo> mntInstallNotOnlineInfoList = mntInstallOnlineInfoMapper.findNotOnlineInfoListJoinRelDtl(mntInstallOnlineInfo);
        cvtContentList(mntInstallNotOnlineInfoList);
        return mntInstallNotOnlineInfoList;
    }
    
    
    /**
     * 通过主键ID获取安装点上线信息
     * @param onlineId Primary key
     * @return MntInstallOnlineInfo
     */
    @Override
    public MntInstallOnlineInfo findMntInstallOnlineInfoByOnlineId(Integer onlineId) {
        MntInstallOnlineInfo mntInstallOnlineInfo = mntInstallOnlineInfoMapper.findByPrimaryKey(onlineId);
        return mntInstallOnlineInfo;
    }

    /**
     * 添加安装点上线信息
     * @param MntInstallOnlineInfo
     */
    @Override
    public void saveMntInstallOnlineInfo(MntInstallOnlineInfo mntInstallOnlineInfo) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntInstallOnlineInfo.setDeleteFlag("0");
        mntInstallOnlineInfo.setCreator(currentUser.getUserName());
        mntInstallOnlineInfo.setModifier(currentUser.getUserName());
        mntInstallOnlineInfo.setCreateDate(new Date());
        mntInstallOnlineInfo.setModifyDate(new Date());
        mntInstallOnlineInfoMapper.save(mntInstallOnlineInfo);
        
    }

    /**
     * 更新安装点上线信息
     * 根据MntInstallOnlineInfo的主键更新主键以外的其他字段
     * @param MntInstallOnlineInfo
     */
    @Override
    public void updateMntInstallOnlineInfoByOnlineId(MntInstallOnlineInfo mntInstallOnlineInfo) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntInstallOnlineInfo.setModifier(currentUser.getUserName());
        mntInstallOnlineInfo.setModifyDate(new Date());
        mntInstallOnlineInfoMapper.updateByPrimaryKey(mntInstallOnlineInfo);
        
    }

    /**
     * 根据主键批量删除安装点上线信息 
     * 非物理删除 修改删除标识
     * @param onlineId Primary key
     */
    @Override
    public void deleteMntInstallOnlineInfoByOnlineIds(String onlineIds) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntInstallOnlineInfo mntInstallOnlineInfo = new MntInstallOnlineInfo();
        mntInstallOnlineInfo.setDeleteFlag("1");
        mntInstallOnlineInfo.setModifier(currentUser.getUserName());
        mntInstallOnlineInfo.setModifyDate(new Date());
        String[] onlineIdAry = onlineIds.split(",");
        for(String onlineId : onlineIdAry) {
            mntInstallOnlineInfo.setOnlineId(Integer.parseInt(onlineId));
            mntInstallOnlineInfoMapper.updateByPrimaryKey(mntInstallOnlineInfo);
        }
    }

    /**
     * 获取上线日期列表
     * @param mntInstallOnlineInfo
     * @return
     */
    @Override
    public List<MntInstallOnlineInfo> getDistinctOnlineDateList(
            MntInstallOnlineInfo mntInstallOnlineInfo) {
        List<MntInstallOnlineInfo> onlineDateList = mntInstallOnlineInfoMapper.getDistinctOnlineDateList(mntInstallOnlineInfo);
        return onlineDateList;
    }
    
    private void cvtContentList(List<MntInstallOnlineInfo> MntInstallOnlineInfoList) {
        for(MntInstallOnlineInfo mntInstallOnlineInfo : MntInstallOnlineInfoList) {
            mntInstallOnlineInfo.setProdName(BaseDataCache.getDataName("PROD_INFO", mntInstallOnlineInfo.getProdId()));
            mntInstallOnlineInfo.setModuleName(BaseDataCache.getDataName("PROD_MODULE_ENUM", mntInstallOnlineInfo.getModuleId()));
            mntInstallOnlineInfo.setBaseName(BaseDataCache.getDataName("BASE_NAME_ENUM", mntInstallOnlineInfo.getBaseId()));
            mntInstallOnlineInfo.setRelCode(BaseDataCache.getDataName("REL_CODE", mntInstallOnlineInfo.getRelId()));
            
            mntInstallOnlineInfo.setIsOnlinedTxt(BaseDataCache.getDataName("IS_ONLINED", mntInstallOnlineInfo.getIsOnlined()));
            mntInstallOnlineInfo.setIsRemoteSupportTxt(BaseDataCache.getDataName("IS_REMOTE_SUPPORT", mntInstallOnlineInfo.getIsRemoteSupport()));
            mntInstallOnlineInfo.setIsOnsiteSupportTxt(BaseDataCache.getDataName("IS_ONSITE_SUPPORT", mntInstallOnlineInfo.getIsOnsiteSupport()));
            mntInstallOnlineInfo.setIsFaultTxt(BaseDataCache.getDataName("IS_FAULT", mntInstallOnlineInfo.getIsFault()));
        }
    }

    /**
     * 上线反馈数据导入
     */
    @Override
    public void importOnlineData(List<List<String>> excelData, Integer baseId)
            throws MntDataAccessException {
        
        List<MntInstallOnlineInfo> mntInstallOnlineInfos = new ArrayList<>();
        List<MntInstallOnlineInfo> existOnlineInfos = new ArrayList<>();
        int index = 0;
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        
        //数据存在校验
        if(excelData == null || excelData.size() <= 1) {
            throw new MntDataAccessException("EXCEL没有数据，请检查后重新导入！" );
        }
        
        for(List<String> rowList : excelData) {
            if(index == 0) {
                index++;
                continue;
            }
            
            MntInstallOnlineInfo mntInstallOnlineInfo = new MntInstallOnlineInfo();
            
            
            //1.系统
            String prodName = rowList.get(1);
            MntProd mntProd = new MntProd();
            mntProd.setProdName(prodName);
            mntProd.setDeleteFlag("0");
            List<MntProd> prodList = mntProdMapper.findList(mntProd);
            if(prodList == null || prodList.size() == 0) {
                throw new MntDataAccessException("第" + (index+1) + "行该系统名称不存在，请检查后重新导入！" );
            }
            Integer prodId = prodList.get(0).getProdId();
            mntInstallOnlineInfo.setProdId(prodId);
            
            //2.版本
            String verName = rowList.get(2);
            MntProdVersion mntProdVersion = new MntProdVersion();
            mntProdVersion.setVerName(verName);
            mntProdVersion.setProdId(prodId);
            mntProdVersion.setDeleteFlag("0");
            List<MntProdVersion> verList = mntProdVersionMapper.findList(mntProdVersion);
            if(verList == null || verList.size() == 0) {
                throw new MntDataAccessException("第" + (index+1) + "行该系统下系统版本名称不存在，请检查后重新导入！" );
            }
            String verCode = verList.get(0).getVerCode();
            mntInstallOnlineInfo.setVerCode(verCode);
            
            //5.上线模块
            MntProdModule module = new MntProdModule();
            module.setProdId(prodId);
            module.setVerCode(verCode);
            module.setDeleteFlag("0");
            module.setModuleName(rowList.get(5).trim());
            List<MntProdModule> moduleList = mntProdModuleMapper.findList(module);
            if(moduleList == null || moduleList.size() == 0) {
                throw new MntDataAccessException("第" + (index+1) + "行该模块不存在，请检查后重新导入！" );
            }
            mntInstallOnlineInfo.setModuleId(moduleList.get(0).getModuleId());
            
            
            //6.上线发布版本号
            MntReleaseRec mntReleaseRec = new MntReleaseRec();
            mntReleaseRec.setProdId(prodId);
            mntReleaseRec.setVerCode(verCode);
            mntReleaseRec.setDeleteFlag("0");
            mntReleaseRec.setRelCode(rowList.get(6));
            List<MntReleaseRec> recList = mntReleaseRecMapper.findList(mntReleaseRec);
            if(recList == null || recList.size() == 0) {
                throw new MntDataAccessException("第" + (index+1) + "行该发布版本号不存在，请检查后重新导入！" );
            }
            Integer relId = recList.get(0).getRelId();
            mntInstallOnlineInfo.setRelId(relId);
            
            //7.上线需求或缺陷ID
            MntReleaseRecDtl mntReleaseRecDtl = new MntReleaseRecDtl();
            mntReleaseRecDtl.setRelId(relId);
            mntReleaseRecDtl.setDeleteFlag("0");
            mntReleaseRecDtl.setBaseId(baseId);
            mntReleaseRecDtl.setDtlCode(rowList.get(7).trim());
            List<MntReleaseRecDtl> dtlList = mntReleaseRecDtlMapper.findList(mntReleaseRecDtl);
            if(dtlList == null || dtlList.size() == 0) {
                String baseName = BaseDataCache.getDataName("PROD_INFO", mntInstallOnlineInfo.getProdId());
                throw new MntDataAccessException("第" + (index+1) + "行该需求或缺陷ID号在" + baseName + rowList.get(6) + "发布版本中不存在，请检查后重新导入！" );
            }
            mntInstallOnlineInfo.setRelDtlId(dtlList.get(0).getDtlId());
            
            
            //检查是否已经存在
            List<MntInstallOnlineInfo> onlines = mntInstallOnlineInfoMapper.findList(mntInstallOnlineInfo);
            if(onlines != null && onlines.size() > 0) {
                existOnlineInfos.addAll(onlines);
            }
            
            //0.计划上线日期
            Date planDate = DateUtil.stringToDate2(rowList.get(0), "yyyy-MM-dd");
            if(planDate == null) {
                throw new MntDataAccessException("第" + (index+1) + "行计划上线时间(yyyy/m/d)格式有误，请检查后重新导入！" );
            }
            mntInstallOnlineInfo.setPlanOnlineDate(planDate);
            
            //3.是否申请R&D现场支持
            String isOnsiteSupportTxt = rowList.get(3);
            if("是".equals(isOnsiteSupportTxt)) {
                mntInstallOnlineInfo.setIsOnsiteSupport("1");
            }else {
                mntInstallOnlineInfo.setIsOnsiteSupport("0");
            }
            
            //4.是否申请R&D远程支持
            String isRemoteSupportTxt = rowList.get(4);
            if("是".equals(isRemoteSupportTxt)) {
                mntInstallOnlineInfo.setIsRemoteSupport("1");
            }else {
                mntInstallOnlineInfo.setIsRemoteSupport("0");
            }
            
            //9.延迟上线日期
            mntInstallOnlineInfo.setDelayOnlineDate(DateUtil.stringToDate2(rowList.get(9), "yyyy-MM-dd"));
            
            //10.实际上线日期
            Date onlineDate = DateUtil.stringToDate2(rowList.get(10), "yyyy-MM-dd");
            mntInstallOnlineInfo.setOnlineDate(onlineDate);
            
            //11.是否出现故障
            String isFaultTxt = rowList.get(11);
            if("是".equals(isFaultTxt)) {
                mntInstallOnlineInfo.setIsFault("1");
            }else {
                mntInstallOnlineInfo.setIsFault("0");
            }
            
            //12.未上线原因
            mntInstallOnlineInfo.setUnOnlineReason(rowList.get(12));
            //13.备注
            mntInstallOnlineInfo.setOnlineRemark(rowList.get(13));
            
            
            //是否已经上线
            if(onlineDate != null) {
                mntInstallOnlineInfo.setIsOnlined("1");
            }else {
                mntInstallOnlineInfo.setIsOnlined("0");
            }
            
            
            mntInstallOnlineInfo.setBaseId(baseId);
            mntInstallOnlineInfo.setDeleteFlag("0");
            mntInstallOnlineInfo.setCreator(currentUser.getUserName());
            mntInstallOnlineInfo.setModifier(currentUser.getUserName());
            mntInstallOnlineInfo.setCreateDate(new Date());
            mntInstallOnlineInfo.setModifyDate(new Date());
            
            mntInstallOnlineInfos.add(mntInstallOnlineInfo);
            
            index++;
        }
        
        //保存
        if(mntInstallOnlineInfos.size() > 0) {
            mntInstallOnlineInfoMapper.saveAll(mntInstallOnlineInfos);
        }
        
        //将已存在的置为删除
        if(existOnlineInfos.size() > 0) {
            for(MntInstallOnlineInfo info : existOnlineInfos) {
                info.setDeleteFlag("1");
                info.setModifier(currentUser.getUserName());
                info.setModifyDate(new Date());
                mntInstallOnlineInfoMapper.updateByPrimaryKey(info);
            }
        }
        
    }
}

