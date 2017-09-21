package com.wyjf.app.share;

import org.springframework.data.util.Pair;

/**
 * Created by Administrator on 2017/9/21.
 */
public interface SmsSender {
    public Pair<Integer, String> send(String phone, String msg);
}
