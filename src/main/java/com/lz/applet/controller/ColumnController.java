package com.lz.applet.controller;

import com.lz.applet.entity.Column;
import com.lz.applet.service.ColumnService;
import com.lz.applet.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 栏目列表导航栏
 *
 * @author dell
 * @date 2020-05-28 10:40
 */
@Controller
@CrossOrigin
public class ColumnController {

    @Autowired
    private ColumnService columnService;


    /**
     * 查询所有的栏目列表信息
     */
    @RequestMapping(value = "getColumnList", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getColumnList() {
        try {
            List<Column> columns = columnService.selectAll();
            return new ResultUtil(200, "请求成功", columns);
        } catch (Exception e) {
            return new ResultUtil(500, "请求失败");
        }
    }
}
