package com.wyjf.common.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/21.
 */
@Entity
public class DrawResult {
    /**
     * 盘口ID
     */
    @Id
    public Long did;
    /**
     * 开始指数
     */
    public double startPrice;
    /**
     * 结束指数
     */
    public double endPrice;
    /**
     * 1涨,0跌
     */
    public int result;
    /**
     * 平台收益(百分比)
     */
    public double platformPercent;
    /**
     * 平台收益金额
     */
    public double platformAmount;
    /**
     * 用户剩余奖池(中奖方总额 - 平台收益金额)
     */
    public double prizeAmount;
    /**
     * 结账时间
     */
    public LocalDateTime processTime;


    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public double getPlatformPercent() {
        return platformPercent;
    }

    public void setPlatformPercent(double platformPercent) {
        this.platformPercent = platformPercent;
    }

    public double getPlatformAmount() {
        return platformAmount;
    }

    public void setPlatformAmount(double platformAmount) {
        this.platformAmount = platformAmount;
    }

    public double getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(double prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public LocalDateTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }
}
