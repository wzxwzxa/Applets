package com.lz.applet.service.impl;

import com.lz.applet.entity.Reply;
import com.lz.applet.entity.Student;
import com.lz.applet.mapper.ReplyMapper;
import com.lz.applet.mapper.StudentMapper;
import com.lz.applet.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author dell
 * @date 2020-06-09 14:20
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 添加回复
     * @param openId 回复这的唯一ID用于查看用户信息
     * @param rContent 回复的内容
     * @param rOtherid 回复用户的ID 回复所对应留言的id
     * @param articleId 文章ID
     * @return
     */
    @Override
    public boolean addReply(String openId, String rContent, int rOtherid, int articleId) {
        //先根据学员的openId查询出学员信息
        Student student = studentMapper.selectByOpenId(openId);
        if (null==student){
            return false;
        }
        Reply reply = addReply(student, rContent, rOtherid);
        //留言所对应的文章
        reply.setrContentid(articleId);

        //被回复者名称
        reply.setrRespondent(student.getNickName());

        //留言回复者的头像
        reply.setrAvatarUrl(student.getAvatarurl());
        //将留言的回复信息存入到数据库
        int insert = replyMapper.insert(reply);
        if (insert>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据留言id查询回复
     * @param commentId 留言id
     * @return
     */
    @Override
    public List<Reply> selectByCommentId(int commentId) {
        List<Reply> replyList = replyMapper.selectByCommentId(commentId);
        return replyList;
    }

    /**
     * 删除回复消息根据ID
     * @param replyId 回复消息Id
     * @return
     */
    @Override
    public int deleteReplyById(int replyId) {
        int deleteByPrimaryKey = replyMapper.deleteByPrimaryKey(replyId);
        return deleteByPrimaryKey;
    }

    /**
     * 添加留言回复
     * @param openId 用户的唯一标识
     * @param rContent 用户的回复信息
     * @param rOtherid 回复所对应的留言ID
     * @return
     */
    @Override
    public int addLeavingReply(String openId, String rContent, int rOtherid) {
        //查询用户的信息
        Student student = studentMapper.selectByOpenId(openId);

        if (null==student){
            return -1;
        }

        Reply reply = addReply(student, rContent, rOtherid);

        return replyMapper.insert(reply);
    }

    public static Reply addReply(Student student,String rContent,int rOtherid){

        //将学员的信息放入回复信息中
        Reply reply = new Reply();
        //回复用户的id
        reply.setrUserid(String.valueOf(student.getId()));
        //回复用户的昵称
        reply.setrName(student.getNickName());
        //添加时间——设置日期格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatTime = format.format(new Date());
        reply.setrCreatime(formatTime);
        //用户回复的内容
        reply.setrContent(rContent);

        //被回复者名称
        reply.setrRespondent(student.getNickName());

        //给那个留言用户的回复——留言者的ID，回复所对应的留言的id
        reply.setrOtherid(String.valueOf(rOtherid));
        //添加留言回复者的头像
        reply.setrAvatarUrl(student.getAvatarurl());

        return reply;
    }
}
