package com.lz.applet.service;

import com.lz.applet.entity.Comment;

import java.util.List;

/**
 * @author dell
 * @date 2020-06-09 10:48
 */
public interface CommentService {

    /**
     * 添加留言
     *
     * @param openId     需要根据用户的唯一标识查询用户的基本信息
     * @param cContentid 评论对应的文章ID
     * @param cContent   评论的内容
     * @return
     */
    boolean addComment(String openId, int cContentid, String cContent);

    /**
     * 查询所有的留言信息
     *
     * @param articleId 文章id
     * @return
     */
    List<Comment> selectCommentList(int articleId);

    /**
     * 查询所有的用户评论信息
     *
     * @return
     */
    List<Comment> getCommentAll();

    /**
     * 根据留言id删除留言信息及留言下的回复信息
     *
     * @param commentId 留言ID
     * @return
     */
    int deleteCommentById(int commentId);

    /**
     * 查询所有的留言信息分页
     *
     * @param pageNum  从那条数据开始查询
     * @param pageSize 每页展示数据的条数
     * @param state    区别评论和留言的信息
     * @return
     */
    List<Comment> selectAll(int pageNum, int pageSize, int state);

    /**
     * 查询评论的总条数
     *
     * @return
     */
    int count();


    /**
     * 添加用户留言
     *
     * @param openId   用户id,根据用户的唯一id获取到留言用户信息
     * @param cContent 用户的评论内容
     * @param state    区别是留言还是评论的
     * @return
     */
    int addLeavingAMessage(String openId, String cContent, int state);

    /**
     * 查询所有的留言信息总条数
     *
     * @param state 区分留言还是评论的字段
     * @return
     */
    int LeavingCount(int state);

    /**
     * 后台查询所有的评论以及留言信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Comment> backGetCommentAll(int pageNum, int pageSize);
}
