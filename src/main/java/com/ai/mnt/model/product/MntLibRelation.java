package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: MntLibRelation 
 * @Description: MntLibRelation Model
 * @Author: Auto Generate. HE
 * @Date: 2016-5-20
 */
public class MntLibRelation implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 8294214306452532120L;

    /***/
    private Integer id;

    /**库文件编号*/
    private Integer libId;

    /**关联库文件编号*/
    private Integer relLibId;

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

    public Integer getLibId() {
        return libId;
    }

    public void setLibId(Integer libId) {
        this.libId = libId;
    }

    public Integer getRelLibId() {
        return relLibId;
    }

    public void setRelLibId(Integer relLibId) {
        this.relLibId = relLibId;
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


}