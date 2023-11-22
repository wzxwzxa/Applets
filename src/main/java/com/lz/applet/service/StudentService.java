package com.lz.applet.service;

import com.lz.applet.entity.Student;
import com.lz.applet.util.ResultUtil;

/**
 * @author dell
 * @date 2020-06-05 22:29
 */
public interface StudentService {

    /**
     * 用户登录
     *
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    ResultUtil onLogin(String encryptedData, String iv, String code);

    /**
     * 根据用户唯一的openid查询用户是否存在
     *
     * @param openid
     * @return
     */
    Student findByOpenId(String openid);

    /**
     * 添加用户信息到数据库
     *
     * @param student
     */
    void insert(Student student);
}
