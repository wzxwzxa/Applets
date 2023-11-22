package com.lz.applet.mapper;

import com.lz.applet.entity.Article;
import com.lz.applet.entity.ArticleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 查询所有的文章
     *
     * @param columnId 查询的类别id
     * @return
     */
    List<Article> backstageSelectAll(int columnId);

    /**
     * 根据栏目Id查询文章列表
     *
     * @param columnId 栏目ID
     * @return
     */
    List<Article> selectByColumnId(int columnId);

    /**
     * 根据推荐0/1查询推荐文章或视频信息
     *
     * @param columnId 栏目ID
     * @return
     */
    List<Article> selectByRecommend(int columnId);

    /**
     * 查询文章总数
     *
     * @param columnId 栏目对应的文章类ID
     * @return
     */
    int selectCount(int columnId);

    /**
     * 根据栏目ID查询推荐信息
     *
     * @param columnId
     * @return
     */
    List<Article> backstageSelectRecommendList(int columnId);

    /**
     * 根据讲师名称查询该讲师对应的所有文章和视频信息
     *
     * @param lecturerName
     * @return
     */
    List<Article> getByLecturerName(String lecturerName);

    /**
     * 跟导师的名称获取这个导师对应的文章视频总数
     *
     * @param lecturerName
     * @return
     */
    int getCount(String lecturerName);

}