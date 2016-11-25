package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title: MntProdVersion 
 * @Description: MntProdVersion Model
 * @Author: Auto Generate 
 * @Date: 2016-4-14
 */
public class MntProdVersion implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**版本ID*/
    private Integer verId;

    /**产品编号*/
    private Integer prodId;

    /**产品名称*/
    private String prodName;
    
    /**版本号*/
    private String verCode;

    /**版本名称*/
    private String verName;

    /**版本描述*/
    private String verDesc;

    /**版本生效时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validDate;

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


    public Integer getVerId() {
        return verId;
    }

    public void setVerId(Integer verId) {
        this.verId = verId;
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

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public String getVerDesc() {
        return verDesc;
    }

    public void setVerDesc(String verDesc) {
        this.verDesc = verDesc;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
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

    @Override
    public String toString() {
        return "MntProdVersion [verId=" + verId + ", prodId=" + prodId
                + ", verCode=" + verCode + ", verName=" + verName
                + ", verDesc=" + verDesc + ", validDate=" + validDate
                + ", deleteFlag=" + deleteFlag + ", creator=" + creator
                + ", createDate=" + createDate + ", modifier=" + modifier
                + ", modifyDate=" + modifyDate + "]";
    }


}