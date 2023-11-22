package com.lz.applet.controller;

import com.lz.applet.entity.Article;
import com.lz.applet.service.ArticleService;
import com.lz.applet.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 视频控制器
 *
 * @author dell
 * @date 2020-06-06 22:10
 */
@Controller
@CrossOrigin
@Slf4j
public class ArticleVideoController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据栏目id获取视频列表
     *
     * @param columnId 栏目ID
     * @param pageNum  分页从Num条数据开始
     * @param pageSize 每页展示size条数据
     * @return
     */
    @RequestMapping(value = "getArticleVideoList", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getArticleVideoList(int columnId, int pageNum, int pageSize) {
        try {
            //查询文章视频集合根据栏目ID进行查询
            List<Article> articleList = articleService.selectArticleVideoListByColumnId(columnId, pageNum, pageSize);
            //查询到视频类型的文章的总数
            int count = articleService.selectArticleVideoCount(columnId);
            return new ResultUtil(200, "请求成功", ResultUtil.result(articleList, count));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("小程序请求视频列表失败" + e.getMessage());
            return new ResultUtil(500, "请求失败", e.getMessage());
        }
    }

    /**
     * 根据文章视频ID查询详细的文章信息
     *
     * @param articleVideoId
     * @return
     */
    @RequestMapping(value = "getArticleVideo", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getArticleVideo(int articleVideoId) {
        try {
            Article article = articleService.selectArticleById(articleVideoId);
            return new ResultUtil(200, "请求成功", article);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("小程序端访问视频详细信息失败", e.getMessage());
            return new ResultUtil(500, "请求失败");
        }
    }

}
