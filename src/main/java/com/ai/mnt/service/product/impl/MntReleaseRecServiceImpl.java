package com.ai.mnt.service.product.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.common.util.DateUtil;
import com.ai.mnt.exception.MntDataAccessException;
import com.ai.mnt.model.product.MntProd;
import com.ai.mnt.model.product.MntReleaseRec;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.product.MntProdMapper;
import com.ai.mnt.persistence.product.MntReleaseRecMapper;
import com.ai.mnt.service.product.MntReleaseRecService;

@Service
public class MntReleaseRecServiceImpl implements MntReleaseRecService {
    
    @Autowired
    MntReleaseRecMapper mntReleaseRecMapper;

//    @Autowired
//    UserRealm userRealm;
    
    @Autowired
    MntProdMapper mntProdMapper;
    
    
    //弹出 版本明细信息
    @Override
    public List<MntReleaseRec> findReleaseRecByRelCode(String  relCode) {
        List<MntReleaseRec> relRecCodelist = mntReleaseRecMapper.findByRelCode(relCode);
        return relRecCodelist;
    }
    
    
    /**
     * 获取全部发布记录
     */
    @Override
    public List<MntReleaseRec> findAllMntReleaseRec() {
        List<MntReleaseRec> relReclist = mntReleaseRecMapper.findAll();
        cvtContentList(relReclist);
        return relReclist;
    }

    @Override
    public MntReleaseRec findReleaseRecById(Integer relId) {
        MntReleaseRec relRec = mntReleaseRecMapper.findByPrimaryKey(relId);
        return relRec;
    }

    @Override
    public List<MntReleaseRec> findReleaseRecList(MntReleaseRec mntReleaseRec) {
        List<MntReleaseRec> relReclist = mntReleaseRecMapper.findList(mntReleaseRec);
        cvtContentList(relReclist);
        return relReclist;
    }
    
    /**
     * 统计各安装点的需求量
     * @param mntReleaseRecDtl
     * @return
     */
    @Override
    public List<MntReleaseRec> getStatResForBaseId(MntReleaseRec mntReleaseRec) {
        List<MntReleaseRec> relReclist = mntReleaseRecMapper.getStatResForBaseId(mntReleaseRec);
        cvtContentList(relReclist);
        return relReclist;
    }
    
    /**
     * 统计各产品的需求量
     * @param mntReleaseRecDtl
     * @return
     */
    @Override
    public List<MntReleaseRec> getStatResForProdId(MntReleaseRec mntReleaseRec) {
        List<MntReleaseRec> relReclist = mntReleaseRecMapper.getStatResForProdId(mntReleaseRec);
        cvtContentList(relReclist);
        return relReclist;
    }
    
    private void cvtContentList(List<MntReleaseRec> relList) {
        for(MntReleaseRec mntReleaseRec : relList) {
            mntReleaseRec.setProdName(BaseDataCache.getDataName("PROD_INFO", mntReleaseRec.getProdId()));
            mntReleaseRec.setVerName(BaseDataCache.getDataName("PROD_VER", mntReleaseRec.getVerCode()));
            mntReleaseRec.setRelTypeTxt(BaseDataCache.getDataName("REL_TYPE", mntReleaseRec.getRelType()));
            mntReleaseRec.setBaseName(BaseDataCache.getDataName("BASE_NAME_ENUM", mntReleaseRec.getBaseId()));
        }
    }

    @Override
    public void saveReleaseRec(MntReleaseRec mntReleaseRec) throws MntDataAccessException{
        
        if(!"BIZBILLING_VER_00000".equals(mntReleaseRec.getRelCode()) && 
                !"BIZBILLING_VER_11111".equals(mntReleaseRec.getRelCode()) &&
                !"BIZBILLING_VER_99999".equals(mntReleaseRec.getRelCode())) {
            //重复验证
            MntReleaseRec mntReleaseRec2 = new MntReleaseRec();
            mntReleaseRec2.setRelCode(mntReleaseRec.getRelCode());
            mntReleaseRec2.setProdId(mntReleaseRec.getProdId());
            List<MntReleaseRec> relReclist = mntReleaseRecMapper.findList(mntReleaseRec2);
            if(relReclist != null && relReclist.size() > 0) {
                throw new MntDataAccessException("该产品的版本号信息已经存在，请检查后重新添加！" );
            }
        }
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntReleaseRec.setDeleteFlag("0");
        mntReleaseRec.setCreator(currentUser.getUserName());
        mntReleaseRec.setModifier(currentUser.getUserName());
        mntReleaseRec.setCreateDate(new Date());
        mntReleaseRec.setModifyDate(new Date());
        
        mntReleaseRecMapper.save(mntReleaseRec);
        
    }

    @Override
    public void updateReleaseRec(MntReleaseRec mntReleaseRec) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntReleaseRec.setModifier(currentUser.getUserName());
        mntReleaseRec.setModifyDate(new Date());
        mntReleaseRecMapper.updateByPrimaryKey(mntReleaseRec);
    }

    @Override
    public void deleteReleaseRec(String relIds) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntReleaseRec mntReleaseRec = new MntReleaseRec();
        mntReleaseRec.setDeleteFlag("1");
        mntReleaseRec.setModifier(currentUser.getUserName());
        mntReleaseRec.setModifyDate(new Date());
        String[] relIdAry = relIds.split(",");
        for(String relId : relIdAry) {
            mntReleaseRec.setRelId(Integer.parseInt(relId));
            mntReleaseRecMapper.updateByPrimaryKey(mntReleaseRec);
        }
    }
    
    //导入
    @Override
    public void importRelData(List<List<String>> excelData) throws MntDataAccessException {
        List<MntReleaseRec> releaseResults = new ArrayList<>();
        int index = 0;
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        for(List<String> rowList : excelData) {
            if(index == 0) {
                index++;
                continue;
            }
            
            MntReleaseRec mntReleaseRec = new MntReleaseRec();
            
            //1.系统
            String prodName = rowList.get(0);
            MntProd mntProd = new MntProd();
            mntProd.setProdName(prodName);
            mntProd.setDeleteFlag("0");
            List<MntProd> prodList = mntProdMapper.findList(mntProd);
            if(prodList == null || prodList.size() == 0) {
                throw new MntDataAccessException("第" + (index+1) + "行该系统名称不存在，请检查后重新导入！" );
            }
            Integer prodId = prodList.get(0).getProdId();
            
            
          //判断 是否重复 有三个版本号是可以重复的
            if(!"BIZBILLING_VER_00000".equals(rowList.get(2)) && 
                    !"BIZBILLING_VER_11111".equals(rowList.get(2)) &&
                    !"BIZBILLING_VER_99999".equals(rowList.get(2))) {
                //重复验证
                MntReleaseRec mntReleaseRec3 = new MntReleaseRec();
                mntReleaseRec3.setRelCode(rowList.get(2));
                mntReleaseRec3.setProdId(prodId);
                List<MntReleaseRec> relReclist = mntReleaseRecMapper.findList(mntReleaseRec3);
                if(relReclist != null && relReclist.size() > 0) {
                    throw new MntDataAccessException("第" + (index+1) +"行该产品的版本号信息已经存在，请检查后重新添加！" );
                }
            }
            
            
            
            mntReleaseRec.setRelCode(rowList.get(2));
            mntReleaseRec.setProdId(prodId);
            mntReleaseRec.setVerCode(rowList.get(1));
            //发布类型
            int relType = 0;
            if ("全量".equals(rowList.get(4)) ) {
                relType = 2 ;
            }else if ("PATCH".equals(rowList.get(4))) {
                relType = 1 ;
            }else {
                throw new MntDataAccessException("第" + (index+1) + "行该发布类型不存在，请检查后重新导入！" );
            }
            mntReleaseRec.setRelType(relType);
            
            //如果描述为空，捕获异常，进行处理
            try {
                mntReleaseRec.setRelDesc(rowList.get(5));
            } catch (IndexOutOfBoundsException e) {
                mntReleaseRec.setRelDesc("");
            }
            
            
            //发布时间
            Date releaseDate = DateUtil.stringToDate2(rowList.get(3), "yyyy-MM-dd");
            if(releaseDate == null) {
                throw new MntDataAccessException("第" + (index+1) + "行发布时间(yyyy/m/d)格式有误，请检查后重新导入！" );
            }
            mntReleaseRec.setRelDate(releaseDate);

            
            mntReleaseRec.setDeleteFlag("0");
            mntReleaseRec.setCreator(currentUser.getUserName());
            mntReleaseRec.setModifier(currentUser.getUserName());
            mntReleaseRec.setCreateDate(DateUtil.getCurrDate());
            mntReleaseRec.setModifyDate(DateUtil.getCurrDate());
            
            releaseResults.add(mntReleaseRec);
            index++;
        }
        
        if(releaseResults.size() > 0) {
           // mntReleaseRecDtlMapper.saveAll(releaseResults);
            mntReleaseRecMapper.saveAll(releaseResults);
        }
        
        
    }

}
