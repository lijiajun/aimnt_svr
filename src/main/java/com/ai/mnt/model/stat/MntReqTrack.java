package com.ai.mnt.model.stat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: MntReqTrack
 * @Description: MntReqTrack Model
 * @Author: Auto Generate.
 * @Date: 2016-8-3
 */
public class MntReqTrack implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer trackId;

    /** 安装点 */
    private Integer baseId;

    /** 安装点名称 */
    private String baseName;

    /** 安装点名称_DMP */
    private String dmpBaseName;

    /** 产品编号 */
    private Integer prodId;

    /** 产品名称 */
    private String prodName;

    /** 产品名称_DMP */
    private String dmpProdName;

    /** 需求编号 */
    private String bizNo;

    /** 需求名称 */
    private String bizName;

    /** 需求类型 */
    private String bizType;

    /** 需求来源 */
    private String bizSrc;

    /** 需求当前状态 */
    private String bizSts;

    /** 节点责任人 */
    private String nodePerson;

    /** 优先级 */
    private String priority;

    /** 紧急程度 */
    private String urgentLevel;

    /** 录入日期 */
    private Date submitDate;

    /** 处理时长(天) */
    private Integer dealDays;

    /** 期望发布日期 */
    private Date askEndDate;

    /** 计划发布日期 */
    private Date planReleaseDate;

    /** 计划上线日期 */
    private Date planOnlineDate;

    /** 计划上线日期_DMP */
    private Date dmpUpdateTime;

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

    /** 修改时间 */
    private int count;

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

    public String getDmpBaseName() {
        return dmpBaseName;
    }

    public void setDmpBaseName(String dmpBaseName) {
        this.dmpBaseName = dmpBaseName;
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

    public String getDmpProdName() {
        return dmpProdName;
    }

    public void setDmpProdName(String dmpProdName) {
        this.dmpProdName = dmpProdName;
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

    public Date getAskEndDate() {
        return askEndDate;
    }

    public void setAskEndDate(Date askEndDate) {
        this.askEndDate = askEndDate;
    }

    public Date getPlanReleaseDate() {
        return planReleaseDate;
    }

    public void setPlanReleaseDate(Date planReleaseDate) {
        this.planReleaseDate = planReleaseDate;
    }

    public Date getPlanOnlineDate() {
        return planOnlineDate;
    }

    public void setPlanOnlineDate(Date planOnlineDate) {
        this.planOnlineDate = planOnlineDate;
    }

    public Date getDmpUpdateTime() {
        return dmpUpdateTime;
    }

    public void setDmpUpdateTime(Date dmpUpdateTime) {
        this.dmpUpdateTime = dmpUpdateTime;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MntReqTrack [baseName=" + baseName
                + ", prodName=" + prodName + ", bizSts=" + bizSts
                + ",  count=" + count + "]";
    }

}