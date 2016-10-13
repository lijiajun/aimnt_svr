package com.ai.mnt.model.stat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: MntReqTrack 
 * @Description: MntReqTrack Model
 * @Author: Auto Generate.
 * @Date: 2016-8-3
 */
public class DmpBizBilling implements Serializable{

    private static final long serialVersionUID = 1L;

    /**编号*/
    private Integer trackId;

    /**安装点*/
    private Integer baseId;

    /**安装点名称*/
    private String baseName;

    /**产品编号*/
    private Integer prodId;

    /**产品名称*/
    private String prodName;

    /**需求编号*/
    private String bizNo;

    /**需求名称*/
    private String bizName;

    /**需求类型*/
    private String bizType;

    /**需求来源*/
    private String bizSrc;

    /**需求当前状态*/
    private String bizSts;

    /**节点责任人*/
    private String nodePerson;
    
    /**提交人*/
    private String submitPerson;

    /**优先级*/
    private String priority;

    /**紧急程度*/
    private String urgentLevel;

    /**录入日期*/
    private Date submitDate;

    /**处理时长(天)*/
    private Integer dealDays;

    /**期望发布日期*/
    private String askEndDate;

    /**计划发布日期*/
    private String planReleaseDate;

    /**计划上线日期*/
    private String planOnlineDate;
    
    /**DMP更新日期*/
    private Date dmpUpdateTime;

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


    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

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

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizSrc() {
        return bizSrc;
    }

    public void setBizSrc(String bizSrc) {
        this.bizSrc = bizSrc;
    }

    public String getBizSts() {
        return bizSts;
    }

    public void setBizSts(String bizSts) {
        this.bizSts = bizSts;
    }

    public String getNodePerson() {
        return nodePerson;
    }

    public void setNodePerson(String nodePerson) {
        this.nodePerson = nodePerson;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUrgentLevel() {
        return urgentLevel;
    }

    public void setUrgentLevel(String urgentLevel) {
        this.urgentLevel = urgentLevel;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Integer getDealDays() {
        return dealDays;
    }

    public void setDealDays(Integer dealDays) {
        this.dealDays = dealDays;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public String getAskEndDate() {
        return askEndDate;
    }

    public void setAskEndDate(String askEndDate) {
        this.askEndDate = askEndDate;
    }

    public String getPlanReleaseDate() {
        return planReleaseDate;
    }

    public void setPlanReleaseDate(String planReleaseDate) {
        this.planReleaseDate = planReleaseDate;
    }

    public String getPlanOnlineDate() {
        return planOnlineDate;
    }

    public void setPlanOnlineDate(String planOnlineDate) {
        this.planOnlineDate = planOnlineDate;
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

    public Date getDmpUpdateTime() {
        return dmpUpdateTime;
    }

    public void setDmpUpdateTime(Date dmpUpdateTime) {
        this.dmpUpdateTime = dmpUpdateTime;
    }

    public String getSubmitPerson() {
        return submitPerson;
    }

    public void setSubmitPerson(String submitPerson) {
        this.submitPerson = submitPerson;
    }


}