package com.lz.applet.mapper;

import com.lz.applet.entity.Student;
import com.lz.applet.entity.StudentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    /**
     * 查询这个openId下有没有用户对象
     * @param openid
     * @return
     */
    Student selectByOpenId(String openid);

    /**
     * 根据openId查询用户信息
     * @param openid
     * @return
     */
    Student findByopenId(String openid);
}