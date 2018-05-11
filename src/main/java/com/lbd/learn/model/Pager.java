package com.lbd.learn.model;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {

    private static final long serialVersionUID = -5179306777751955415L;

    //当前页码
    private int currentPage;

    //总页数
    private int totalPage;

    //每页条数
    private int pageSize;

    //总共的记录
    private int totalRecord;

    //要显示的数据
    private List<T> dataList;

    public Pager() {
    }

    /**
     * 构造函数完成分页（subList分页）
     * @param pageSize
     * @param pageNum
     * @param sourceList
     */
    public Pager(int pageSize, int pageNum, List<T> sourceList) {
        if (null == sourceList) {
            return;
        }
        this.pageSize = pageSize;
        this.totalRecord = sourceList.size();
        this.totalPage = this.totalRecord / this.pageSize;
        this.currentPage = this.totalPage > pageNum ? pageNum : this.totalPage;

        /*起始索引*/
        int fromIndex = this.pageSize * (this.currentPage - 1);
        /*结束索引*/
        int endIndex = this.pageSize * this.currentPage > this.totalPage ? this.totalPage : this.pageSize * this.currentPage;

        this.dataList = sourceList.subList(fromIndex, endIndex);
    }

    public Pager(int currentPage, int totalPage, int pageSize, int totalRecord, List<T> dataList) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.dataList = dataList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
