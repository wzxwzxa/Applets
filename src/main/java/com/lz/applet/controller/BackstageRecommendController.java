package com.lz.applet.controller;

import com.lz.applet.entity.Article;
import com.lz.applet.service.RecommendService;
import com.lz.applet.util.ProcessRequestUtils;
import com.lz.applet.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 推荐类后台管理
 *
 * @author dell
 * @date 2020-06-08 15:14
 */
@Controller
@Slf4j
public class BackstageRecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
     * 查询所有的推荐类信息
     * @param columnId
     * @param request
     * @return
     */
    @RequestMapping("getRecommendList")
    public String getRecommendList(int columnId, HttpServletRequest request){
        try {
            List<Article> recommendList = recommendService.getRecommendList(columnId);
            request.setAttribute("recommendList",recommendList);
            return "recommend";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台获取推荐类文章失败"+e.getMessage());
            return "error";
        }
    }

    /**
     * 查询推荐类的文章和视频信息
     * @param columnId 推荐类id
     * @param pageNum  从那页开始进行查询
     * @param pageSize  一页展示的数据条数
     * @return
     */
    @RequestMapping("getRecommendListPaging")
    @ResponseBody
    public ResultUtil getRecommendListPaging(int columnId,int pageNum,int pageSize){
        try {
            //查询所有的文章信息分页
            List<Article> articleList = recommendService.getRecommendListPaging(columnId,pageNum,pageSize);
            //对显示图片部分切分只显示一张图片
            List<Article> articlesList = ProcessRequestUtils.processImg(articleList);
            int count = recommendService.selectCount(columnId);
            HashMap<Object, Object> map = new HashMap<>(16);
            map.put("data",articlesList);
            map.put("count",count);
            return new ResultUtil(200,"请求成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文章/视频推荐类分页查询失败"+e.getMessage());
            return new ResultUtil(500,"请求失败");
        }
    }

    /**
     * 删除文章根据ID
     * deleteRecommend?recommendId="+recommendId
     */
    @RequestMapping("deleteRecommend")
    public String deleteRecommend(int recommendId){
        try {
            int result = recommendService.deleteRecommendById(recommendId);
            if (result>0){
                return "forward:/getRecommendList?columnId=4001";
            }else {
                log.error("后台推荐类删除文章失败");
                return "error";
            }
        } catch (Exception e) {
            log.error("后台推荐类删除文章失败"+e.getMessage());
            return "error";
        }
    }
}
