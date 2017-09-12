package com.wyjf.app.api;

/**
 * Created by Administrator on 2017/8/22.
 */
public abstract class ApiFactory {
    public static ApiResult createResult(int code, String msg, Object val) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setVal(val);
        return result;
    }

    public static ApiResult createResult(Object val) {
        return createResult(0, "", val);
    }

    public static ApiResult success(String msg) {
        return createResult(0, msg, null);
    }

    public static ApiResult fail(int code, String msg) {
        return createResult(code, msg, null);
    }

}
