package com.lz.applet.controller;

import com.lz.applet.entity.Article;
import com.lz.applet.service.ArticleService;
import com.lz.applet.util.ProcessRequestUtils;
import com.lz.applet.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序推荐展示
 *
 * @author dell
 * @date 2020-06-05 23:34
 */
@Controller
@CrossOrigin
public class RecommendController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询所有的推荐信息
     *
     * @param columnId 栏目ID
     * @param pageNum  从pageNum页开始
     * @param pageSize 每页展示size条数据
     * @return
     */
    @RequestMapping(value = "getRecommendList", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil selectRecommend(int columnId, int pageNum, int pageSize) {
        try {
            List<Article> articleList = articleService.selectByRecommend(columnId, pageNum, pageSize);
            //这里返回的是文章和视频一起的信息需要进行处理img
            List<Article> result = ProcessRequestUtils.processImg(articleList);
            int count = articleService.selectArticleVideoCount(columnId);
            return new ResultUtil(200, "请求成功", ResultUtil.result(result, count));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(500, "请求失败", e.getMessage());
        }
    }

    /**
     * 查询所有的推荐文章/视频的总数
     *
     * @return
     */
    @RequestMapping(value = "getRecommendCount", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getRecommendCount(int columnId) {
        try {
            int result = articleService.selectArticleVideoCount(columnId);
            return new ResultUtil(200, "请求成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(500, "请求失败");
        }
    }

    /**
     * 推荐文章/视频详情
     *
     * @param articleOrVideoId 文章或视频的ID
     * @return
     */
    @RequestMapping(value = "getRecommend", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getRecommend(
            @RequestParam(value = "articleOrVideoId", required = false) int articleOrVideoId) {
        try {
            Article article = articleService.selectArticleById(articleOrVideoId);
            return new ResultUtil(200, "请求成功", article);
        } catch (Exception e) {
            return new ResultUtil(500, "请求失败", e.getMessage());
        }
    }

}
