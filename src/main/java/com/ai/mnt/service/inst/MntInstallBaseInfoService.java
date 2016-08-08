package com.ai.mnt.service.inst;

import java.util.List;

import com.ai.mnt.model.inst.MntInstallBaseInfo;

public interface MntInstallBaseInfoService {

    /**
     * 通过id获取安装点信息
     * @return
     */
    public MntInstallBaseInfo findInstallBaseInfoByBaseId(Integer baseId);
    
    /**
     * 获取所有的安装点信息
     * @return
     */
    public List<MntInstallBaseInfo> findAllMntInstallBaseInfo();
    
    /**
     * 通过安装点信息列表
     * @return
     */
    public List<MntInstallBaseInfo> findInstallBaseInfoList(MntInstallBaseInfo mntInstallBaseInfo);
    
    /**
     * 保存安装点信息
     * @param sysUser
     * @param roleIds
     */
    public void saveInstallBaseInfo(MntInstallBaseInfo mntInstallBaseInfo);
    
    /**
     * 更新安装点信息
     * @param sysUser
     * @param roleIds
     */
    public void updateInstallBaseInfo(MntInstallBaseInfo mntInstallBaseInfo);
    
    
}
