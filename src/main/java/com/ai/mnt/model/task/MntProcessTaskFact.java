package com.ai.mnt.model.task;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: MntProcessTaskFact 
 * @Description: MntProcessTaskFact Model
 * @Author: Auto Generate.
 * @Date: 2016-8-4
 */
public class MntProcessTaskFact implements Serializable{

    private static final long serialVersionUID = 1L;

    /**编号*/
    private Integer id;

    /**任务编号*/
    private Integer taskKey;

    /**任务状态*/
    private String taskSts;

    /**任务日期*/
    private String taskDate;

    /**任务开始时间*/
    private Date beginTime;

    /**任务结束时间*/
    private Date endTime;

    /**执行结果描述*/
    private String result;

    /**任务创建时间*/
    private Date createDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(Integer taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskSts() {
        return taskSts;
    }

    public void setTaskSts(String taskSts) {
        this.taskSts = taskSts;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}