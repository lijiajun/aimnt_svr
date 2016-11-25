package com.ai.mnt.service.inst;

import java.util.List;

import com.ai.mnt.model.inst.MntInstallOnlineInfo;

/**
 * @Title: MntInstallOnlineInfoService 
 * @Description: MntInstallOnlineInfoService ServicePackage
 * @Author: Auto Generate. HE
 * @Date: 2016-5-23
 */

public interface MntInstallOnlineInfoService {
    
    /**
     * 获取所有安装点上线信息列表
     * @return List<MntInstallOnlineInfo>
     */
    public List<MntInstallOnlineInfo> findAllMntInstallOnlineInfo();
    
    
    /**
     * 获取安装点上线信息列表
     * @param inst
     * @return List<MntInstallOnlineInfo>
     */
    public List<MntInstallOnlineInfo> findMntInstallOnlineInfoList(MntInstallOnlineInfo mntInstallOnlineInfo);
    
    /**
     * 通过主键ID获取安装点上线信息
     * @param onlineId Primary key
     * @return MntInstallOnlineInfo
     */
    public MntInstallOnlineInfo findMntInstallOnlineInfoByOnlineId(Integer onlineId);
    
    /**
     * 添加安装点上线信息
     * @param MntInstallOnlineInfo
     */
    public void saveMntInstallOnlineInfo(MntInstallOnlineInfo mntInstallOnlineInfo);
    
    /**
     * 更新安装点上线信息
     * 根据MntInstallOnlineInfo的主键更新主键以外的其他字段
     * @param MntInstallOnlineInfo
     */
    public void updateMntInstallOnlineInfoByOnlineId(MntInstallOnlineInfo mntInstallOnlineInfo);
    
    /**
     * 根据主键批量删除安装点上线信息 
     * 非物理删除 修改删除标识
     * @param onlineId Primary key
     */
    public void deleteMntInstallOnlineInfoByOnlineIds(String onlineIds);
    
    /**
     * 获取上线日期列表
     * @param mntInstallOnlineInfo
     * @return
     */
    public List<MntInstallOnlineInfo> getDistinctOnlineDateList(MntInstallOnlineInfo mntInstallOnlineInfo);


    public List<MntInstallOnlineInfo> findOnlineInfoListJoinRelDtl(
            MntInstallOnlineInfo mntInstallOnlineInfo);

    public List<MntInstallOnlineInfo> findNotOnlineInfoListJoinRelDtl(
            MntInstallOnlineInfo mntInstallOnlineInfo);


    public void importOnlineData(List<List<String>> excelData, Integer baseId) throws Exception;
}
