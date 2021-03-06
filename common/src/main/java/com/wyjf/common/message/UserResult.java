package com.wyjf.common.message;

import com.wyjf.common.domain.User;
import com.wyjf.common.domain.UserInfo;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/12 0012.
 */
public class UserResult extends User {
    private String headurl;
    private Integer isSetTrade;
    private Long cardId;
    private String bank;
    private String cardNumber;

    public UserResult(User u, UserInfo userInfo) {
        this.setUid(u.getUid());
        this.setPhone(u.getPhone());
        this.setNickname(u.getNickname());
        this.setGender(u.getGender());
        this.setToken(u.getToken());
        this.setBalance(u.getBalance());
        this.setIsSetTrade(u.getPasswordTrade() != null ? 1 : 0);
        this.setTokenTime(u.getTokenTime());
        if (u != null && userInfo != null) {
            this.headurl = "/api/user/head/" + u.getUid() + "/" + new Date().getTime() + "/head.png";
        } else {
            this.headurl = "";
        }
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public Integer getIsSetTrade() {
        return isSetTrade;
    }

    public void setIsSetTrade(Integer isSetTrade) {
        this.isSetTrade = isSetTrade;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
