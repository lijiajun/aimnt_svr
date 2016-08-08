package com.ai.mnt.persistence.base;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Title: CrudMapper 
 * @Description: Mapper操作基础接口
 * @author He
 * @date 2016年4月10日 下午4:31:36 
 * 
 * @param <T>
 * @param <ID>
 */
public interface CrudMapper<T, ID extends Serializable> {
    
    /**
     * 保存单条记录
     * @param entity
     */
    public int save(T entity);
    
    /**
     * 保存数据列表
     * @param entities
     */
    public int saveAll(List<T> entities);
    
    /**
     * 通过主键ID删除数据
     * @param id
     */
    public void deleteByPrimaryKey(ID id);
    
    /**
     * 通过主键集合批量删除数据
     * @param entities
     */
    public void deleteList(List<ID> ids);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    public int updateByPrimaryKey(T entity);
    
    /**
     * 通过主键ID获取数据
     * @param id
     * @return
     */
    public T findByPrimaryKey(ID id);
    
    /**
     * 获取数据列表
     * @param entity
     * @return
     */
    public List<T> findList(T entity);
    
    /**
     * 获取所有数据列表
     * @param entity
     * @return
     */
    public List<T> findAll();

}
