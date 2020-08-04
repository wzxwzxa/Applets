package com.lz.applet.service.impl;

import com.github.pagehelper.PageHelper;
import com.lz.applet.entity.Comment;
import com.lz.applet.entity.Reply;
import com.lz.applet.entity.Student;
import com.lz.applet.mapper.CommentMapper;
import com.lz.applet.mapper.ReplyMapper;
import com.lz.applet.mapper.StudentMapper;
import com.lz.applet.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dell
 * @date 2020-06-09 10:48
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplyMapper replyMapper;

    /**
     * 添加评论
     * @param openId 需要根据用户的唯一标识查询用户的基本信息
     * @param cContentid 评论对应的文章ID
     * @param cContent 评论的内容
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addComment(String openId, int cContentid, String cContent) {

        //1、先根据这个用户的openId查询到评论用户的基本信息
        Student student = studentMapper.selectByOpenId(openId);
        if (student==null){
            return false;
        }
        //2、将需要的用户信息添加到评论中
        Comment comment = new Comment();
        //评论者名称
        comment.setcName(student.getNickName());
        //评论者id
        comment.setcUserid(String.valueOf(student.getId()));
        //内容id(在哪一篇文章下的留言)
        comment.setcContentid(cContentid);
        //这里0表示这是评论的消息
        comment.setcState(0);
        //添加留言这的头像
        comment.setcOtherid(student.getAvatarurl());
        int insert = insert(comment, cContent);
        if (insert>0){
            return true;
        }
        return false;
    }

    /**
     * 查询文章下的所有留言及回复信息
     * @param articleId 文章id
     * @return
     */
    @Override
    public List<Comment> selectCommentList(int articleId) {
        //查询出所有的留言信息
        List<Comment> commentList = commentMapper.selectByArticleId(articleId);
        //查询评论下的回复
        List<Comment> commentAddReply = commentAddReply(commentList);
        return commentAddReply;
    }

    /**
     * 查询所有的留言评论信息
     * @return
     */
    @Override
    public List<Comment> getCommentAll() {
        //查询所有的留言
       List<Comment> commentList = commentMapper.backSelectAll();

       //在查询这个留言下的回复
       // List<Comment> commentAddReply = commentAddReply(commentList);
        return commentList;
    }

    /**
     * 根据留言id删除留言信息及留言下的回复信息
     * @param commentId 留言ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCommentById(int commentId) {
        //1、先根据留言的ID删除所有留言下的回复信息
       int resultReply = replyMapper.deleteCommentById(commentId);
       //2、删除完留言下的所有回复后再删除所选择的留言
        int i = commentMapper.deleteByPrimaryKey(commentId);
        return i;
    }

    /**
     * 小程序查询所有留言部分的信息
     * @param pageNum 从那条数据开始查询
     * @param pageSize  每页展示数据的条数
     * @return
     */
    @Override
    public List<Comment> selectAll(int pageNum, int pageSize,int state) {
        PageHelper.startPage(pageNum,pageSize);
        //这里查询的是留言下的信息
        List<Comment> commentList = commentMapper.selectAllState(state);
        List<Comment> commentListAndReply = commentAddReply(commentList);
        return commentListAndReply;
    }

    @Override
    public int count() {
        return commentMapper.count();
    }

    /**
     * 用户添加留言
     * @param openId 用户id,根据用户的唯一id获取到留言用户信息
     * @param cContent 用户的评论内容
     * @return
     */
    @Override
    public int addLeavingAMessage(String openId, String cContent,int state) {
        //根据用的唯一openId查询用户的基本信息
        Student student = studentMapper.selectByOpenId(openId);
        if (null == student){
            return -1;
        }
        Comment comment = new Comment();
        //留言用户的id
        comment.setcUserid(String.valueOf(student.getId()));
        //添加信息是留言信息
        comment.setcState(state);
        //留言用户的昵称
        comment.setcName(student.getNickName());
        //添加留言者头像
        comment.setcOtherid(student.getAvatarurl());
        //调用方法存入留言信息
        System.out.println("用户留言信息#############"+cContent);
        return insert(comment,cContent);
    }

    /**
     * 查询所有的留言类信息总条数
     * @param state 区分留言还是评论的字段
     * @return
     */
    @Override
    public int LeavingCount(int state) {
       return commentMapper.LeavingCount(state);
    }

    @Override
    public List<Comment> backGetCommentAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return commentMapper.backGetCommentAll();
    }


    public List<Comment> commentAddReply(List<Comment> commentList){
        ArrayList<Comment> commentArrayList = new ArrayList<>();
        for (Comment comment : commentList) {
            List<Reply> replyList = replyMapper.selectByCommentId(comment.getcId());
            if (null!=replyList && replyList.size()>0){
                comment.setReplyList(replyList);
            }
            commentArrayList.add(comment);
        }
        return commentArrayList;
    }

    public int insert(Comment comment,String content){
        //添加时间——设置日期格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatTime = format.format(new Date());
        comment.setcCreatetime(formatTime);
        //设置添加的内容
        comment.setcContent(content);
        int insert = commentMapper.insert(comment);
        return insert;
    }
}
