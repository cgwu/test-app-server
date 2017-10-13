package com.wyjf.common.domain;

import com.wyjf.common.message.BasicTicket;
import com.wyjf.common.message.MyTicket;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 票
 * Created by Administrator on 2017/9/1.
 */

//@SqlResultSetMappings({
//        @SqlResultSetMapping(
//                name = "queryTicketByDrawMap",
//                entities = {
////                        @EntityResult
////                                (
////                                        entityClass = BasicTicket.class,
////                                        fields =
////                                                {
////                                                        @FieldResult(name = "phone", column = "phone"),
////                                                        @FieldResult(name = "loc", column = "loc"),
////                                                        @FieldResult(name = "direction", column = "direction"),
////                                                        @FieldResult(name = "amount", column = "amount"),
////                                                        @FieldResult(name = "time", column = "time")
////                                                }
////                                )
//                },
//                columns = {
//                        @ColumnResult(name = "phone"),
//                        @ColumnResult(name = "loc"),
//                        @ColumnResult(name = "direction"),
//                        @ColumnResult(name = "amount"),
//                        @ColumnResult(name = "time")
//                }
//        )
//})

//@NamedStoredProcedureQuery(name = "Ticket.buy", procedureName = "sp_buy_ticket", parameters = {
//        @StoredProcedureParameter(mode = ParameterMode.IN, name = "a", type = Integer.class),
//        @StoredProcedureParameter(mode = ParameterMode.IN, name = "b", type = Integer.class),
//        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "ret", type = Integer.class) })

@NamedNativeQueries({
        @NamedNativeQuery(name = "queryTicketByDraw", resultSetMapping = "queryTicketByDrawMap",
                query = "select concat(left(u.phone,3), '****', right(u.phone,4)) phone, \n" +
                        "(select concat(province,' ',city) as loc from phone_addr where phone_prefix= left(u.phone, 7) limit 1) loc,\n" +
                        "t.direction, t.amount, DATE_FORMAT(t.buy_time,'%m-%d %T' ) as time from ticket t left join user u on t.uid = u.uid\n" +
                        "where did = ?\n" +
                        "order by tid desc\n" +
                        "limit ?, ?;"
        ),

        @NamedNativeQuery(name = "queryMine", resultSetMapping = "queryMineMap",
                query = "select tid, sid, direction,amount, DATE_FORMAT(buy_time,'%m-%d %T' ) as time from ticket where did = ? and uid = ? order by tid desc;"

        ),


})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "queryTicketByDrawMap",
                classes = {
                        @ConstructorResult(
                                targetClass = BasicTicket.class,
                                columns = {
                                        @ColumnResult(name = "phone"),
                                        @ColumnResult(name = "loc"),
                                        @ColumnResult(name = "direction", type = Integer.class),
                                        @ColumnResult(name = "amount", type = Double.class),
                                        @ColumnResult(name = "time")
                                }
                        )
                }),

        @SqlResultSetMapping(
                name = "queryMineMap",
                classes = {
                        @ConstructorResult(
                                targetClass = MyTicket.class,
                                columns = {
                                        @ColumnResult(name = "tid", type = Long.class),
                                        @ColumnResult(name = "sid"),
                                        @ColumnResult(name = "direction", type = Integer.class),
                                        @ColumnResult(name = "amount", type = Double.class),
                                        @ColumnResult(name = "time")
                                }
                        )
                }),

})

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tid;
    private String sid;
    private long uid;
    private long did;
    private int direction;
    private double amount;
    private double realAmount;
    private double fee;
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
     *
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
