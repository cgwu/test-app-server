package com.wyjf.common.domain;

import com.wyjf.common.util.CommonUtil;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/9/20 0020.
 */
@Entity
@Table(name = "bank_card")
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String cardNumber;
    private String realName;
    private String openBank;
    private String bank;

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

    public String getCardNumber() {
        return CommonUtil.bankCardReplaceWithStar(cardNumber);
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = CommonUtil.bankCardReplaceWithStar(cardNumber);
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
