package com.lz.applet.controller;

import com.lz.applet.entity.User;
import com.lz.applet.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户登录
 *
 * @author dell
 * @date 2020-05-30 22:19
 */
@Controller
public class BackstageUserLoginController {

    @Autowired
    private UserService userService;

    /**goLogin","/
     * 用户进入登录界面
     * @return
     */
    @RequestMapping(value = {"/","goLogin"})
    public String goLogin(){
        return "login";
    }
    /**
     * 用户输入登录信息进行登录校验
     * @param user
     * @return
     */
    @RequestMapping("login")
    public String UserLogin(User user, Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            //执行登录方法，如果没有异常就说明成功了
            subject.login(token);
            model.addAttribute("username",user.getUsername());
            return "myIndex";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    /**
     * 管理员管理
     * 展示所有的管理员信息
     * @return
     */
    @RequestMapping("userManagement")
    public String userManagement(HttpServletRequest request){
        //查询所有的用户信息
        List<User> userList = userService.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
        request.setAttribute("userList",userList);
        return "admin";
    }

    /**
     * 添加管理员
     * @param user 填写的用户信息
     * @return
     */
    @RequestMapping("addUser")
    public String addUser(User user){
        try {
            Boolean aBoolean = userService.addUser(user);
            if (aBoolean){
                return "forward:/userManagement";
            }else {
                //用户已存在
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping("deleteUser")
    public String deleteUser(int userId){
        System.out.println(userId);
        Boolean result = userService.delete(userId);
        if (result){
           return "forward:/userManagement";
        }
        return "error";
    }

    @RequestMapping("updateUser")
    public String updateUser(User user){
        Boolean result = userService.updateUser(user);
        if (result){
            return "forward:/userManagement";
        }
        return "error";
    }

    @RequestMapping("logOut")
    public String logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
