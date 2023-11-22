package com.lz.applet.service;

import com.lz.applet.entity.Column;

import java.util.List;

/**
 * 标题栏接口
 *
 * @author dell
 * @date 2020-05-28 10:52
 */
public interface ColumnService {

    /**
     * 查询所有的栏目列表
     *
     * @return
     */
    List<Column> selectAll();
}
