package com.wyjf.common.message;

/**
 * Created by Administrator on 2017/9/11.
 */
public class AjaxPageRequest {
    int start;
    int length;
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
