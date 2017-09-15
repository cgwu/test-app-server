package com.wyjf.common.domain;

import com.wyjf.common.util.CommonUtil;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/13 0013.
 */
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String orderNumber;
    private Integer orderStatus;
    private Long uid;
    private Double orderMoney;
    private Integer orderType;
    private LocalDateTime createTime;
    private LocalDateTime payTime;
    private String remark;

    public Order(Double money, Integer payType, User user, String remark){
        this.orderNumber = "";
        this.orderStatus = 0;
        this.uid = user.getUid();
        this.orderMoney = money;
        this.orderType = payType;
        this.createTime = CommonUtil.getTokenDateTime(0);
        this.remark = remark;
    }


    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
