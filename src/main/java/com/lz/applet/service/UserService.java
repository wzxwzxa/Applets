package com.lz.applet.service;

import com.lz.applet.entity.User;

import java.util.List;

/**
 * @author dell
 * @date 2020-05-30 22:44
 */
public interface UserService {

    /**
     * 根据用户输入的登录密码判断是否登录
     *
     * @param user
     * @return
     */
    Boolean login(User user);

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 根据传入的id进行删除
     *
     * @param userId
     * @return
     */
    Boolean delete(int userId);

    /**
     * 根据传入的ID返回用户信息
     *
     * @param userId
     * @return
     */
    User selectByUserKey(int userId);

    /**
     * 根据传入的用户信息添加管理员
     *
     * @param user
     * @return
     */
    Boolean addUser(User user);

    /**
     * 修改管理员信息
     *
     * @param user
     * @return
     */
    Boolean updateUser(User user);

    /**
     * 根据名字查询是否有这个用户
     *
     * @param username
     * @return
     */
    User queryUserByName(String username);
}
