package com.wyjf.common.message;

/**
 * Created by Administrator on 2017/9/12.
 */
public class SysParamQueryRequest extends AjaxPageRequest {

    public SysParamQueryRequest() {
    }

    private String dataKey;
    private String comment;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
