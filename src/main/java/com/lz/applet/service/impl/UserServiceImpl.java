package com.lz.applet.service.impl;

import com.lz.applet.entity.User;
import com.lz.applet.mapper.UserMapper;
import com.lz.applet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dell
 * @date 2020-05-30 22:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 判断用户登录信息是否正确
     * @param user
     * @return
     */
    @Override
    public Boolean login(User user) {
        User result = userMapper.selectUserPassword(user.getUsername());
        if (null != result && result.getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }

    /**
     * 查询所有用户
     * @return 返回所有的用户信息
     */
    @Override
    public List<User> selectAll() {
        List<User> userList = userMapper.selectAll();
        for (User user : userList) {
            if (null!=user.getPerms() && user.getPerms()!=""){
                if (user.getPerms().equals("user:admin")){
                    user.setPerms("超级管理员");
                }else {
                    user.setPerms("管理员");
                }
            }
        }
        return userList;
    }

    /**
     * 删除管理员
     * @param userId 管理员ID
     * @return
     */
    @Override
    public Boolean delete(int userId) {
        int i = userMapper.deleteByPrimaryKey(userId);
        System.out.println(i);
        if (i!=0){
            return true;
        }
        return false;
    }

    /**
     * 根据传入的ID查询
     */
    @Override
    public User selectByUserKey(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    /**
     * 加入新的管理员
     * @param user
     * @return
     */
    @Override
    public Boolean addUser(User user) {
        //查询这个用户是否已经注册
        User user1 = userMapper.selectUserPassword(user.getUsername());
        //如果已经存在就返回false
        if (null!=user1){
            return false;
        }else {
            //添加
            int insert = userMapper.insert(user);
            System.out.println(insert);
            System.out.println(user.getId());
            return true;
        }
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public Boolean updateUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User queryUserByName(String username) {
        return userMapper.selectUserPassword(username);
    }
}
