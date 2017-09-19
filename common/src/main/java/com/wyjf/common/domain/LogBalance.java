package com.wyjf.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/19.
 */
@Entity
public class LogBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lid;

    /**
     * 用户ID
     */
    private long uid;

    /**
     * 变动金额
     */
    private double amount;

    /**
     * 0云投记录，1充值记录，2提款记录
     */
    private int type;

    /**
     * 子类型标识(1-5下注盘口类型, 6返现, 7中奖)
     */
    private Integer tag;

    /**
     * 引用记录ID
     */
    private Long refId;

    /**
     * 操作日志时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime logTime;

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }
}
