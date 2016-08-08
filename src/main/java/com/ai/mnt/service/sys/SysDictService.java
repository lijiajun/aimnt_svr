package com.ai.mnt.service.sys;

import java.util.List;

import com.ai.mnt.model.sys.SysDict;

public interface SysDictService {

    /**
     * 获取所有数据字典
     * @return
     */
    public List<SysDict> findAllDict();

    /**
     * 获取数据字典列表
     * @return
     */
    public List<SysDict> findDictList(SysDict sysDict);
    
}
