package com.wyjf.common.domain;

import com.wyjf.common.constant.WithDrawStatus;
import com.wyjf.common.util.CommonUtil;

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
    private LocalDateTime createTime;
    private LocalDateTime handingTime;
    private Integer status;
    private String remark;

    public WithDraw() {
        this.status = WithDrawStatus.CHECKING;
        this.createTime = CommonUtil.getTokenDateTime(0);
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
