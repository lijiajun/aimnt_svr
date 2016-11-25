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
import com.ai.mnt.model.inst.MntInstallBaseInfo;
import com.ai.mnt.model.product.MntModuleLib;
import com.ai.mnt.model.product.MntProd;
import com.ai.mnt.model.product.MntProdModule;
import com.ai.mnt.model.product.MntProdVersion;
import com.ai.mnt.model.product.MntReleaseRec;
import com.ai.mnt.model.sys.SysDict;
import com.ai.mnt.persistence.product.MntProdModuleMapper;
import com.ai.mnt.service.inst.MntInstallBaseInfoService;
import com.ai.mnt.service.product.MntModuleLibService;
import com.ai.mnt.service.product.MntProdService;
import com.ai.mnt.service.product.MntProdVerService;
import com.ai.mnt.service.product.MntReleaseRecService;
import com.ai.mnt.service.sys.SysDictService;
import com.ai.mnt.service.sys.SysUserService;

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
    public MntProdService mntProdService;
    
    @Autowired
    MntProdVerService mntProdVerService;
    
    @Autowired
    MntReleaseRecService mntReleaseRecService;
    
    @Autowired
    SysDictService sysDictService;
    
//    @Autowired
//    SysResourceService sysResourceService;
    
//    @Autowired
//    SysRoleService sysRoleService;
    
    @Autowired
    MntInstallBaseInfoService mntInstallBaseInfoService;
    
    @Autowired
    MntProdModuleMapper mntProdModuleMapper;
    
    @Autowired
    MntModuleLibService mntModuleLibService;
    
//    @Autowired
//    MntTaskInfoService mntTaskInfoService;
    
    @Autowired
    SysUserService sysUserService;
    
//    @Autowired
//    MntArticleService mntArticleService;
    
    public void init(){
        
        logger.info("===============加载缓存数据===============");
        loadProdInfo();
        loadProdVer();
        loadRelCode();
        loadProdModule();
        loadSysDict();
//        loadSysResource();
//        loadSysRole();
        loadInstallBaseInfo();
        loadModuleLib();
//        loadMntTask();
//        loadUserName();
//        loadArticleType();
        logger.info("===============加载缓存数据完成===============");
        loadTime = new Date();
    }
    
    /**
     * 加载文章类型映射
     */
//    private void loadArticleType() {
//        Map<Object, EnumObject> map = new HashMap<>();
//        List<MntArticleType> articleTypeList = mntArticleService.findAllMntArticleType();
//        for(MntArticleType temp : articleTypeList) {
//            EnumObject enumObject = new EnumObject();
//            enumObject.setKey(temp.getTypeId().toString());
//            enumObject.setValue(temp.getTypeCn());
//            enumObject.setOrder(temp.getTypeId());
//            enumObject.setGroupKey("ARTICLE_TYPE");
//            enumObject.setGroupValue("产品类型映射");
//            map.put(temp.getTypeId().toString(), enumObject);
//        }
//        GLOBAL_MAP.put("ARTICLE_TYPE", map);
//        
//    }

    /**
     * 加载产品映射
     */
    public void loadProdInfo() {
        Map<Object, EnumObject> map = new HashMap<>();
        MntProd mntProd = new MntProd();
        mntProd.setDeleteFlag("0");
        List<MntProd> findAllMntProd = mntProdService.findMntProdList(mntProd);
        for(MntProd temp : findAllMntProd) {
            EnumObject enumObject = new EnumObject();
            enumObject.setKey(temp.getProdId().toString());
            enumObject.setValue(temp.getProdName());
            enumObject.setOrder(temp.getProdId());
            enumObject.setGroupKey("PROD_INFO");
            enumObject.setGroupValue("产品类型映射");
            map.put(temp.getProdId().toString(), enumObject);
        }
        GLOBAL_MAP.put("PROD_INFO", map);
    }
    
    /**
     * 加载产品版本映射
     */
    public void loadProdVer() {
        Map<Object, EnumObject> map = new HashMap<>();
        MntProdVersion prodVersion = new MntProdVersion();
        prodVersion.setDeleteFlag("0");
        List<MntProdVersion> prodVerList = mntProdVerService.findProdVerList(prodVersion);
        for(MntProdVersion mntProdVersion : prodVerList) {
            EnumObject enumObject = new EnumObject();
            enumObject.setKey(mntProdVersion.getVerCode());
            enumObject.setValue(mntProdVersion.getVerName());
            enumObject.setOrder(mntProdVersion.getProdId());
            enumObject.setGroupKey("PROD_VER");
            enumObject.setGroupValue("产品版本映射");
            map.put(mntProdVersion.getVerCode(),enumObject);
        }
        GLOBAL_MAP.put("PROD_VER", map);
    }

    /**
     * 加载产品发布信息映射
     */
    public void loadRelCode() {
        Map<Object, EnumObject> map = new HashMap<>();
        MntReleaseRec releaseRec = new MntReleaseRec();
        releaseRec.setDeleteFlag("0");
        List<MntReleaseRec> relRecList = mntReleaseRecService.findReleaseRecList(releaseRec);
        for(MntReleaseRec relRec : relRecList) {
            EnumObject enumObject = new EnumObject();
            enumObject.setKey(relRec.getRelId().toString());
            enumObject.setValue(relRec.getRelCode());
            enumObject.setOrder(relRec.getRelId());
            enumObject.setGroupKey("REL_CODE");
            enumObject.setGroupValue("发布版本映射");
            map.put(relRec.getRelId().toString(), enumObject);
        }
        GLOBAL_MAP.put("REL_CODE", map);
    }

    /**
     * 加载产品模块映射
     */
    public void loadProdModule() {
        Map<Object, EnumObject> map = new HashMap<>();
        MntProdModule mntProdModule = new MntProdModule();
        mntProdModule.setDeleteFlag("0");
        List<MntProdModule> mntProdModuleList = mntProdModuleMapper.findList(mntProdModule);
        for(MntProdModule prodModule : mntProdModuleList) {
            EnumObject enumObject = new EnumObject();
            enumObject.setKey(prodModule.getModuleId().toString());
            enumObject.setValue(prodModule.getModuleNameCn() + "(" +prodModule.getModuleNameEn() + ")");
            enumObject.setOrder(prodModule.getModuleId());
            enumObject.setGroupKey("PROD_MODULE_ENUM");
            enumObject.setGroupValue("模块映射");
            map.put(prodModule.getModuleId().toString(), enumObject);
        }
        GLOBAL_MAP.put("PROD_MODULE_ENUM", map);
    }
    
    /**
     * 加载任务映射
     */
//    public void loadMntTask() {
//        Map<Object, EnumObject> map = new HashMap<>();
//        
//        MntTaskInfo mntTaskInfo = new MntTaskInfo();
//        mntTaskInfo.setDeleteFlag("0");
//        List<MntTaskInfo> mntTaskInfoList = mntTaskInfoService.findMntTaskInfoList(mntTaskInfo);
//        for(MntTaskInfo taskInfo : mntTaskInfoList) {
//            EnumObject enumObject = new EnumObject();
//            enumObject.setKey(taskInfo.getTaskId().toString());
//            enumObject.setValue(taskInfo.getTaskName());
//            enumObject.setOrder(taskInfo.getTaskId());
//            enumObject.setGroupKey("TASK_NAME_ENUM");
//            enumObject.setGroupValue("任务名称映射");
//            map.put(taskInfo.getTaskId().toString(), enumObject);
//        }
//        GLOBAL_MAP.put("TASK_NAME_ENUM", map);
//    }
    
    /**
     * 加载用户映射
     */
//    public void loadUserName() {
//        Map<Object, EnumObject> map = new HashMap<>();
//        SysUser sysUser = new SysUser();
//        sysUser.setUserSts("1");
//        List<SysUser> sysUserList = sysUserService.findUserList(sysUser);
//        for(SysUser user : sysUserList) {
//            EnumObject enumObject = new EnumObject();
//            enumObject.setKey(user.getUserName().toString());
//            enumObject.setValue(user.getUserName() + "/" + user.getRealName());
//            enumObject.setOrder(user.getUserId());
//            enumObject.setGroupKey("USER_NAME_ENUM");
//            enumObject.setGroupValue("任务名称映射");
//            map.put(user.getUserName().toString(), enumObject);
//        }
//        GLOBAL_MAP.put("USER_NAME_ENUM", map);
//    }
    
    /**
     * 加载资源信息映射
     */
//    public void loadSysResource() {
//        Map<Object, EnumObject> map = new HashMap<>();
//        SysResource sysRes = new SysResource();
//        sysRes.setResSts("1");
//        List<SysResource> resList = sysResourceService.findAllSysResource();
//        for(SysResource sysResource : resList) {
//            EnumObject enumObject = new EnumObject();
//            enumObject.setKey(sysResource.getResId().toString());
//            enumObject.setValue(sysResource.getResName());
//            enumObject.setOrder(sysResource.getResId());
//            enumObject.setGroupKey("SYS_RESOURCE");
//            enumObject.setGroupValue("资源映射");
//            map.put(sysResource.getResId().toString(), enumObject);
//        }
//        EnumObject enumObject = new EnumObject();
//        enumObject.setKey("-1");
//        enumObject.setValue("根节点");
//        enumObject.setGroupKey("SYS_RESOURCE");
//        enumObject.setGroupValue("资源映射");
//        enumObject.setOrder(0);
//        map.put("-1", enumObject);
//        GLOBAL_MAP.put("SYS_RESOURCE", map);
//    }
    
    /**
     * 加载角色
     */
//    public void loadSysRole() {
//        Map<Object, EnumObject> map = new HashMap<>();
//        SysRole sysRole = new SysRole();
//        sysRole.setRoleSts("1");
//        List<SysRole> roleList = sysRoleService.findRoleList(sysRole);
//        for(SysRole temp : roleList) {
//            EnumObject enumObject = new EnumObject();
//            enumObject.setKey(temp.getRoleId().toString());
//            enumObject.setValue(temp.getRoleName());
//            enumObject.setOrder(temp.getRoleId());
//            enumObject.setGroupKey("SYS_ROLE_ENUM");
//            enumObject.setGroupValue("角色");
//            map.put(temp.getRoleId().toString(), enumObject);
//        }
//        GLOBAL_MAP.put("SYS_ROLE_ENUM", map);
//    }
    
    /**
     * 加载安装点映射
     */
    public void loadInstallBaseInfo() {
        Map<Object, EnumObject> map = new HashMap<>();
        MntInstallBaseInfo baseInfo = new MntInstallBaseInfo();
        baseInfo.setDeleteFlag("0"); //未删除的数据
        List<MntInstallBaseInfo> baseInfoList = mntInstallBaseInfoService.findInstallBaseInfoList(baseInfo);
        for(MntInstallBaseInfo temp : baseInfoList) {
            EnumObject enumObject = new EnumObject();
            enumObject.setKey(temp.getBaseId().toString());
            enumObject.setValue(temp.getBaseName());
            enumObject.setOrder(temp.getBaseId());
            enumObject.setGroupKey("BASE_NAME_ENUM");
            enumObject.setGroupValue("安装点");
            
            map.put(temp.getBaseId().toString(), enumObject);
        }
        GLOBAL_MAP.put("BASE_NAME_ENUM", map);
    }
    
    /**
     * 加载Lib库映射
     */
    public void loadModuleLib() {
        Map<Object, EnumObject> map = new HashMap<>();
        MntModuleLib mntModuleLib = new MntModuleLib();
        mntModuleLib.setDeleteFlag("0"); //未删除的数据
        List<MntModuleLib> mntModuleLibList = mntModuleLibService.findMntModuleLibList(mntModuleLib);
        for(MntModuleLib temp : mntModuleLibList) {
            EnumObject enumObject = new EnumObject();
            enumObject.setKey(temp.getLibId().toString());
            enumObject.setValue(temp.getLibName());
            enumObject.setOrder(temp.getLibId());
            enumObject.setGroupKey("LIB_NAME_ENUM");
            enumObject.setGroupValue("安装点");
            map.put(temp.getLibId().toString(), enumObject);
        }
        GLOBAL_MAP.put("LIB_NAME_ENUM", map);
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
    
    /**
     * 刷新缓存
     */
    public void refreshCache() {
        //清空缓存
        clearCache();
        //刷新缓存
        init();
    }
}
