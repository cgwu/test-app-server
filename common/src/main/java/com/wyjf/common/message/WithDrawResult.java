package com.wyjf.common.message;

import com.wyjf.common.domain.BankCard;
import com.wyjf.common.domain.User;
import com.wyjf.common.domain.WithDraw;

import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
public class WithDrawResult extends WithDraw {
    private static final DateTimeFormatter FMT_SNO = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    private User user;
    private BankCard bankCard;

    public WithDrawResult() {
    }

    public WithDrawResult(User u, BankCard bankCard) {
        this.user = u;
        this.bankCard = bankCard;
    }

    public WithDrawResult(WithDraw withDraw, BankCard bankCard) {
        super(withDraw);
        this.bankCard = bankCard;
        if (withDraw.getHandingTime() == null) {
            this.setHandingTime(this.getCreateTime().plusDays(1));
        }
    }

    public String getSNO() {
        return FMT_SNO.format(this.getCreateTime()) + this.getId().toString();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }


}
