package com.lz.applet.controller;

import com.lz.applet.entity.Article;
import com.lz.applet.service.ArticleService;
import com.lz.applet.util.ProcessRequestUtils;
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
 * 小程序端
 * 查询文章（视频）信息
 *
 * @author dell
 * @date 2020-05-28 10:19
 */
@Controller
@CrossOrigin
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据栏目ID查询所有的栏目信息
     *
     * @param columnId
     * @return
     */
    @RequestMapping(value = "getArticleList", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getArticleList(int columnId, int pageNum, int pageSize) {
        try {
            //查询所有的文章类信息，分页查询
            List<Article> articles = articleService.selectByColumnId(columnId, pageNum, pageSize);
            //处理文章类返回的数据信息，图片处理为只返回一张
            List<Article> articleList = ProcessRequestUtils.processImg(articles);
            //分页的时候需要数据的总数信息
            int count = articleService.getArticleCount(columnId);
            return new ResultUtil(200, "请求成功", ResultUtil.result(articleList, count));
        } catch (Exception e) {
            log.error("小程序端访问文章列表信息失败" + e.getMessage());
            return new ResultUtil(500, "请求失败", e.getMessage());
        }
    }


    /**
     * 根据文章的id查询文章信息
     *
     * @param articleId 查询的文章ID
     */
    @RequestMapping(value = "getArticle", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getArticle(int articleId) {
        try {
            //查询对应id文章的详细信息
            Article article = articleService.selectArticleById(articleId);
            return new ResultUtil(200, "请求成功", article);
        } catch (Exception e) {
            e.printStackTrace();
            //android.util.Log.e(TAG, "getArticle: ", );.error("小程序端访问详细文章信息失败"+e.getMessage());
            log.error("小程序端访问详细文章信息失败" + e.getMessage());
            return new ResultUtil(500, "请求失败", e.getMessage());
        }
    }
}
