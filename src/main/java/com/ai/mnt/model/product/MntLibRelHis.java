package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.Date;

import com.ai.mnt.model.product.MntModuleLib;
import com.ai.mnt.model.product.MntReleaseRec;

/**
 * @Title: MntLibRelHis 
 * @Description: MntLibRelHis Model
 * @Author: Auto Generate. HE
 * @Date: 2016-5-17
 */
public class MntLibRelHis implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -620600963297694027L;

    /**库文件发布历史明细ID*/
    private Integer id;

    /**库文件编号*/
    private Integer libId;

    /**库文件版本号*/
    private String libCode;
    
    /**库文件版本号*/
    private String outputFile;

    /**MD5值*/
    private String md5Val;

    /**发布版本编号 关联产品小版本发布明细信息表*/
    private Integer relId;

    /**安装点*/
    private Integer baseId;
    
    /**备注*/
    private String remark;

    /**删除标识，1删除，0未删除*/
    private String deleteFlag;

    /**创建人*/
    private String creator;

    /**创建时间*/
    private Date createDate;

    /**修改人*/
    private String modifier;

    /**修改时间*/
    private Date modifyDate;

    //========================================
    private MntModuleLib mntModuleLib = new MntModuleLib();
    
    private MntReleaseRec mntReleaseRec = new MntReleaseRec();
    
    /**安装点*/
    private String baseIdTxt;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLibId() {
        return libId;
    }

    public void setLibId(Integer libId) {
        this.libId = libId;
    }

    public String getLibCode() {
        return libCode;
    }

    public void setLibCode(String libCode) {
        this.libCode = libCode;
    }

    public String getMd5Val() {
        return md5Val;
    }

    public void setMd5Val(String md5Val) {
        this.md5Val = md5Val;
    }

    public Integer getRelId() {
        return relId;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public MntModuleLib getMntModuleLib() {
        return mntModuleLib;
    }

    public void setMntModuleLib(MntModuleLib mntModuleLib) {
        this.mntModuleLib = mntModuleLib;
    }

    public MntReleaseRec getMntReleaseRec() {
        return mntReleaseRec;
    }

    public void setMntReleaseRec(MntReleaseRec mntReleaseRec) {
        this.mntReleaseRec = mntReleaseRec;
    }

    public String getBaseIdTxt() {
        return baseIdTxt;
    }

    public void setBaseIdTxt(String baseIdTxt) {
        this.baseIdTxt = baseIdTxt;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

}