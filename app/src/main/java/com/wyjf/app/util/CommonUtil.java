package com.wyjf.app.util;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
public class CommonUtil {
    /**
     * 获取6位数随机数
     * @return
     */
    public static String getVerifyCode(){
        Double code = (Math.random()*9+1)*100000;
        return new Integer(code.intValue()).toString();
    }
}
