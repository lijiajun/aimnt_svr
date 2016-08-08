package com.ai.mnt.common.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * 添加基础缓存数据
 * @author matrix
 *
 */
public class CacheUtil {
    
    public static Logger logger = LoggerFactory.getLogger(CacheUtil.class);
    
    CacheManager cacheManager;
    
    String cacheName;
    
    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public Cache getCache() {
        return cacheManager.getCache(cacheName);
    }
    
    public void put(String key, Object value) {  
        Cache cache = getCache();  
//        Element element = new Element(key, value);  
        cache.put(key, value);  
    }  
  
//    public Object get(String key) {  
//        Cache cache = getCache();  
//        Element element = cache.get(key);  
//        return element == null ? null : element.getObjectValue();  
//    }
//  
//    public void remove(String key) {  
//        Cache cache = getCache();  
//        cache.remove(key);  
//    }  
    
    
    
}
