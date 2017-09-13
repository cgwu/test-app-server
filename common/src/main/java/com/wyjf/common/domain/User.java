package com.wyjf.common.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String token;
    private LocalDateTime tokenTime;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(LocalDateTime tokenTime) {
        this.tokenTime = tokenTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", balance=" + balance +
                ", gender='" + gender + '\'' +
                ", passwordLogin='" + passwordLogin + '\'' +
                ", passwordTrade='" + passwordTrade + '\'' +
                '}';
    }
}
