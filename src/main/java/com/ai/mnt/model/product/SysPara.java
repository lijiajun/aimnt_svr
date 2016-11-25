package com.ai.mnt.model.product;

import java.io.Serializable;

/**
 * @Title: SysPara 
 * @Description: SysPara Model
 * @Author: Auto Generate. HE
 * @Date: 2016-5-23
 */
public class SysPara implements Serializable{

    private static final long serialVersionUID = 1L;

    /**参数编号*/
    private Integer paraId;

    /**地区代码，系统中对应的地区代码表内容，如果是0表示对所有的地区有效*/
    private Integer regionCode;

    /**县区代码，系统中对应的县区代码表内容，如果是0表示对所有的县区有效*/
    private Integer countyCode;

    /**参数代码*/
    private String paramCode;

    /**参数名称*/
    private String paramName;

    /**所属系统
            系统基础参数所属的子系统：
            1 －－ 系统管理
            2 －－ 业务管理
            3 －－ 综合客服
            4 －－ 帐务管理
            5 －－ 帐务处理
            6 －－ 接口管理*/
    private Integer paramClass;

    /**参数类型 参数的数据类别：(1--Char  2--Number  3--Boolean  4？Date 5－Long 6-String 7-Double)SYS_BASE_TYPE.code_type=1*/
    private String paramDataType;

    /**参数取值*/
    private String paramValue;

    /**参数说明*/
    private String paramDesc;

    /**安装点*/
    private Integer baseId;

    /**所属产品*/
    private Integer prodId;

    /**产品版本*/
    private String verCode;

    /**发布版本*/
    private String relCode;


    public Integer getParaId() {
        return paraId;
    }

    public void setParaId(Integer paraId) {
        this.paraId = paraId;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Integer getParamClass() {
        return paramClass;
    }

    public void setParamClass(Integer paramClass) {
        this.paramClass = paramClass;
    }

    public String getParamDataType() {
        return paramDataType;
    }

    public void setParamDataType(String paramDataType) {
        this.paramDataType = paramDataType;
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


}