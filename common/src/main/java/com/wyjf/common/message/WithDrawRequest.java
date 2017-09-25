package com.wyjf.common.message;

/**
 * Created by Administrator on 2017/9/22 0022.
 */
public class WithDrawRequest extends AjaxPageRequest {
    private String userPhone;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
