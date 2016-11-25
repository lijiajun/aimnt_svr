package com.ai.mnt.model.inst;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ai.mnt.model.product.MntReleaseRecDtl;

/**
 * @Title: MntInstallOnlineInfo
 * @Description: MntInstallOnlineInfo Model
 * @Author: Auto Generate. HE
 * @Date: 2016-5-23
 */
public class MntInstallOnlineInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 上线编号 */
    private Integer onlineId;

    /** 安装点编号 */
    private Integer baseId;

    /** 产品编号 */
    private Integer prodId;

    /** 产品版本 */
    private String verCode;

    /** 发布版本号 */
    private Integer relId;
    
    /** 发布明细编号 需求或故障 */
    private Integer relDtlId;

    /** 上线模块 */
    private Integer moduleId;

    /** 是否申请R&D现场支持 1是 0否 */
    private String isOnsiteSupport;

    /** 是否申请R&D远程支持 1是 0否 */
    private String isRemoteSupport;

    /** 计划上线日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date planOnlineDate;

    /** 实际上线日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date onlineDate;

    /** 是否已上线 1是 0否 */
    private String isOnlined;

    /** 未上线原因 */
    private String unOnlineReason;

    /** 延期上线日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date delayOnlineDate;

    /**是否出现故障,1是 0否*/
    private String isFault;
    
    /** 备注 */
    private String onlineRemark;

    /** 删除标识，1删除，0未删除 */
    private String deleteFlag;

    /** 创建人 */
    private String creator;

    /** 创建时间 */
    private Date createDate;

    /** 修改人 */
    private String modifier;

    /** 修改时间 */
    private Date modifyDate;

    // ===========================================
    /** 开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /** 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /** 安装点编号 */
    private String baseName;

    /** 产品编号 */
    private String prodName;

    /** 发布明细编号 需求或故障 */
    private String dtlName;

    /** 上线模块 */
    private String moduleName;

    /** 是否申请R&D现场支持 1是 0否 */
    private String isOnsiteSupportTxt;

    /** 是否申请R&D远程支持 1是 0否 */
    private String isRemoteSupportTxt;
    
    /** 是否已上线 1是 0否 */
    private String isOnlinedTxt;
    
    /** 发布版本号 */
    private String relCode;
    
    private String isFaultTxt;
    
    private MntReleaseRecDtl mntReleaseRecDtl = new MntReleaseRecDtl();

    public Integer getOnlineId() {
        return onlineId;
    }

    public void setOnlineId(Integer onlineId) {
        this.onlineId = onlineId;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getRelCode() {
        return relCode;
    }

    public void setRelCode(String relCode) {
        this.relCode = relCode;
    }

    public Integer getRelDtlId() {
        return relDtlId;
    }

    public void setRelDtlId(Integer relDtlId) {
        this.relDtlId = relDtlId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getIsOnsiteSupport() {
        return isOnsiteSupport;
    }

    public void setIsOnsiteSupport(String isOnsiteSupport) {
        this.isOnsiteSupport = isOnsiteSupport;
    }

    public String getIsRemoteSupport() {
        return isRemoteSupport;
    }

    public void setIsRemoteSupport(String isRemoteSupport) {
        this.isRemoteSupport = isRemoteSupport;
    }

    public Date getPlanOnlineDate() {
        return planOnlineDate;
    }

    public void setPlanOnlineDate(Date planOnlineDate) {
        this.planOnlineDate = planOnlineDate;
    }

    public Date getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    public String getIsOnlined() {
        return isOnlined;
    }

    public void setIsOnlined(String isOnlined) {
        this.isOnlined = isOnlined;
    }

    public String getUnOnlineReason() {
        return unOnlineReason;
    }

    public void setUnOnlineReason(String unOnlineReason) {
        this.unOnlineReason = unOnlineReason;
    }

    public Date getDelayOnlineDate() {
        return delayOnlineDate;
    }

    public void setDelayOnlineDate(Date delayOnlineDate) {
        this.delayOnlineDate = delayOnlineDate;
    }

    public String getOnlineRemark() {
        return onlineRemark;
    }

    public void setOnlineRemark(String onlineRemark) {
        this.onlineRemark = onlineRemark;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDtlName() {
        return dtlName;
    }

    public void setDtlName(String dtlName) {
        this.dtlName = dtlName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public MntReleaseRecDtl getMntReleaseRecDtl() {
        return mntReleaseRecDtl;
    }

    public void setMntReleaseRecDtl(MntReleaseRecDtl mntReleaseRecDtl) {
        this.mntReleaseRecDtl = mntReleaseRecDtl;
    }

    public String getIsOnsiteSupportTxt() {
        return isOnsiteSupportTxt;
    }

    public void setIsOnsiteSupportTxt(String isOnsiteSupportTxt) {
        this.isOnsiteSupportTxt = isOnsiteSupportTxt;
    }

    public String getIsRemoteSupportTxt() {
        return isRemoteSupportTxt;
    }

    public void setIsRemoteSupportTxt(String isRemoteSupportTxt) {
        this.isRemoteSupportTxt = isRemoteSupportTxt;
    }

    public String getIsOnlinedTxt() {
        return isOnlinedTxt;
    }

    public void setIsOnlinedTxt(String isOnlinedTxt) {
        this.isOnlinedTxt = isOnlinedTxt;
    }

    public Integer getRelId() {
        return relId;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    public String getIsFault() {
        return isFault;
    }

    public void setIsFault(String isFault) {
        this.isFault = isFault;
    }

    public String getIsFaultTxt() {
        return isFaultTxt;
    }

    public void setIsFaultTxt(String isFaultTxt) {
        this.isFaultTxt = isFaultTxt;
    }

}