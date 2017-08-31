package com.wyjf.app.api;

/**
 * Created by Administrator on 2017/8/22.
 */
public abstract class ApiFactory {
    public static ApiResult createResult(int code, String msg, Object val) {
        ApiResult result = new ApiResult();
        result.setCode(0);
        result.setMsg(msg);
        result.setVal(val);
        return result;
    }

    public static ApiResult createResult(Object val) {
        return createResult(0, "", val);
    }
}
