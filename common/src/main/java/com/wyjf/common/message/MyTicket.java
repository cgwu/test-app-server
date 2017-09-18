package com.wyjf.common.message;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/18.
 * 用于查询我的票信息
 */
public class MyTicket {
    private String sid;
    private int direction;
    private double amount;
    private String time;

    public MyTicket(String sid, int direction, double amount, String time) {
        this.sid = sid;
        this.direction = direction;
        this.amount = amount;
        this.time = time;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
