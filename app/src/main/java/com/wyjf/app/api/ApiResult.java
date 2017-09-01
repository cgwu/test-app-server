package com.wyjf.app.api;

/**
 * Created by dannis on 2017/8/22.
 */
public class ApiResult {
    /**
     * 0代表成功；其它代表失败。
     */
    private int code;

    private String msg;

    private Object val;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", val=" + val +
                '}';
    }
}
