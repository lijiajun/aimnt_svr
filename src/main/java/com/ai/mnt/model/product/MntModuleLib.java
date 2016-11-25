package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: MntModuleLib 
 * @Description: MntModuleLib Model
 * @Author: Auto Generate. HE
 * @Date: 2016-5-12
 */
public class MntModuleLib implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 3787258312356028683L;

    /**库文件编号*/
    private Integer libId;

    /**库文件名称*/
    private String libName;
    
    /**业务库类型*/
    private String libType;

    /**所属模块*/
    private Integer moduleId;

    /**库文件描述*/
    private String libDesc;

    /**是否在用*/
    private String isUsed;

    /**删除标识，1删除，0未删除*/
    private String deleteFlag;

    /**备注*/
    private String remark;

    /**创建人*/
    private String creator;

    /**创建时间*/
    private Date createDate;

    /**修改人*/
    private String modifier;

    /**修改时间*/
    private Date modifyDate;

    //======================
    /**所属模块信息*/
    private MntProdModule mntProdModule = new MntProdModule();
    
    /**关联库*/
    List<MntLibRelation> mntLibRelations = new ArrayList<>();
    
    /**所属模块*/
    private String moduleName;
    
    /**是否在用*/
    private String isUsedTxt;
    
    /**业务库类型*/
    private String libTypeTxt;
    
    public Integer getLibId() {
        return libId;
    }

    public void setLibId(Integer libId) {
        this.libId = libId;
    }

    public String getLibName() {
        return libName;
    }

    public String getLibType() {
        return libType;
    }

    public void setLibType(String libType) {
        this.libType = libType;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getLibDesc() {
        return libDesc;
    }

    public void setLibDesc(String libDesc) {
        this.libDesc = libDesc;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public MntProdModule getMntProdModule() {
        return mntProdModule;
    }

    public void setMntProdModule(MntProdModule mntProdModule) {
        this.mntProdModule = mntProdModule;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getIsUsedTxt() {
        return isUsedTxt;
    }

    public void setIsUsedTxt(String isUsedTxt) {
        this.isUsedTxt = isUsedTxt;
    }

    public List<MntLibRelation> getMntLibRelations() {
        return mntLibRelations;
    }

    public void setMntLibRelations(List<MntLibRelation> mntLibRelations) {
        this.mntLibRelations = mntLibRelations;
    }

    public String getLibTypeTxt() {
        return libTypeTxt;
    }

    public void setLibTypeTxt(String libTypeTxt) {
        this.libTypeTxt = libTypeTxt;
    }
    
}