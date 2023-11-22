package com.lz.applet.controller;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lz.applet.entity.Article;
import com.lz.applet.entity.Lecturer;
import com.lz.applet.service.LecturerService;
import com.lz.applet.util.FastDfsUtils;
import com.lz.applet.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 后台讲师管理
 *
 * @author dell
 * @date 2020-06-04 15:02
 */
@Controller
@Slf4j
@CrossOrigin
public class BackstageLecturerController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${fast.path}")
    private String fastPath;

    /**
     * 查询所有的讲师信息
     *
     * @param request
     * @return
     */
    @RequestMapping("backstageGetLecturerList")
    public String backstageGetLecturer(HttpServletRequest request) {
        try {

            List<Lecturer> lecturerList = lecturerService.selectAll();
            request.setAttribute("lecturerList", lecturerList);
            return "lecturer";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台获取讲师信息失败" + e.getMessage());
            return "error";
        }
    }


    /**
     * 添加讲师信息
     *
     * @param lecturer
     * @return
     */
    @RequestMapping(value = "backstageAddToLecturer", method = RequestMethod.POST)
    public String backstageAddToLecturer(@RequestParam("file") MultipartFile multipartFile, Lecturer lecturer) {
        try {
            String path = FastDfsUtils.uploadLecturer(multipartFile, fastFileStorageClient, fastPath);
            System.out.println("上传路径" + path);
            System.out.println(path);
            if (null != path) {
                lecturer.setImg(path);
                int result = lecturerService.addArticle(lecturer);
                if (result > 0) {
                    return "forward:/backstageGetLecturerList";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台添加讲师信息失败" + e.getMessage());
        }
        return "error";
    }

    /**
     * 删除选择的讲师信息根据id进行删除
     *
     * @param lecturerId 讲师id
     * @return
     */
    @RequestMapping("deleteLecturerId")
    public String deleteLecturerId(int lecturerId) {
        try {
            int result = lecturerService.deleteLecturerById(lecturerId);
            if (result > 0) {
                return "forward:/backstageGetLecturerList";
            } else {
                log.error("后台删除讲师信息失败");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台删除讲师信息异常" + e.getMessage());
            return "error";
        }
    }

    /**
     * 小程序端访问讲师信息
     *
     * @param pageNum  从那条数据开始查询
     * @param pageSize 每页展示数据的条数
     * @return
     */
    @RequestMapping(value = "getLecturerList", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getLecturerList(int pageNum, int pageSize) {
        try {
            List<Lecturer> lecturerList = lecturerService.getLecturerList(pageNum, pageSize);
            int count = lecturerService.count();
            HashMap<String, Object> map = new HashMap<>(16);
            map.put("data", lecturerList);
            map.put("count", count);
            return new ResultUtil(200, "请求成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("小程序用户访问讲师列表报错" + e.getMessage());
            return new ResultUtil(500, "请求失败");
        }
    }

    /**
     * 根据讲师名称查询这个讲师对应的文章和视频列表
     *
     * @param lecturerName 讲师名称
     * @param pageNum      从那条数据开始查询
     * @param pageSize     每页展示多少条数据
     * @return
     */
    @RequestMapping(value = "getByLecturerName", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil getByLecturerName(String lecturerName, int pageNum, int pageSize) {

        try {
            //查询讲师所对应的文章视频信息
            List<Article> articleList = lecturerService.getByLecturerName(lecturerName, pageNum, pageSize);
            //获取查询数据的总数
            int count = lecturerService.getCount(lecturerName);
            return new ResultUtil(200, "请求成功", ResultUtil.result(articleList, count));
        } catch (Exception e) {
            log.error("查询讲师课程信息失败" + e.getMessage());
            e.printStackTrace();
            return new ResultUtil(500, "请求失败", e.getMessage());
        }
    }

}
