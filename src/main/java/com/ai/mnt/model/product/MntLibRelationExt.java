package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: MntLibRelationExt 
 * @Description: MntLibRelationExt Model
 * @Author: Auto Generate.
 * @Date: 2016-7-11
 */
public class MntLibRelationExt implements Serializable{

    private static final long serialVersionUID = 1L;

    /***/
    private Integer id;

    /**产品编号*/
    private Integer prodId;
    
    /**产品编号*/
    private String prodName;

    /**产品大版本编号*/
    private String verCode;

    /**库文件*/
    private String libName;

    /**关联库文件*/
    private String relLibName;

    /**关联描述*/
    private String relDesc;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getRelLibName() {
        return relLibName;
    }

    public void setRelLibName(String relLibName) {
        this.relLibName = relLibName;
    }

    public String getRelDesc() {
        return relDesc;
    }

    public void setRelDesc(String relDesc) {
        this.relDesc = relDesc;
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


}