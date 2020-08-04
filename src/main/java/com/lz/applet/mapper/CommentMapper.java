package com.lz.applet.mapper;

import com.lz.applet.entity.Comment;
import com.lz.applet.entity.CommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer cId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer cId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 根据文章id查询所有的留言信息
     * @param articleId 文章id
     * @return
     */
    List<Comment> selectByArticleId(int articleId);

    /**
     * 查询所有的留言/评论
     * @return
     */
    List<Comment> selectAll();

    /**
     * 查询留言的总条数
     * @return
     */
    int count();

    /**
     * 小程序端查询所有的留言部分信息
     * @param state
     * @return
     */
    List<Comment> selectAllState(int state);

    /**
     * 查询所有的留言部分信息的总数
     * @param state
     * @return
     */
    int LeavingCount(int state);

    /**
     * 后台查询所有的留言信息和评论信息
     * @return
     */
    List<Comment> backSelectAll();

    /**
     * 查询所有的评论和留言信息
     * @return
     */
    List<Comment> backGetCommentAll();
}