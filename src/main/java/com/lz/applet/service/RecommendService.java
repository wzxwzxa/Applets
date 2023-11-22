package com.lz.applet.service;

import com.lz.applet.entity.Article;

import java.util.List;

/**
 * 文章推荐接口
 *
 * @author dell
 * @date 2020-06-08 15:20
 */
public interface RecommendService {

    /**
     * 根据栏目ID查询推荐的文章信息
     *
     * @param columnId
     * @return
     */
    List<Article> getRecommendList(int columnId);

    /**
     * 删除文章或视频根据ID
     *
     * @param recommendId
     */
    int deleteRecommendById(int recommendId);

    /**
     * 根据类别id查询推荐类的信息
     *
     * @param columnId 推荐类id
     * @param pageNum  从那页开始查询数据
     * @param pageSize 每页展示多少条数据
     * @return
     */
    List<Article> getRecommendListPaging(int columnId, int pageNum, int pageSize);

    /**
     * 根据推荐类id去查询推荐类数据的重量
     *
     * @param columnId
     * @return
     */
    int selectCount(int columnId);

}
