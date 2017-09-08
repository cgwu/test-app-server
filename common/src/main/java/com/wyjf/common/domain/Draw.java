package com.wyjf.common.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/1.
 */
@Entity
public class Draw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long did;
    private LocalDate drawDay;
    private int drawSeq;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int amountUp;
    private int amountDown;
    private int status;

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

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public LocalDate getDrawDay() {
        return drawDay;
    }

    public void setDrawDay(LocalDate drawDay) {
        this.drawDay = drawDay;
    }

    public int getDrawSeq() {
        return drawSeq;
    }

    public void setDrawSeq(int drawSeq) {
        this.drawSeq = drawSeq;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Draw{" +
                "did=" + did +
                ", drawDay=" + drawDay +
                ", drawSeq=" + drawSeq +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amountUp=" + amountUp +
                ", amountDown=" + amountDown +
                ", status=" + status +
                '}';
    }
}
