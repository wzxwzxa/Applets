package com.lz.applet.service;

import com.lz.applet.entity.Article;
import com.lz.applet.entity.Lecturer;

import java.util.List;

/**
 * 讲师管理接口
 *
 * @author dell
 * @date 2020-06-04 15:16
 */
public interface LecturerService {

    /**
     * 查询所有讲师信息
     *
     * @return
     */
    List<Lecturer> selectAll();

    /**
     * 添加讲师信息
     *
     * @param lecturer 讲师信息
     * @return
     */
    int addArticle(Lecturer lecturer);

    /**
     * 根据id进行讲师删除
     *
     * @param lecturerId 讲师ID
     * @return
     */
    int deleteLecturerById(int lecturerId);

    /**
     * 所有的导师信息分页查询
     *
     * @param
     * @param pageNum  从那条数据开始查询
     * @param pageSize 每页展示的数据条数
     * @return
     */
    List<Lecturer> getLecturerList(int pageNum, int pageSize);

    /**
     * 查询讲师总数
     *
     * @return
     */
    int count();

    /**
     * 查询讲师对应的是视频和
     *
     * @param lecturerName 讲师名称
     * @param pageNum      从那条数据开始查询
     * @param pageSize     每页展示数据的条数
     * @return
     */
    List<Article> getByLecturerName(String lecturerName, int pageNum, int pageSize);

    /**
     * 获取查询数据的总数
     *
     * @param lecturerName
     * @return
     */
    int getCount(String lecturerName);
}
