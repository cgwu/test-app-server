package com.wyjf.common.message;

import com.wyjf.common.domain.Draw;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/15.
 */
public class DrawEx extends Draw {
    public DrawEx(){}

    public DrawEx(Long countdown) {
        this.countdown = countdown;
    }

    public DrawEx(Draw d, Long countdown) {
        this.setDid(d.getDid());
        this.setDrawDay(d.getDrawDay());
        this.setDrawSeq(d.getDrawSeq());
        this.setStartDate(d.getStartDate());
        this.setEndDate(d.getEndDate());
        this.setAmountUp(d.getAmountUp());
        this.setAmountDown(d.getAmountDown());
        this.setStatus(d.getStatus());
        this.countdown = countdown;
    }

    /**
     * 倒计时(毫秒ms)
     */
    public Long countdown;

    public Long getCountdown() {
        return countdown;
    }

    public void setCountdown(Long countdown) {
        this.countdown = countdown;
    }
}
