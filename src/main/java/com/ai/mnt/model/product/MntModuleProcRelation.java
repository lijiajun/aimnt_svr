package com.ai.mnt.model.product;

import java.io.Serializable;


/**
 * @Title: MntModuleProcRelation 
 * @Description: MntModuleProcRelation Model
 * @Author: Auto Generate 
 * @Date: 2016-5-8
 */
public class MntModuleProcRelation implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1324197965794502262L;

    /**编号*/
    private Integer id;

    /**进程编号*/
    private Integer procId;

    /**功能模块编号*/
    private Integer moduleId;

    /**依赖描述*/
    private String relDesc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcId() {
        return procId;
    }

    public void setProcId(Integer procId) {
        this.procId = procId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getRelDesc() {
        return relDesc;
    }

    public void setRelDesc(String relDesc) {
        this.relDesc = relDesc;
    }


}