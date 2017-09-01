package com.wyjf.common.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/9/1.
 */
@Entity
public class Ticket {

    /**
     * tid : 1
     * did : 1
     * direction : 2
     * amount : 3
     * realAmount : 1
     * status : 0
     */
    @Id
    private long tid;
    private long uid;
    private String did;
    private int direction;
    private double amount;
    private double realAmount;
    private int status;

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(double realAmount) {
        this.realAmount = realAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
