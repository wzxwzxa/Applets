package com.lz.applet.service;

import com.lz.applet.entity.Reply;

import java.util.List;

/**
 * 留言回复接口
 *
 * @author dell
 * @date 2020-06-09 14:20
 */
public interface ReplyService {
    /**
     * 添加留言回复
     * @param openId 回复这的唯一ID用于查看用户信息
     * @param rContent 回复的内容
     * @param rOtherid 回复用户的ID
     * @param articleId
     * @return
     */
    boolean addReply(String openId, String rContent, int rOtherid, int articleId);

    /**
     * 根据留言id查询留言下的所有回复
     * @param commentId 留言id
     * @return
     */
    List<Reply> selectByCommentId(int commentId);

    /**
     * 删除留言回复
     * @param replyId 回复消息Id
     * @return
     */
    int deleteReplyById(int replyId);

    /**
     * 添加留言回复信息
     * @param openId 用户的唯一标识
     * @param rContent 用户的回复信息
     * @param rOtherid 回复所对应的留言ID
     * @return
     */
    int addLeavingReply(String openId, String rContent, int rOtherid);
}
