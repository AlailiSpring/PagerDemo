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
