package com.ai.mnt.service.product.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mnt.common.cache.BaseDataCache;
import com.ai.mnt.common.util.DateUtil;
import com.ai.mnt.exception.MntDataAccessException;
import com.ai.mnt.model.inst.MntInstallBaseInfo;
import com.ai.mnt.model.inst.MntInstallOnlineInfo;
import com.ai.mnt.model.product.MntProd;
import com.ai.mnt.model.product.MntReleaseRec;
import com.ai.mnt.model.product.MntReleaseRecDtl;
import com.ai.mnt.model.sys.SysUser;
import com.ai.mnt.persistence.inst.MntInstallBaseInfoMapper;
import com.ai.mnt.persistence.inst.MntInstallOnlineInfoMapper;
import com.ai.mnt.persistence.product.MntProdMapper;
import com.ai.mnt.persistence.product.MntProdVersionMapper;
import com.ai.mnt.persistence.product.MntReleaseRecDtlMapper;
import com.ai.mnt.persistence.product.MntReleaseRecMapper;
import com.ai.mnt.persistence.sys.SysDictMapper;
import com.ai.mnt.service.product.MntReleaseRecDtlService;
import com.ai.mnt.service.product.MntReleaseRecService;

@Service
public class MntReleaseRecDtlServiceImpl implements MntReleaseRecDtlService{

    @Autowired
    MntReleaseRecDtlMapper mntReleaseRecDtlMapper;
    
    @Autowired
    MntReleaseRecService mntReleaseRecService;
    
//    @Autowired
//    UserRealm userRealm;
    
    @Autowired
    MntProdMapper mntProdMapper;
    
    @Autowired
    MntProdVersionMapper mntProdVersionMapper;
    
    @Autowired
    MntReleaseRecMapper mntReleaseRecMapper;
    
    @Autowired
    MntInstallBaseInfoMapper mntInstallBaseInfoMapper;
    
    @Autowired
    SysDictMapper sysDictMapper;
    
    @Autowired
    MntInstallOnlineInfoMapper mntInstallOnlineInfoMapper;
    
    /**
     * 获取发布明细
     * @return
     */
    @Override
    public List<MntReleaseRecDtl> findRelDtlList(MntReleaseRecDtl mntReleaseRecDtl) {
        
        List<MntReleaseRecDtl> recDtllist = mntReleaseRecDtlMapper.findList(mntReleaseRecDtl);
        cvtContentList(recDtllist);
        return recDtllist;
    }
    
    /**
     * 获取发布明细
     * @return
     */
    @Override
    public List<MntReleaseRecDtl> findRecAndDtlJoinList(MntReleaseRecDtl mntReleaseRecDtl) {
        
        List<MntReleaseRecDtl> recDtllist = mntReleaseRecDtlMapper.findRecAndDtlJoinList(mntReleaseRecDtl);
        cvtContentList(recDtllist);
        return recDtllist;
    }
    
    private void cvtContentList(List<MntReleaseRecDtl> relDtlList) {
        for(MntReleaseRecDtl mntReleaseRecDtl : relDtlList) {
            MntReleaseRec releaseRec = mntReleaseRecDtl.getMntReleaseRec();
            if(releaseRec != null) {
                releaseRec.setProdName(BaseDataCache.getDataName("PROD_INFO", releaseRec.getProdId()));
                releaseRec.setVerName(BaseDataCache.getDataName("PROD_VER", releaseRec.getVerCode()));
            }
            mntReleaseRecDtl.setDtlTypeTxt(BaseDataCache.getDataName("REL_DTL_TYPE", mntReleaseRecDtl.getDtlType()));
            mntReleaseRecDtl.setBaseIdTxt(BaseDataCache.getDataName("BASE_NAME_ENUM", mntReleaseRecDtl.getBaseId()));
            mntReleaseRecDtl.setRelCode(BaseDataCache.getDataName("REL_CODE", mntReleaseRecDtl.getRelId()));
        }
    }

    @Override
    public List<MntReleaseRecDtl> getStatResForBaseId(
            MntReleaseRecDtl mntReleaseRecDtl) {
        List<MntReleaseRecDtl> list = mntReleaseRecDtlMapper.getStatResForBaseId(mntReleaseRecDtl);
        return list;
    }

    @Override
    public void saveRelDtl(MntReleaseRecDtl mntReleaseRecDtl) {
        
        
        MntReleaseRec releaseRec = mntReleaseRecService.findReleaseRecById(mntReleaseRecDtl.getRelId());
        if(!"BIZBILLING_VER_00000".equals(releaseRec.getRelCode()) && 
                !"BIZBILLING_VER_11111".equals(releaseRec.getRelCode()) &&
                !"BIZBILLING_VER_99999".equals(releaseRec.getRelCode())) {
            //重复验证
            MntReleaseRecDtl mntReleaseRecDtl2 = new MntReleaseRecDtl();
            mntReleaseRecDtl2.setRelId(mntReleaseRecDtl.getRelId());
            mntReleaseRecDtl2.setDtlCode(mntReleaseRecDtl.getDtlCode());
            MntReleaseRec mntReleaseRec = new MntReleaseRec();
            mntReleaseRec.setProdId(mntReleaseRecDtl.getProdId());
            mntReleaseRecDtl2.setMntReleaseRec(mntReleaseRec);
            List<MntReleaseRecDtl> recDtllist = mntReleaseRecDtlMapper.findRecAndDtlJoinList(mntReleaseRecDtl2);
            if(recDtllist != null && recDtllist.size() > 0) {
                throw new MntDataAccessException("该产品的相同版本号下已经存在相同的明细信息，请检查后重新添加！" );
            }
        }
        
        
        
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntReleaseRecDtl.setDeleteFlag("0");
        mntReleaseRecDtl.setCreator(currentUser.getUserName());
        mntReleaseRecDtl.setModifier(currentUser.getUserName());
        mntReleaseRecDtl.setCreateDate(new Date());
        mntReleaseRecDtl.setModifyDate(new Date());
        
        mntReleaseRecDtlMapper.save(mntReleaseRecDtl);
        
    }

    @Override
    public void updateRelDtlById(MntReleaseRecDtl mntReleaseRecDtl) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntReleaseRecDtl.setModifier(currentUser.getUserName());
        mntReleaseRecDtl.setModifyDate(new Date());
        mntReleaseRecDtlMapper.updateByPrimaryKey(mntReleaseRecDtl);
        
        int dtlId = mntReleaseRecDtl.getDtlId();
        int relId = mntReleaseRecDtl.getRelId();
        int baseId = mntReleaseRecDtl.getBaseId();
        MntInstallOnlineInfo mntInstallOnlineInfo = new MntInstallOnlineInfo();
        mntInstallOnlineInfo.setRelDtlId(dtlId);
        mntInstallOnlineInfo.setBaseId(baseId);
       List<MntInstallOnlineInfo> mntInstallBaseInfos = mntInstallOnlineInfoMapper.findList(mntInstallOnlineInfo);
        if (mntInstallBaseInfos != null && mntInstallBaseInfos.size() > 0 ) {
            int onlineId = mntInstallBaseInfos.get(0).getOnlineId();
            MntInstallOnlineInfo mntInstallOnlineInfo1 = new MntInstallOnlineInfo();
            mntInstallOnlineInfo1.setOnlineId(onlineId);
            mntInstallOnlineInfo1.setRelId(relId);
            mntInstallOnlineInfoMapper.updateRelIdByDtlId(mntInstallOnlineInfo1);
        }
        
       
        
    }

    @Override
    public void deleteRelDtlByIds(String dtlIds) {
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        MntReleaseRecDtl mntReleaseRecDtl = new MntReleaseRecDtl();
        mntReleaseRecDtl.setDeleteFlag("1");
        mntReleaseRecDtl.setModifier(currentUser.getUserName());
        mntReleaseRecDtl.setModifyDate(new Date());
        String[] dtlIdAry = dtlIds.split(",");
        for(String dtlId : dtlIdAry) {
            mntReleaseRecDtl.setDtlId(Integer.parseInt(dtlId));
            mntReleaseRecDtlMapper.updateByPrimaryKey(mntReleaseRecDtl);
        }
    }

    @Override
    public MntReleaseRecDtl getRelDtlByDtlId(Integer dtlId) {
        MntReleaseRecDtl releaseRecDtl = mntReleaseRecDtlMapper.findByPrimaryKey(dtlId);
        return releaseRecDtl;
    }

    @Override
    public void saveReleaseRecWithBaseIds(MntReleaseRecDtl mntReleaseRecDtl,
            String[] baseIdAry) {
        
        MntReleaseRec releaseRec = mntReleaseRecService.findReleaseRecById(mntReleaseRecDtl.getRelId());
        if(!"BIZBILLING_VER_00000".equals(releaseRec.getRelCode()) && 
                !"BIZBILLING_VER_11111".equals(releaseRec.getRelCode()) &&
                !"BIZBILLING_VER_99999".equals(releaseRec.getRelCode())) {
            //重复验证
            MntReleaseRecDtl mntReleaseRecDtl2 = new MntReleaseRecDtl();
            mntReleaseRecDtl2.setRelId(mntReleaseRecDtl.getRelId());
            mntReleaseRecDtl2.setDtlCode(mntReleaseRecDtl.getDtlCode());
            MntReleaseRec mntReleaseRec = new MntReleaseRec();
            mntReleaseRec.setProdId(mntReleaseRecDtl.getProdId());
            mntReleaseRecDtl2.setMntReleaseRec(mntReleaseRec);
            List<MntReleaseRecDtl> recDtllist = mntReleaseRecDtlMapper.findRecAndDtlJoinList(mntReleaseRecDtl2);
            if(recDtllist != null && recDtllist.size() > 0) {
                throw new MntDataAccessException("该产品的相同版本号下已经存在相同的明细信息，请检查后重新添加！" );
            }
        }
        
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        mntReleaseRecDtl.setDeleteFlag("0");
        mntReleaseRecDtl.setCreator(currentUser.getUserName());
        mntReleaseRecDtl.setModifier(currentUser.getUserName());
        mntReleaseRecDtl.setCreateDate(new Date());
        mntReleaseRecDtl.setModifyDate(new Date());
        
        for(String baseId : baseIdAry) {
            mntReleaseRecDtl.setBaseId(Integer.parseInt(baseId));
            mntReleaseRecDtlMapper.save(mntReleaseRecDtl);
        }
        
    }

    @Override
    public void importRelData(List<List<String>> excelData) throws MntDataAccessException {
        List<MntReleaseRecDtl> releaseResults = new ArrayList<>();
        int index = 0;
        SysUser currentUser = new SysUser(); //userRealm.getCurrentUser();
        for(List<String> rowList : excelData) {
            if(index == 0) {
                index++;
                continue;
            }
            MntReleaseRecDtl mntReleaseRecDtl = new MntReleaseRecDtl();
            
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
            
            //2.版本
            String verCode = rowList.get(1);
            String relCode = rowList.get(2);
            mntReleaseRec.setRelCode(relCode);
            mntReleaseRec.setVerCode(verCode);
            mntReleaseRec.setProdId(prodId);
            mntReleaseRec.setDeleteFlag("0");
            List<MntReleaseRec> verList = mntReleaseRecMapper.findList(mntReleaseRec);
            if (verList == null || verList.size() == 0) {
                throw new MntDataAccessException("第" + (index+1) + "行该系统下系统版本名称不存在，请检查后重新导入！" );
            }
            Integer relId = verList.get(0).getRelId();
            
            //3.省份
            MntInstallBaseInfo mntInstallBaseInfo = new MntInstallBaseInfo();
            mntInstallBaseInfo.setBaseName(rowList.get(3));
            mntInstallBaseInfo.setDeleteFlag("0");
            List<MntInstallBaseInfo> insts = mntInstallBaseInfoMapper.findList(mntInstallBaseInfo);
            if(insts == null || insts.size() == 0) {
                throw new MntDataAccessException("第" + (index+1) + "行该发布省份不存在，请检查后重新导入！" );
            }
            
            //4.发布类型 需求、故障、bug
            int dtlType =0 ;
            if ("需求".equals(rowList.get(4))){
                dtlType = 1 ;
            }else if ("bug".equals(rowList.get(4))) {
               dtlType = 2;
            }else if("故障".equals(rowList.get(4))){
                dtlType = 3 ;
            }else {
                throw new MntDataAccessException("第" + (index+1) + "行该发布类型不存在，请检查后重新导入！" );
            }
            
            //判断 是否重复 有三个版本号是可以重复的
            if(!"BIZBILLING_VER_00000".equals(rowList.get(2)) && 
                    !"BIZBILLING_VER_11111".equals(rowList.get(2)) &&
                    !"BIZBILLING_VER_99999".equals(rowList.get(2))) {
                //重复验证
                MntReleaseRecDtl mntReleaseRecDtl2 = new MntReleaseRecDtl();
                mntReleaseRecDtl2.setRelId(relId);
                mntReleaseRecDtl2.setDtlCode(rowList.get(5));
                MntReleaseRec mntReleaseRec2 = new MntReleaseRec();
                mntReleaseRec2.setProdId(prodId);
                mntReleaseRecDtl2.setMntReleaseRec(mntReleaseRec2);
                List<MntReleaseRecDtl> recDtllist = mntReleaseRecDtlMapper.findRecAndDtlJoinList(mntReleaseRecDtl2);
                if(recDtllist != null && recDtllist.size() > 0) {
                    throw new MntDataAccessException("第" + (index+1) + "行该产品的相同版本号下已经存在相同的明细信息，请检查后重新添加！" );
                }
            }
            
            mntReleaseRecDtl.setRelId(relId);
            mntReleaseRecDtl.setBaseId(insts.get(0).getBaseId());
            mntReleaseRecDtl.setDtlType(dtlType);
            mntReleaseRecDtl.setDtlCode(rowList.get(5));
            mntReleaseRecDtl.setDtlName(rowList.get(6));
            mntReleaseRecDtl.setDtlDesc(rowList.get(7));
            
            mntReleaseRecDtl.setDeleteFlag("0");
            mntReleaseRecDtl.setCreator(currentUser.getUserName());
            mntReleaseRecDtl.setModifier(currentUser.getUserName());
            mntReleaseRecDtl.setCreateDate(DateUtil.getCurrDate());
            mntReleaseRecDtl.setModifyDate(DateUtil.getCurrDate());
            
            releaseResults.add(mntReleaseRecDtl);
            index++;
        }
        
        if(releaseResults.size() > 0) {
           // mntReleaseRecDtlMapper.saveAll(releaseResults);
            mntReleaseRecDtlMapper.saveAll(releaseResults);
        }
        
        
    }

}
