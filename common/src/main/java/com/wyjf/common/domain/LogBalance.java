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
     * 1充值，2取款，3下注，4中奖
     */
    private int type;

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
}
