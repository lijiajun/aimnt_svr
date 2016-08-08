package com.ai.mnt.model.common;

import java.io.Serializable;

public class Pagination implements Serializable{

    
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int pageSize = 8;
    
    private int currentPage = 1;
    
    private int start = 0;
    
    private long totalCount = 0;
    
    private long totalPage = 0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
    
    
    
}
