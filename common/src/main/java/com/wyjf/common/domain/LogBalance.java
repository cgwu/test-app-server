package com.wyjf.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyjf.common.message.LogBalanceEx;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedNativeQueries({
        @NamedNativeQuery(name = "queryLogBalanceEx", resultSetMapping = "queryLogBalanceExMap",
                query = "select b.lid , b.uid, b.amount, -t.fee as fee, b.`type`, b.tag, b.ref_id as refId, b.log_time as logTime, d.draw_day as drawDay " +
                        "from log_balance b left join ticket t on b.ref_id = t.tid left join draw d on t.did = d.did\n" +
                        "where b.uid = ? and b.type= ? " +
                        "order by lid desc limit ?, ?"
        ),
        @NamedNativeQuery(name = "queryLogBalanceBc", resultSetMapping = "queryLogBalanceBcMap",
                query = "SELECT " +
                        "lb.lid, lb.uid, lb.amount, lb.`type`, lb.tag, lb.ref_id AS refId, lb.log_time AS logTime, bc.card_number AS cardNumber, bc.bank AS bank, w.status " +
                        "FROM " +
                        "log_balance lb " +
                        "LEFT JOIN withdraw w ON w.id = lb.ref_id " +
                        "LEFT JOIN bank_card bc ON bc.id = w.bcid " +
                        "WHERE lb.uid = ? AND lb.type = ? " +
                        "order by lid desc limit ?, ?"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "queryLogBalanceExMap",
                classes = {
                        @ConstructorResult(
                                targetClass = LogBalanceEx.class,
                                columns = {
                                        @ColumnResult(name = "lid", type = Long.class),
                                        @ColumnResult(name = "uid", type = Long.class),
                                        @ColumnResult(name = "amount", type = Double.class),
                                        @ColumnResult(name = "fee", type = Double.class),
                                        @ColumnResult(name = "type", type = Integer.class),
                                        @ColumnResult(name = "tag", type = Integer.class),
                                        @ColumnResult(name = "refId", type = Long.class),
                                        @ColumnResult(name = "logTime", type = LocalDateTime.class),
                                        @ColumnResult(name = "drawDay", type = LocalDate.class),
                                }
                        )
                }),
        @SqlResultSetMapping(
                name = "queryLogBalanceBcMap",
                classes = {
                        @ConstructorResult(
                                targetClass = LogBalanceEx.class,
                                columns = {
                                        @ColumnResult(name = "lid", type = Long.class),
                                        @ColumnResult(name = "uid", type = Long.class),
                                        @ColumnResult(name = "amount", type = Double.class),
                                        @ColumnResult(name = "type", type = Integer.class),
                                        @ColumnResult(name = "tag", type = Integer.class),
                                        @ColumnResult(name = "refId", type = Long.class),
                                        @ColumnResult(name = "logTime", type = LocalDateTime.class),
                                        @ColumnResult(name = "cardNumber", type = String.class),
                                        @ColumnResult(name = "bank", type = String.class),
                                        @ColumnResult(name = "status", type = Integer.class),
                                }
                        )
                })
})


@Entity
public class LogBalance {

    public LogBalance() {
    }

    public LogBalance(long lid, long uid, double amount, int type, Integer tag, Long refId, LocalDateTime logTime) {
        this.lid = lid;
        this.uid = uid;
        this.amount = amount;
        this.type = type;
        this.tag = tag;
        this.refId = refId;
        this.logTime = logTime;
    }

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
     * 0云投记录，1充值记录，2提款记录
     */
    private int type;

    /**
     * 子类型标识(1-5下注盘口类型, 6返现, 7中奖)
     */
    private Integer tag;

    /**
     * 引用记录ID
     */
    private Long refId;

    /**
     * 操作日志时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    @Override
    public String toString() {
        return "LogBalance{" +
                "lid=" + lid +
                ", uid=" + uid +
                ", amount=" + amount +
                ", type=" + type +
                ", tag=" + tag +
                ", refId=" + refId +
                ", logTime=" + logTime +
                '}';
    }
}
