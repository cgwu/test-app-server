package com.wyjf.common.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/9/1.
 */
@Entity
public class Draw {
    /**
     * did : 201709011
     * startDate : 2017-09-01 16:45:51
     * endDate : 2017-09-01 16:45:53
     * amountUp : 0
     * amountDown : 0
     * status : 0
     */
    @Id
    private String did;
    private Timestamp startDate;
    private Timestamp endDate;
    private int amountUp;
    private int amountDown;
    private int status;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getAmountUp() {
        return amountUp;
    }

    public void setAmountUp(int amountUp) {
        this.amountUp = amountUp;
    }

    public int getAmountDown() {
        return amountDown;
    }

    public void setAmountDown(int amountDown) {
        this.amountDown = amountDown;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
