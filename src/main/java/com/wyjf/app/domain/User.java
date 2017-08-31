package com.wyjf.app.domain;

import javax.persistence.*;

/**
 * Created by dannis on 2017/8/22.
 * 用户表
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long uid;
    private String phone;
    private String nickname;
    private double balance;
    private String gender;
    private String passwordLogin;
    private String passwordTrade;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    public String getPasswordTrade() {
        return passwordTrade;
    }

    public void setPasswordTrade(String passwordTrade) {
        this.passwordTrade = passwordTrade;
    }
}
