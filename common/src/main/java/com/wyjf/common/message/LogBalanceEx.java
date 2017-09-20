package com.wyjf.common.message;

import com.wyjf.common.domain.LogBalance;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/20.
 */
public class LogBalanceEx extends LogBalance {
    public LogBalanceEx(long lid, long uid, double amount, int type, Integer tag, Long refId, LocalDateTime logTime, LocalDate drawDay) {
        super(lid, uid, amount, type, tag, refId, logTime);
        this.drawDay = drawDay;
    }

    private LocalDate drawDay;

    public LocalDate getDrawDay() {
        return drawDay;
    }

    public void setDrawDay(LocalDate drawDay) {
        this.drawDay = drawDay;
    }

    @Override
    public String toString() {
        return super.toString()+ " >> LogBalanceEx{" +
                "drawDay=" + drawDay +
                '}';
    }
}
