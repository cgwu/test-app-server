package com.wyjf.common.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/1.
 */
@Entity
//@NamedStoredProcedureQuery(name = "Ticket.buy", procedureName = "sp_buy_ticket", parameters = {
//        @StoredProcedureParameter(mode = ParameterMode.IN, name = "a", type = Integer.class),
//        @StoredProcedureParameter(mode = ParameterMode.IN, name = "b", type = Integer.class),
//        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "ret", type = Integer.class) })
public class Ticket {
    @Id
    private long tid;
    private String sid;
    private long uid;
    private long did;
    private int direction;
    private double amount;
    private double realAmount;
    private LocalDateTime buyTime;
    private int status;

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    /**
     * 方向（涨1、跌0）
     * @return
     */
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

    public LocalDateTime getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {
        this.buyTime = buyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
