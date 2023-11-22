package com.lz.applet.controller;

import com.lz.applet.entity.Comment;
import com.lz.applet.service.CommentService;
import com.lz.applet.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户留言控制器
 *
 * @author dell
 * @date 2020-06-09 10:47
 */
@Controller
@CrossOrigin
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加评论
     *
     * @param openId     需要根据用户的唯一标识查询用户的基本信息
     * @param cContentid 评论对应的文章ID
     * @param cContent   评论的内容
     * @return
     */
    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil addComment(String openId, int cContentid, String cContent) {

        boolean isOk = false;
        try {
            //添加评论
            isOk = commentService.addComment(openId, cContentid, cContent);
            if (isOk) {
                return new ResultUtil(200, "评论成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("小程序添加留言失败" + e.getMessage());
        }
        return new ResultUtil(500, "用户未登录，评论失败");
    }

    /**
     * 根据文章id查询所有的评论
     *
     * @param articleId 传入文章的ID
     * @return
     */
    @RequestMapping(value = "getCommentList", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getCommentList(int articleId) {
        try {
            List<Comment> commentList = commentService.selectCommentList(articleId);
            return new ResultUtil(200, "请求成功", commentList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("小程序查询所有的评论信息失败" + e.getMessage());
            return new ResultUtil(500, "请求失败");
        }
    }

    /**
     * 小程序端查询留言部分信息
     * 查询所有的留言信息。分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "getCommentAllPaging", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getCommentAllPaging(int pageNum, int pageSize) {
        try {
            //这里查询的是所有的留言信息，这里的留言的字段应该是1
            int state = 1;
            List<Comment> commentList = commentService.selectAll(pageNum, pageSize, state);
            //查询所有留言的信息条数
            int count = commentService.LeavingCount(state);
            return new ResultUtil(200, "请求成功", ResultUtil.resultComment(commentList, count));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台获取用户留言评论列表失败" + e.getMessage());
            return new ResultUtil(500, "请求失败");
        }
    }

    /**
     * 用户留言部分
     * 用户添加留言
     *
     * @param openId   用户的唯一ID
     * @param cContent 用户的留言内容
     * @return
     */
    @RequestMapping(value = "addLeavingAMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil addLeavingAMessage(String openId, String cContent) {

        try {
            //区分这条消息是留言还是评论，state是1表示是留言
            int state = 1;
            //添加留言信息
            int result = commentService.addLeavingAMessage(openId, cContent, state);
            if (result > 0) {
                System.out.println("用户留言成功");
                return new ResultUtil(200, "用户留言成功");
            } else {
                return new ResultUtil(500, "用户留言失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户留言失败" + e.getMessage());
            return new ResultUtil(500, "小程序用户留言失败");
        }
    }


    /**
     * 根据id删除留言信息以及留言下的回复
     *
     * @param commentId 留言id
     * @return
     */
    @RequestMapping("deleteCommentById")
    public String deleteCommentById(int commentId) {
        try {
            int result = commentService.deleteCommentById(commentId);
            if (result > 0) {
                return "forward:/getCommentAll";
            } else {
                log.error("根据留言id删除留言失败id=" + commentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "error";
    }

    /**
     * 后台的查询就直接查询出所有的不区分留言和评论
     * 查询所有的评论/回复信息
     *
     * @return
     */
    @RequestMapping("getCommentAll")
    public String getCommentAll(HttpServletRequest request) {
        try {
            List<Comment> commentList = commentService.getCommentAll();
            request.setAttribute("commentList", commentList);
            return "comment";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台查询留言下的回复信息失败" + e.getMessage());
            return "error";
        }
    }

    /**
     * 后台查询所有的留言信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("backGetCommentAll")
    @ResponseBody
    public ResultUtil backGetCommentAll(int pageNum, int pageSize) {
        try {
            List<Comment> commentList = commentService.backGetCommentAll(pageNum, pageSize);
            int count = commentService.count();
            return new ResultUtil(200, "请求成功", ResultUtil.resultComment(commentList, count));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(500, "请求失败", e.getMessage());
        }

    }
}
