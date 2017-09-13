package com.wyjf.common.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/9/1.
 */
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    protected Long uid;

    @Lob
    @Basic(fetch = FetchType.LAZY)
//    @Column(length=500000)
    private byte[] headThumb;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public byte[] getHeadThumb() {
        return headThumb;
    }

    public void setHeadThumb(byte[] headThumb) {
        this.headThumb = headThumb;
    }
}
