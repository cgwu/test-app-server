package com.wyjf.common.domain;

import com.alibaba.fastjson.JSONObject;
import com.wyjf.common.util.CommonUtil;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
@Entity
@Table(name = "t_stock_data")
public class StockData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Double percent;
    private Double high;
    private Double price;
    private Double low;
    private Double updown;
    private Double open;
    private String type;
    private int status;
    private Double symbol;
    private Date updateTime;
    private String name;
    private String arrow;
    private Date timeTime;
    private Double yestclose;
    private Double turnover;


    public StockData(JSONObject json) throws Exception {
        code = json.getString("code");
        percent = json.getDouble("percent");
        high = json.getDouble("high");
        price = json.getDouble("price");
        low = json.getDouble("low");
        updown = json.getDouble("updown");
        open = json.getDouble("open");
        type = json.getString("type");
        status = json.getIntValue("status");
        symbol = json.getDouble("symbol");
        updateTime = CommonUtil.stringToDateTime(json.getString("update"), "yyyy/MM/dd HH:mm:ss");
        name = json.getString("name");
        arrow = json.getString("arrow");
        timeTime = CommonUtil.stringToDateTime(json.getString("time"), "yyyy/MM/dd HH:mm:ss");
        yestclose = json.getDouble("yestclose");
        turnover = json.getDouble("turnover");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getUpdown() {
        return updown;
    }

    public void setUpdown(Double updown) {
        this.updown = updown;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getSymbol() {
        return symbol;
    }

    public void setSymbol(Double symbol) {
        this.symbol = symbol;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArrow() {
        return arrow;
    }

    public void setArrow(String arrow) {
        this.arrow = arrow;
    }

    public Date getTimeTime() {
        return timeTime;
    }

    public void setTimeTime(Date timeTime) {
        this.timeTime = timeTime;
    }

    public Double getYestclose() {
        return yestclose;
    }

    public void setYestclose(Double yestclose) {
        this.yestclose = yestclose;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }
}
