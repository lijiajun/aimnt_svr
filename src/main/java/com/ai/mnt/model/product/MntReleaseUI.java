package com.ai.mnt.model.product;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * @Title: MntReleaseRec 
 * @Description: MntReleaseRec Model
 * @Author: Auto Generate 
 * @Date: 2016-4-21
 */
public class MntReleaseUI extends MntReleaseRecDtl implements Serializable{

    
    /**
     * 
     */
    private static final long serialVersionUID = -8924366077857765142L;

    /**产品编号*/
    private Integer prodId;
    
    /**大版本号*/
    private String verCode;
    
    /**小版本号*/
    private String relCode;
    
    /**开始时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    
    /**结束时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

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
    
    
}