package com.wyjf.common.message;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */
public class AjaxPageRequest {
    int start;
    int length;

    /**
     * 根据ajax表单自动转换为 spring-data: PageRequest
     * PageRequest preq = new PageRequest(req.getStart()/req.getLength(), req.getLength(), Sort.Direction.ASC, "did");
     * @param http
     * @return
     */
    public PageRequest getPage(HttpServletRequest http){
        int pageSize = length;
        if(pageSize == 0) pageSize = 1;

        String orderColIdx = http.getParameter("order[0][column]");  //order[0][column]:1
        String orderDir = http.getParameter("order[0][dir]");  //order[0][dir]:asc

        Sort.Direction dir = Sort.Direction.ASC;    //排序
        if("desc".equals(orderDir)) dir = Sort.Direction.DESC;

        String orderCol = http.getParameter(String.format("columns[%s][data]",orderColIdx)); //排序字段名 -> columns[1][data]:did

        PageRequest preq = new PageRequest(start/pageSize, pageSize, dir, orderCol);
        return preq;
    }

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
