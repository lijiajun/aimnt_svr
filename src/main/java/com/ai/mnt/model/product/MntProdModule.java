package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title: MntProdModule
 * @Description: MntProdModule Model
 * @Author: Auto Generate. HE
 * @Date: 2016-5-11
 */
public class MntProdModule implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 267982345064211020L;

    /** 功能编号 */
    private Integer moduleId;

    /** 模块英文名称 */
    private String moduleNameEn;

    /** 模块中文名称 */
    private String moduleNameCn;

    /** 产品编号 */
    private Integer prodId;

    /** 产品大版本编号 */
    private String verCode;

    /** 父模块编号 */
    private Integer parentModuleId;

    /** 输出类型 1程序、2库、3模板 */
    private String outputType;

    /** 输出物 */
    private String output;

    /** 启动命令 */
    private String startCommand;

    /** SVN路径 */
    private String svnPath;

    /** 模块描述 */
    private String moduleDesc;

    /** 是否在用 1是，0否 */
    private String isUsed;

    /** 删除标识，1删除，0未删除 */
    private String deleteFlag;

    /** 备注 */
    private String remark;

    /** 创建人 */
    private String creator;

    /** 创建时间 */
    private Date createDate;

    /** 修改人 */
    private String modifier;

    /** 修改时间 */
    private Date modifyDate;

    // =============================================
    /** 产品名称 */
    private String prodName;

    /** 产品大版本号 */
    private String verName;

    /** 模块名称 */
    private String moduleName;

    /** 父模块编号 */
    private String parentModuleName;

    /** 是否在用 1是，0否 */
    private String isUsedTxt;

    /** 输出类型 1程序、2库、3模板 */
    private String outputTypeTxt;

    /** 开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /** 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleNameEn() {
        return moduleNameEn;
    }

    public void setModuleNameEn(String moduleNameEn) {
        this.moduleNameEn = moduleNameEn;
    }

    public String getModuleNameCn() {
        return moduleNameCn;
    }

    public void setModuleNameCn(String moduleNameCn) {
        this.moduleNameCn = moduleNameCn;
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

    public Integer getParentModuleId() {
        return parentModuleId;
    }

    public void setParentModuleId(Integer parentModuleId) {
        this.parentModuleId = parentModuleId;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getStartCommand() {
        return startCommand;
    }

    public void setStartCommand(String startCommand) {
        this.startCommand = startCommand;
    }

    public String getSvnPath() {
        return svnPath;
    }

    public void setSvnPath(String svnPath) {
        this.svnPath = svnPath;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getParentModuleName() {
        return parentModuleName;
    }

    public void setParentModuleName(String parentModuleName) {
        this.parentModuleName = parentModuleName;
    }

    public String getIsUsedTxt() {
        return isUsedTxt;
    }

    public void setIsUsedTxt(String isUsedTxt) {
        this.isUsedTxt = isUsedTxt;
    }

    public String getOutputTypeTxt() {
        return outputTypeTxt;
    }

    public void setOutputTypeTxt(String outputTypeTxt) {
        this.outputTypeTxt = outputTypeTxt;
    }

}