package com.wyjf.app.utils.weixinpay;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */
public class ResultObject {
    private String msg;
    private String resultCode;
    private List<String> data;


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getMsg() {
        return msg;
    }
}
