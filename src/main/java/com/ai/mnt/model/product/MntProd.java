package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: MntProd 
 * @Description: MntProd Model
 * @Author: Auto Generate 
 * @Date: 2016-4-14
 */
public class MntProd implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -1181672546260841678L;

    /**产品编号*/
    private Integer prodId;

    /**产品名称*/
    private String prodName;

    /**产品类型*/
    private Integer prodType;

    /**产品描述*/
    private String prodDesc;

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

    public Integer getProdType() {
        return prodType;
    }

    public void setProdType(Integer prodType) {
        this.prodType = prodType;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
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

    @Override
    public String toString() {
        return "MntProd [prodId=" + prodId + ", prodName=" + prodName
                + ", prodType=" + prodType + ", prodDesc=" + prodDesc
                + ", deleteFlag=" + deleteFlag + ", creator=" + creator
                + ", createDate=" + createDate + ", modifier=" + modifier
                + ", modifyDate=" + modifyDate + "]";
    }


}