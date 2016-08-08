package com.ai.mnt.model.inst;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title: MntInstallBaseInfo 
 * @Description: MntInstallBaseInfo Model
 * @Author: Auto Generate 
 * @Date: 2016-5-5
 */
public class MntInstallBaseInfo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1399326044411887246L;

    /**安装点编号*/
    private Integer baseId;

    /**安装点名称*/
    private String baseName;

    /***/
    private Integer buId;

    /**安装点描述*/
    private String baseDesc;

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

    /**开始时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    
    /**结束时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
    /**删除标识，1删除，0未删除*/
    private String deleteFlagTxt;
    
    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public Integer getBuId() {
        return buId;
    }

    public void setBuId(Integer buId) {
        this.buId = buId;
    }

    public String getBaseDesc() {
        return baseDesc;
    }

    public void setBaseDesc(String baseDesc) {
        this.baseDesc = baseDesc;
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

    public String getDeleteFlagTxt() {
        return deleteFlagTxt;
    }

    public void setDeleteFlagTxt(String deleteFlagTxt) {
        this.deleteFlagTxt = deleteFlagTxt;
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

}