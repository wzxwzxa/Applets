package com.lz.applet.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lz.applet.entity.Article;
import com.lz.applet.entity.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定义数据返回结构
 *
 * @author dell
 * @date 2020-05-28 11:44
 */
public class ResultUtil {
    /**
     * 定义返回的状态 200/500
     */
    @JsonIgnore
    private Integer status;

    /**
     * 响应的消息
     */
    @JsonIgnore
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    public ResultUtil(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * @return
     */
    public static Map result(List<Article> articleList, int count) {
        HashMap<Object, Object> map = new HashMap<>(16);
        map.put("data", articleList);
        map.put("count", count);
        return map;
    }

    public static Map resultComment(List<Comment> commentList, int count) {
        HashMap<Object, Object> map = new HashMap<>(16);
        map.put("data", commentList);
        map.put("count", count);
        return map;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
