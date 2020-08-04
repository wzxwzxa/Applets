package com.lz.applet.mapper;

import com.lz.applet.entity.User;
import com.lz.applet.entity.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据 用户名查询用户是否存在
     * @param username
     * @return
     */
    User selectUserPassword(String username);

    /**
     * 查询所有的用户
     * @return
     */
    List<User> selectAll();

}