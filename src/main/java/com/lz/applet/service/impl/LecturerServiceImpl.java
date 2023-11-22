package com.lz.applet.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lz.applet.entity.Article;
import com.lz.applet.entity.Lecturer;
import com.lz.applet.mapper.ArticleMapper;
import com.lz.applet.mapper.LecturerMapper;
import com.lz.applet.service.LecturerService;
import com.lz.applet.util.FastDfsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 实现接口方法
 *
 * @author dell
 * @date 2020-06-04 15:23
 */
@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerMapper lecturerMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${fast.path}")
    private String fastDfs;

    /**
     * 查询所有的讲师信息
     *
     * @return 返回讲师List
     */
    @Override
    public List<Lecturer> selectAll() {

        return lecturerMapper.selectAll();

    }

    /**
     * 添加讲师信息
     *
     * @param lecturer 讲师信息
     * @return
     */
    @Override
    public int addArticle(Lecturer lecturer) {
        int insertSelective = lecturerMapper.insertSelective(lecturer);
        System.out.println(insertSelective);
        return insertSelective;
    }

    /**
     * 删除讲师信息 在同时也要删除讲师图片信息fastDfs里面的文件
     *
     * @param lecturerId 讲师ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteLecturerById(int lecturerId) {
        //先查询出这个id的讲师信息
        Lecturer lecturer = lecturerMapper.selectByPrimaryKey(lecturerId);
        //取出img存储的路径信息
        String lecturerImg = lecturer.getImg();
        String fastPath = lecturerImg.replace(fastDfs, "");
        System.out.println("文件名称" + fastPath);
        //调用fast删除文件
        boolean isOk = FastDfsUtils.deleteFastFile(fastPath, fastFileStorageClient);
        //文件删除成功再进行讲师数据删除
        if (isOk) {
            int key = lecturerMapper.deleteByPrimaryKey(lecturerId);
            return key;
        }
        return 0;
    }

    /**
     * 查询讲师的信息
     *
     * @param
     * @param pageNum  从那条数据开始查询
     * @param pageSize 每页展示的数据条数
     * @return
     */
    @Override
    public List<Lecturer> getLecturerList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        List<Lecturer> lecturerList = lecturerMapper.selectAll();
        return lecturerList;
    }

    /**
     * 查询讲师总数
     *
     * @return
     */
    @Override
    public int count() {
        return lecturerMapper.count();
    }

    /**
     * 根据讲师名称查询讲师的视频和文章
     *
     * @param lecturerName 讲师名称
     * @param pageNum      从那条数据开始查询
     * @param pageSize     每页展示数据的条数
     * @return
     */
    @Override
    public List<Article> getByLecturerName(String lecturerName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //查询
        List<Article> articleList = articleMapper.getByLecturerName(lecturerName);
        for (Article article : articleList) {

        }

        return articleList;
    }

    /**
     * 获取查询数据的总数
     *
     * @param lecturerName 根据这个名称进行查询
     * @return
     */
    @Override
    public int getCount(String lecturerName) {
        return articleMapper.getCount(lecturerName);
    }
}
