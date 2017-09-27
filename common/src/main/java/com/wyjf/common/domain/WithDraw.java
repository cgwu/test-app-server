package com.wyjf.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/21 0021.
 */
@Entity
@Table(name = "withdraw")
public class WithDraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private Long bcid;
    private Double money;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handingTime;
    private Integer status;
    private String remark;

    public WithDraw() {
    }
    public WithDraw(WithDraw withDraw) {
        this.id = withDraw.getId();
        this.uid = withDraw.getUid();
        this.bcid = withDraw.getBcid();
        this.money = withDraw.getMoney();
        this.createTime = withDraw.getCreateTime();
        this.handingTime = withDraw.getHandingTime();
        this.status = withDraw.getStatus();
        this.remark = withDraw.getRemark();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getBcid() {
        return bcid;
    }

    public void setBcid(Long bcid) {
        this.bcid = bcid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getHandingTime() {
        return handingTime;
    }

    public void setHandingTime(LocalDateTime handingTime) {
        this.handingTime = handingTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
