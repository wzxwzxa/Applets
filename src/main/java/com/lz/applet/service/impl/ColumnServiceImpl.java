package com.lz.applet.service.impl;

import com.lz.applet.entity.Column;
import com.lz.applet.mapper.ColumnMapper;
import com.lz.applet.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dell
 * @date 2020-05-28 11:07
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;

    /**
     * 查询所有的栏目
     * @return
     */
    @Override
    public List<Column> selectAll() {
        //查询所有的栏目信息
       return columnMapper.selectAll();

    }
}
