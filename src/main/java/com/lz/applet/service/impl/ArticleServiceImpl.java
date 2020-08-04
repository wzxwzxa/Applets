package com.lz.applet.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lz.applet.entity.Article;
import com.lz.applet.mapper.ArticleMapper;
import com.lz.applet.service.ArticleService;
import com.lz.applet.util.FastDfsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author dell
 * @date 2020-05-28 13:59
 */
@Service
public class ArticleServiceImpl implements ArticleService {


    @Value("${fast.path}")
    private String fastPath;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 根据栏目id查询文章信息
     * @param columnId 传入的目录ID
     * @return
     */
    @Override
    public List<Article> selectByColumnId(int columnId,int pageNum,int pageSize) {
       PageHelper.startPage(pageNum,pageSize);
       return articleMapper.selectByColumnId(columnId);

    }

    /**
     * 后台查询所有的文章信息
     * @return
     */
    @Override
    public List<Article> backstageSelectAll(int columnId) {
        //PageHelper.startPage(pageNum,pageSize);
        //文章的id是4002
        List<Article> articleList = articleMapper.backstageSelectAll(columnId);
        return articleList;

    }

    /**
     * @param resultArticle 文章对象
     * @param
     * @return
     */
    @Override
    public int addToArticle(Article resultArticle) {
        //设置日期格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatTime = format.format(new Date());
        resultArticle.setCreationTime(formatTime);
        int insert = articleMapper.insert(resultArticle);
        return insert;
    }

    /**
     * 根据ID删除文章信息
     * @param articleId 传入的ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(int articleId) {
        //首先根据id查询出所有的信息，获取到fstDfs的文件路径
        Article article = articleMapper.selectByPrimaryKey(articleId);
        String articleImg = article.getImgs();
        //传入参数进行fast文件删除
        boolean isOk = FastDfsUtils.deleteFile(articleImg, fastPath,fastFileStorageClient);
        //判断是否成功，如果成功删除fastDfs的文件就进行后续的数据删除
        if (isOk){
            try {
                //根据id删除
                articleMapper.deleteByPrimaryKey(articleId);
                return article.getId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 删除视频信息
     * @param videoId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteVideoById(int videoId) {
        //删除视频信息需要先删除存在的视频封面图片
        //根据视频id查询到图片路径进行删除
        Article article = articleMapper.selectByPrimaryKey(videoId);
        //获取到图片路径
        String img = article.getImgs();
        boolean isOk = true;
        if (img!=null && img!=""){
             isOk = FastDfsUtils.deleteFastFile(img, fastFileStorageClient);
        }
        if (isOk){
            int i = articleMapper.deleteByPrimaryKey(videoId);
            return i;
        }
        return 0;
    }

    /**
     * 查询文章信息
     * @param articleId 文章ID
     * @return
     */
    @Override
    public Article selectArticleById(int articleId) {
        //返回查询的数据
        return articleMapper.selectByPrimaryKey(articleId);
    }

    /**
     * 分页查询所有的推荐信息
     * @param columnId 栏目ID
     * @param pageNum 从num页开始
     * @param pageSize 每页展示size条
     * @return
     */
    @Override
    public List<Article> selectByRecommend(int columnId,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return articleMapper.selectByRecommend(columnId);
    }

    /**
     * 获取文章的总数量，用于分页
     * @return
     */
    @Override
    public int getArticleCount(int columnId) {
        return articleMapper.selectCount(columnId);
    }

    /**
     * 查询文章视频集合根据栏目ID进行查询
     * @param columnId 栏目Id
     * @param pageNum 从num页开始查询
     * @param pageSize 每页展示size条数据
     * @return
     */
    @Override
    public List<Article> selectArticleVideoListByColumnId(int columnId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return articleMapper.selectByColumnId(columnId);
    }

    /**
     * 查询所有的视频类型总数
     * @param columnId  视频类ID
     * @return
     */
    @Override
    public int selectArticleVideoCount(int columnId) {
        return articleMapper.selectCount(columnId);
    }

    /**
     * @param columnId 栏目中的文章类ID
     * @param pageNum  从那页开始展示数据
     * @param pageSize 每页展示数据的长度
     * @return
     */
    @Override
    public List<Article> backstageArticlePaging(int columnId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return articleMapper.backstageSelectAll(columnId);
    }

    @Override
    public List<Article> backstageGetVideoListPaging(int columnId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.backstageSelectAll(columnId);
        return articleList;
    }
}
