package com.lz.applet.mapper;

import com.lz.applet.entity.Reply;
import com.lz.applet.entity.ReplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    long countByExample(ReplyExample example);

    int deleteByExample(ReplyExample example);

    int deleteByPrimaryKey(Integer rId);

    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> selectByExample(ReplyExample example);

    Reply selectByPrimaryKey(Integer rId);

    int updateByExampleSelective(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExample(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    /**
     * 根据留言的id删除留言下的所有回复
     *
     * @param commentId
     * @return
     */
    int deleteCommentById(int commentId);

    /**
     * 根据留言评论id查询下面的所有回复
     *
     * @param getcId
     * @return
     */
    List<Reply> selectByCommentId(Integer getcId);
}