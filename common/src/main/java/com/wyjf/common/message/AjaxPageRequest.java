package com.wyjf.common.message;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */
public class AjaxPageRequest {
    int start;
    int length;

//    List<Map<String,Object>> columns;
//    List<Map<String,Object>> order;

    /**
     * 前台请求序号
     */
    int draw;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    @Override
    public String toString() {
        return "AjaxPageRequest{" +
                "start=" + start +
                ", length=" + length +
                ", draw=" + draw +
                '}';
    }
}
