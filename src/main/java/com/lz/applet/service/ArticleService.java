package com.lz.applet.service;

import com.lz.applet.entity.Article;

import java.util.List;

/**
 * 文章操作接口
 *
 * @author dell
 * @date 2020-05-28 13:58
 */
public interface ArticleService {

    /**
     * 根据目录id查询文章
     *
     * @param columnId 传入的目录ID
     * @param pageNum  第多少页
     * @param pageSize 每页展示的条数
     * @return
     */
    List<Article> selectByColumnId(int columnId, int pageNum, int pageSize);

    /**
     * 查询所有的文章信息
     *
     * @param columnId 栏目分类
     * @return
     */
    List<Article> backstageSelectAll(int columnId);

    /**
     * 上传文章保存到数据库
     *
     * @param resultArticle 文章对象
     * @param
     * @return
     */
    int addToArticle(Article resultArticle);

    /**
     * 根据传入的ID进行删除文章
     *
     * @param articleId 传入的ID
     * @return
     */
    int deleteById(int articleId);

    /**
     * 根据传入的视频ID进行删除
     *
     * @param videoId
     * @return
     */
    int deleteVideoById(int videoId);

    /**
     * 根据id查询出文章的详细信息
     *
     * @param articleId 文章ID
     * @return
     */
    Article selectArticleById(int articleId);

    /**
     * 查看推荐的文章以及视频信息集合
     *
     * @param columnId 栏目ID
     * @param pageNum  从num页开始
     * @param pageSize 每页展示size条
     * @return
     */
    List<Article> selectByRecommend(int columnId, int pageNum, int pageSize);

    /**
     * 查询文章的总数，用于分页使用
     *
     * @param columnId 栏目ID
     * @return
     */
    int getArticleCount(int columnId);

    /**
     * 查询文章视频集合根据栏目ID进行查询
     *
     * @param columnId 栏目Id
     * @param pageNum  从num页开始查询
     * @param pageSize 每页展示size条数据
     * @return
     */
    List<Article> selectArticleVideoListByColumnId(int columnId, int pageNum, int pageSize);

    /**
     * 根据栏目ID查询视频类文章的总条数
     *
     * @param columnId
     * @return
     */
    int selectArticleVideoCount(int columnId);

    /**
     * 后台系统查询文章数据分页展示
     *
     * @param columnId 栏目中的文章类ID
     * @param pageNum  从那页开始展示数据
     * @param pageSize 每页展示数据的长度
     * @return
     */
    List<Article> backstageArticlePaging(int columnId, int pageNum, int pageSize);

    /**
     * 查询视频类的所有信息列表分页查询
     *
     * @param columnId 栏目ID
     * @param pageNum  从那页开始查询
     * @param pageSize 每页展示的数据条数
     * @return
     */
    List<Article> backstageGetVideoListPaging(int columnId, int pageNum, int pageSize);

}
