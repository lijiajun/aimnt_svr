package com.ai.mnt.model.product;

import java.io.Serializable;

/**
 * @Title: SysBaseType 
 * @Description: SysBaseType Model
 * @Author: Auto Generate. HE
 * @Date: 2016-5-23
 */
public class SysBaseType implements Serializable{

    private static final long serialVersionUID = 1L;

    /**编号*/
    private Integer id;

    /**表名*/
    private String tableName;

    /**列名*/
    private String colName;

    /**码值*/
    private Integer codeValue;

    /**码值名称*/
    private String cname;

    /**排序号*/
    private Integer sortId;

    /**枚举名称*/
    private String nameLoc;

    /**枚举值*/
    private String enumName;

    /**系统编号*/
    private Integer systemId;

    /**是否在用*/
    private Integer isUsed;

    /**备注*/
    private String remark;

    /**安装点*/
    private Integer baseId;

    /**所属产品*/
    private Integer prodId;

    /**产品版本*/
    private String verCode;

    /**发布版本*/
    private String relCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Integer getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(Integer codeValue) {
        this.codeValue = codeValue;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getNameLoc() {
        return nameLoc;
    }

    public void setNameLoc(String nameLoc) {
        this.nameLoc = nameLoc;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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