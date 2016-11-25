package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title: MntReleaseRec 
 * @Description: MntReleaseRec Model
 * @Author: Auto Generate 
 * @Date: 2016-4-21
 */
public class MntReleaseRec implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5732524730783918108L;

    /**小版本ID*/
    private Integer relId;

    /**小版本号*/
    private String relCode;

    /**产品编号*/
    private Integer prodId;

    /**产品名称*/
    private String prodName;

    /**大版编号*/
    private String verCode;
    
    /**发布类型*/
    private Integer relType;

    /**版本描述*/
    private String relDesc;

    /**发布时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date relDate;

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

    /**大版编号*/
    private String verName;
    
    /**开始时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    
    /**结束时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
    /**明细列表*/
    List<MntReleaseRecDtl> releaseRecList = new ArrayList<>();
    
    /**安装点，如：省份*/
    private Integer baseId;
    
    private String baseName;
    
    /**明细计数*/
    private Integer dtlCount;
    
    /**发布类型Name*/
    private String relTypeTxt;
    
    public Integer getRelId() {
        return relId;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    public String getRelCode() {
        return relCode;
    }

    public void setRelCode(String relCode) {
        this.relCode = relCode;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getRelType() {
        return relType;
    }

    public void setRelType(Integer relType) {
        this.relType = relType;
    }

    public String getRelDesc() {
        return relDesc;
    }

    public void setRelDesc(String relDesc) {
        this.relDesc = relDesc;
    }

    public Date getRelDate() {
        return relDate;
    }

    public void setRelDate(Date relDate) {
        this.relDate = relDate;
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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
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

    public List<MntReleaseRecDtl> getReleaseRecList() {
        return releaseRecList;
    }

    public void setReleaseRecList(List<MntReleaseRecDtl> releaseRecList) {
        this.releaseRecList = releaseRecList;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public Integer getDtlCount() {
        return dtlCount;
    }

    public void setDtlCount(Integer dtlCount) {
        this.dtlCount = dtlCount;
    }

    public String getRelTypeTxt() {
        return relTypeTxt;
    }

    public void setRelTypeTxt(String relTypeTxt) {
        this.relTypeTxt = relTypeTxt;
    }

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

}