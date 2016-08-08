package com.ai.mnt.model.common;

public class EnumObject {
    
    private String key;
    
    private String value;
    
    private Integer order;
    
    private String groupKey;
    
    private String groupValue;
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getGroupKey() {
        return groupKey;
    }
    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }
    public String getGroupValue() {
        return groupValue;
    }
    public void setGroupValue(String groupValue) {
        this.groupValue = groupValue;
    }

}
