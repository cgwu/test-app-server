package com.wyjf.common.domain;

import java.util.Date;
import javax.persistence.*;

/**
 * Created by zhuxulei on 2017/8/31 0031.
 */
@Entity
@Table(name = "log_verifycode")
public class LogVerifycode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String verifycode;
    private Date createTime;
    private int status;

    public LogVerifycode(){}
    public LogVerifycode(String phone, String verfycode) {
        this.setPhone(phone);
        this.setVerifycode(verfycode);
        this.setStatus(0);
        this.setCreateTime(new Date());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

}
