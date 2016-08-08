package com.ai.mnt.common.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.mnt.model.common.EnumObject;
import com.ai.mnt.model.sys.SysDict;
import com.ai.mnt.service.sys.SysDictService;

/**
 * 添加基础缓存数据
 * @author matrix
 *
 */
@Component
public class BaseDataCache {
    
    private static Logger logger = LoggerFactory.getLogger(BaseDataCache.class);
    
    public static Map<String, Map<Object, EnumObject>> GLOBAL_MAP = new ConcurrentHashMap<>();
    
    public static Date loadTime;
    
    @Autowired
    SysDictService sysDictService;
    
    
    public void init(){
        
        logger.info("===============加载缓存数据===============");
        loadSysDict();
        logger.info("===============加载缓存数据完成===============");
        loadTime = new Date();
    }
    
    /**
     * 加载数据字典
     */
    public void loadSysDict() {
        SysDict sysDict = new SysDict();
        sysDict.setParamSts("1"); //状态正常的数据
        List<SysDict> dictList = sysDictService.findDictList(sysDict);
        for(SysDict temp : dictList) {
            EnumObject enumObject = new EnumObject();
            enumObject.setKey(temp.getParamCode().toString());
            enumObject.setValue(temp.getParamValue());
            enumObject.setOrder(temp.getParamOrder());
            enumObject.setGroupKey(temp.getParamGroupCode());
            enumObject.setGroupValue(temp.getParamGroupName());
            Map<Object, EnumObject> groupMap = GLOBAL_MAP.get(temp.getParamGroupCode());
            if(groupMap == null) {
                Map<Object, EnumObject> map = new HashMap<>();
                map.put(temp.getParamCode(), enumObject);
                GLOBAL_MAP.put(temp.getParamGroupCode(), map);
            }else {
                groupMap.put(temp.getParamCode(), enumObject);
            }
        }
    }
    
    /**
     * 获取KEY对应的枚举值
     * @param groupKey
     * @param key
     * @return
     */
    public static String getDataName(String groupKey, Object key) {
        Map<Object, EnumObject> groupMap = (Map<Object, EnumObject>) GLOBAL_MAP.get(groupKey);
        if(groupMap == null || key == null) {
            return "";
        }
        EnumObject enumObject = groupMap.get(key.toString());
        if(enumObject == null) {
            return "";
        }
        return enumObject.getValue();
    }
    
    /**
     * 返回同组的枚举排序列表
     * @param groupKey
     * @return
     */
    public static List<EnumObject> getDataList(String groupKey) {
        Map<Object, EnumObject> groupMap = (Map<Object, EnumObject>) GLOBAL_MAP.get(groupKey);
        if(groupMap == null) {
            return null;
        }
        List<EnumObject> list = new ArrayList<>();
        for(Entry<Object, EnumObject> entry: groupMap.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<EnumObject>(){
            @Override
            public int compare(EnumObject o1, EnumObject o2) {
                return  o1.getOrder().compareTo(o2.getOrder());
            }
        });
        return list;
    }
    
    /**
     * 清空缓存
     */
    public static void clearCache() {
        GLOBAL_MAP.clear();
    }
    
    
}
