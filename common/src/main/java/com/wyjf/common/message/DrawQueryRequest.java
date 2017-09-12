package com.wyjf.common.message;

/**
 * Created by Administrator on 2017/9/12.
 */
public class DrawQueryRequest extends AjaxPageRequest {
    public DrawQueryRequest(){}

    private String drawDay;

    public String getDrawDay() {
        return drawDay;
    }

    public void setDrawDay(String drawDay) {
        this.drawDay = drawDay;
    }
}
