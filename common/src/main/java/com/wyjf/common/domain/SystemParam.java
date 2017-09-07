package com.wyjf.common.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/9/7.
 */
@Entity
public class SystemParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String dataKey;

    private String dataVal;

    private String comment;

    private int sortId;

    private long pid;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataVal() {
        return dataVal;
    }

    public void setDataVal(String dataVal) {
        this.dataVal = dataVal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "SystemParam{" +
                "id=" + id +
                ", dataKey='" + dataKey + '\'' +
                ", dataVal='" + dataVal + '\'' +
                ", comment='" + comment + '\'' +
                ", sortId=" + sortId +
                ", pid=" + pid +
                '}';
    }
}
