package com.ai.mnt.model.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: SysDict 
 * @Description: SysDict Model
 * @Author: Auto Generate 
 * @Date: 2016-4-24
 */
public class SysDict implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5557337131404750057L;

    /**编号*/
    private Integer id;

    /**参数组*/
    private String paramGroupCode;

    /**参数组名称*/
    private String paramGroupName;

    /**参数编码*/
    private String paramCode;

    /**参数值*/
    private String paramValue;

    /**参数名称*/
    private String paramDesc;

    /**顺序编号*/
    private Integer paramOrder;

    /**状态，1正常2失效*/
    private String paramSts;

    /**创建人*/
    private String creator;

    /**创建时间*/
    private Date createDate;

    /**修改人*/
    private String modifier;

    /**修改时间*/
    private Date modifyDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamGroupCode() {
        return paramGroupCode;
    }

    public void setParamGroupCode(String paramGroupCode) {
        this.paramGroupCode = paramGroupCode;
    }

    public String getParamGroupName() {
        return paramGroupName;
    }

    public void setParamGroupName(String paramGroupName) {
        this.paramGroupName = paramGroupName;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public Integer getParamOrder() {
        return paramOrder;
    }

    public void setParamOrder(Integer paramOrder) {
        this.paramOrder = paramOrder;
    }

    public String getParamSts() {
        return paramSts;
    }

    public void setParamSts(String paramSts) {
        this.paramSts = paramSts;
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


}