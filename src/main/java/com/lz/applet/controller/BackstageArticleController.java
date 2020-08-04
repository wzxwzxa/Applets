package com.lz.applet.controller;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lz.applet.entity.Article;
import com.lz.applet.service.ArticleService;
import com.lz.applet.util.ArticleContentUtils;
import com.lz.applet.util.FastDfsUtils;
import com.lz.applet.util.ProcessRequestUtils;
import com.lz.applet.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 文章管理后后台管理，与前台做区分
 *
 * @author dell
 * @date 2020-06-01 22:04
 */
@Controller
@Slf4j
@CrossOrigin
public class BackstageArticleController {

    @Value("${fast.path}")
    private String fastPath;

    @Value("${phone.width}")
    private String phoneWidth;

    @Value("${head.portrait}")
    private String headPortrait;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private ArticleService articleService;

    private static int TEMP = 4001;
    /**
     * 返回所有的文章信息
     * @return
     */
    @RequestMapping("backstageGetArticleList")
    public String backstageGetArticleList(HttpServletRequest request,int columnId){

        try {
            //查询所有的文章信息
            List<Article> articleList = articleService.backstageSelectAll(columnId);
            List<Article> articleArrayList = ProcessRequestUtils.processImg(articleList);
            request.setAttribute("articleList",articleArrayList);
            return "article";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台返回所有的文章信息失败"+e.getMessage());
            return "error";
        }
    }

    /**
     * 这个是添加文章信息
     * @param file
     * @param article
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "backstageAddToArticle",method = RequestMethod.POST)
    public String addToArticle(MultipartFile[] file, Article article) throws Exception {
        try {
//            System.out.println(article.getRecommend());

            if (article.getRecommend()==TEMP){
                article.setColumnId(TEMP);
            }
            //文件上传返回文件的路径信息。
            HashMap<String,String> hashMap = FastDfsUtils.upload(file, fastFileStorageClient,fastPath);
            //修改主页内容信息，修改为html格式的内容
            Article resultArticle = ArticleContentUtils.articleContentUtil(file, article, phoneWidth, hashMap);
            //放入管理员用户头像
            //resultArticle.setHeadPortrait(headPortrait);
            try {
                //保存到数据库
                int i = articleService.addToArticle(resultArticle);
                if (i!=0){
                    return "forward:/backstageGetArticleList";
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("后台文章信息添加数据库失败"+e.getMessage());
            }
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台添加文章信息失败",e.getMessage());
            return "error";
        }
    }

    /**
     * 根据传入的ID进行文章信息的删除
     * @param articleId
     * @return
     */
    @RequestMapping("backstageDeleteArticle")
    public String backstageDeleteArticle(int articleId){
        try {
            //删除文章章信息
            int i = articleService.deleteById(articleId);
            System.out.println("返回值"+i);
            if (i>0){
                return "forward:/backstageGetArticleList?columnId=4002";
            }else {
                log.error("删除文章信息失败");
                return "error";
            }
        } catch (Exception e) {
            log.error("删除文章信息失败"+e.getMessage());
            return "error";
        }
    }

    /**
     *  获取视频文件信息
     * */
    @RequestMapping("backstageGetVideoList")
    public String backstageGetVideoList(HttpServletRequest request,int columnId){
        try {
            //查询所有的视频类信息
            List<Article> videos = articleService.backstageSelectAll(columnId);
            request.setAttribute("videos",videos);
            return "video";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取视频文件列表信息失败"+e.getMessage());
            return "error";
        }
    }

    /**
     * 根据视频类栏目ID查询所有的视频信息列表
     * @param columnId 栏目ID
     * @param pageNum 从那条数据开始查询
     * @param pageSize 每页展示多少条数据
     * @return
     */
    @RequestMapping("backstageGetVideoListPaging")
    @ResponseBody
    public ResultUtil backstageGetVideoListPaging(int columnId,int pageNum,int pageSize){
        try {
            //查询所有视频信息
            List<Article> articleList = articleService.backstageGetVideoListPaging(columnId,pageNum,pageSize);
            //查询视频信息的总数
            int count = articleService.getArticleCount(columnId);
            //将获取到的数据和总数放回
            return new ResultUtil(200,"请求成功",ResultUtil.result(articleList,count));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台获取视频列表信息失败"+e.getMessage());
            return new ResultUtil(500,"请求失败");
        }
    }

    /**
     * 添加视频信息
     * @param article 用户填写的视频内容
     * @return
     */
    @RequestMapping(value = "backstageAddVideo",method = RequestMethod.POST)
    public String backstageAddVideo(MultipartFile[] file,Article article){
        try {

            //上传视频封面图片
            HashMap<String, String> upload = FastDfsUtils.upload(file, fastFileStorageClient, fastPath);
            //遍历
            for (MultipartFile multipartFile : file) {
                //根据文件名称作为key获取到文件的路径
                String s = upload.get(multipartFile.getOriginalFilename());
                //文件路径存入对象中
                article.setImgs(s);
            }

            if (article.getRecommend()==TEMP){
                article.setColumnId(TEMP);
            }
            int i = articleService.addToArticle(article);
            System.out.println(i);
            return "forward:/backstageGetVideoList";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台添加视频信息失败"+e.getMessage());
            return "error";
        }
    }

    /**
     * 删除视频信息
     * @param videoId
     * @return
     */
    @RequestMapping("backstageDeleteVideoById")
    public String deleteVideoById(int videoId){
        try {

            int result = articleService.deleteVideoById(videoId);
            if (result>0){
                return "forward:/backstageGetVideoList?columnId=4003";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台删除视频信息错误"+e.getMessage());
        }
        log.error("后台删除视频信息失败");
        return "error";
    }

//    /**
//     * 返回文章的总条数
//     * @return
//     * @param columnId 栏目对应的文章类型ID
//     */
//    @RequestMapping("backstageArticleCount")
//    @ResponseBody
//    public ResultUtil backstageArticleCount(int columnId){
//        try {
//            int articleCount = articleService.getArticleCount(columnId);
//            return new ResultUtil(200,"请求成功",articleCount);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("管理系统查询文章总条数失败"+e.getMessage());
//            return new ResultUtil(500,"请求失败");
//        }
//    }

    /**
     * 后台系统查询文章信息的分页
     * @param columnId 文章栏目类别的id
     * @param pageNum  分页从那页开始选
     * @param pageSize 每页展示多少条数据
     * @return
     */
    @RequestMapping("backstageArticlePaging")
    @ResponseBody
    public ResultUtil backstageArticlePaging(int columnId,int pageNum,int pageSize){
        try {
            HashMap<Object, Object> map = new HashMap<>(16);
            //分页查询所有的文章信息
            List<Article> articleArrayList = articleService.backstageArticlePaging(columnId, pageNum, pageSize);
            //修改文章的图片改为一张
            List<Article> articleList = ProcessRequestUtils.processImg(articleArrayList);
            //查询文章的总数
            int count = articleService.getArticleCount(columnId);
            map.put("data",articleList);
            map.put("count",count);
            return new ResultUtil(200,"请求成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台获取文章类信息失败"+e.getMessage());
            return new ResultUtil(500,"请求失败");
        }
    }
}
