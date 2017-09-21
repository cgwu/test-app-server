package com.wyjf.common.constant;

/**
 * Created by Administrator on 2017/9/21 0021.
 * 状态（0未审核，1审核成功，2审核失败）
 */
public interface WithDrawStatus {
    /**
     * 提现请求未审核
     */
    public static final int CHECKING = 0;

    /**
     * 提现请求审核成功
     */
    public static final int CHECKSUCCESS = 1;

    /**
     * 提现请求审核失败
     */
    public static final int CHECKFAIL = 2;
}
