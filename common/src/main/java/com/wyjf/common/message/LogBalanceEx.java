package com.wyjf.common.message;

import com.wyjf.common.domain.BankCard;
import com.wyjf.common.domain.LogBalance;
import com.wyjf.common.util.CommonUtil;

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

    public LogBalanceEx(long lid, long uid, double amount, int type, Integer tag, Long refId, LocalDateTime logTime, String cardNumber, String bank) {
        super(lid, uid, amount, type, tag, refId, logTime);
        this.cardNumber = CommonUtil.bankCardReplaceWithStar(cardNumber);
        this.bank = bank;
    }
    private LocalDate drawDay;
    private String cardNumber;
    private String bank;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

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
