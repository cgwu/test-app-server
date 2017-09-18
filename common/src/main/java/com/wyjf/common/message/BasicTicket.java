package com.wyjf.common.message;

/**
 * Created by Administrator on 2017/9/18.
 * 用于查询盘口所有人的票信息列表
 */

public class BasicTicket {
    private String phone;
    private String loc;
    private int direction;
    private double amount;
    private String time;

    public BasicTicket(String phone, String loc, int direction, double amount, String time) {
        this.phone = phone;
        this.loc = loc;
        this.direction = direction;
        this.amount = amount;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
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

    @Override
    public String toString() {
        return "BasicTicket{" +
                "phone='" + phone + '\'' +
                ", loc='" + loc + '\'' +
                ", direction=" + direction +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                '}';
    }
}
