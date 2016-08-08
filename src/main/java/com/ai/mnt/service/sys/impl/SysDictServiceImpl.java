package com.ai.mnt.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.model.sys.SysDict;
import com.ai.mnt.persistence.sys.SysDictMapper;
import com.ai.mnt.service.sys.SysDictService;

@Service
public class SysDictServiceImpl implements SysDictService{

    @Autowired
    SysDictMapper sysDictMapper;
    
    @Override
    public List<SysDict> findAllDict() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SysDict> findDictList(SysDict sysDict) {
        List<SysDict> dictList = sysDictMapper.findList(sysDict);
        return dictList;
    }



    
}
