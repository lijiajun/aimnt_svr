package com.ai.mnt.model.sys;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @Title: SysUser
 * @Description: SysUser Model
 * @author Auto Genrate
 * @date 2016-4-11
 */
public class SysUser implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1690195488809889342L;

    /** 用户编号 */
    private Integer userId;

    /** 用户名 */
    private String userName;

    /** 用户密码 */
    private String password;

    /** salt */
    private String salt;

    /** 姓名 */
    private String realName;

    /** 安装点，区域 */
    private Integer baseId;

    /** 邮箱 */
    private String email;

    /** 电话号码 */
    private String phoneId;

    /** 头像 */
    private String userAvatar;

    /** 最后登录IP */
    private String lastLoginIp;

    /** 最后登录时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastLoginDate;

    /** 用户状态,1正常,0锁定 */
    private String userSts;

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

    // =========================================================
    /** 开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /** 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /** 用户状态,1正常,0锁定 */
    private String userStsTxt;
    
    /** 安装点，区域 */
    private String baseIdTxt;
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUserSts() {
        return userSts;
    }

    public void setUserSts(String userSts) {
        this.userSts = userSts;
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

    public String getUserStsTxt() {
        return userStsTxt;
    }

    public void setUserStsTxt(String userStsTxt) {
        this.userStsTxt = userStsTxt;
    }

    public String getBaseIdTxt() {
        return baseIdTxt;
    }

    public void setBaseIdTxt(String baseIdTxt) {
        this.baseIdTxt = baseIdTxt;
    }

    @Override
    public String toString() {
        return "SysUser [userId=" + userId + ", userName=" + userName
                + ", password=" + password + ", salt=" + salt + ", realName="
                + realName + ", baseId=" + baseId + ", email=" + email
                + ", phoneId=" + phoneId + ", userAvatar=" + userAvatar
                + ", lastLoginIp=" + lastLoginIp + ", lastLoginDate="
                + lastLoginDate + ", userSts=" + userSts + ", remark=" + remark
                + ", creator=" + creator + ", createDate=" + createDate
                + ", modifier=" + modifier + ", modifyDate=" + modifyDate + "]";
    }

}