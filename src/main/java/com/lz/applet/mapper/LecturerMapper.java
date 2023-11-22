package com.lz.applet.mapper;

import com.lz.applet.entity.Lecturer;
import com.lz.applet.entity.LecturerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LecturerMapper {
    long countByExample(LecturerExample example);

    int deleteByExample(LecturerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Lecturer record);

    int insertSelective(Lecturer record);

    List<Lecturer> selectByExample(LecturerExample example);

    Lecturer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Lecturer record, @Param("example") LecturerExample example);

    int updateByExample(@Param("record") Lecturer record, @Param("example") LecturerExample example);

    int updateByPrimaryKeySelective(Lecturer record);

    int updateByPrimaryKey(Lecturer record);

    /**
     * 查询所有的讲师信息
     */
    List<Lecturer> selectAll();

    /**
     * 查询所有的讲师的总数
     *
     * @return
     */
    int count();

    /**
     * 根据讲师名称查询对应的文章视频信息
     *
     * @param lecturerName
     * @return
     */
    List<Lecturer> getByLecturerName(String lecturerName);
}