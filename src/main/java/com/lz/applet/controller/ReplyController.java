package com.lz.applet.controller;

import com.lz.applet.entity.Reply;
import com.lz.applet.service.ReplyService;
import com.lz.applet.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 回复控制器
 *
 * @author dell
 * @date 2020-06-09 14:19
 */
@Controller
@Slf4j
@CrossOrigin
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加评论回复
     *
     * @param openId    回复这的唯一ID用于查看用户信息
     * @param rContent  回复的内容
     * @param rOtherid  回复评论的ID  回复所对应评论的ID
     * @param articleId
     * @return
     */
    @RequestMapping("addReply")
    @ResponseBody
    public ResultUtil addReply(String openId, String rContent, int rOtherid, int articleId) {
        try {
            System.out.println("duduBranch提交");
            boolean isOk = replyService.addReply(openId, rContent, rOtherid, articleId);
            if (isOk) {
                return new ResultUtil(200, "回复成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("回复失败，错误原因" + e.getMessage());
        }
        return new ResultUtil(500, "用户未登录，回复失败");
    }

    /**
     * 根据评论id查询留言下的所有回复信息
     *
     * @param commentId
     * @return
     */
    @RequestMapping("selectReplyById")
    @ResponseBody
    public ResultUtil selectReply(int commentId) {
        try {
            List<Reply> replyList = replyService.selectByCommentId(commentId);
            return new ResultUtil(200, "请求成功", replyList);
        } catch (Exception e) {
            log.error("获取回复失败" + e.getMessage());
            return new ResultUtil(500, "请求失败");
        }
    }

    /**
     * 用户留言回复
     *
     * @param openId   用户的唯一标识根据这个id到数据库获取用户的信息保存到留言回复中
     * @param rContent 用户回复的信息
     * @param rOtherid 回复所对应的留言id
     * @return
     */
    @RequestMapping(value = "addLeavingReply", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil addLeavingReply(String openId, String rContent, int rOtherid) {
        try {
            int result = replyService.addLeavingReply(openId, rContent, rOtherid);
            if (result > 0) {
                return new ResultUtil(200, "留言回复成功");
            } else {
                return new ResultUtil(500, "用户未登录回复失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户添加留言回复失败" + e.getMessage());
            return new ResultUtil(500, "留言回复失败");
        }
    }


    /**
     * 后台删除
     * 删除评论下的回复
     *
     * @param replyId 回复信息id
     * @return
     */
    @RequestMapping("deleteReplyById")
    public String deleteReplyById(int replyId) {
        try {
            int result = replyService.deleteReplyById(replyId);
            if (result > 0) {
                return "forward:/getCommentAll";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "error";
    }
}
