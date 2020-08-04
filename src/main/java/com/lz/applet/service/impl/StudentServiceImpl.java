package com.lz.applet.service.impl;

import com.lz.applet.entity.Student;
import com.lz.applet.mapper.StudentMapper;
import com.lz.applet.service.StudentService;
import com.lz.applet.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author dell
 * @date 2020-06-05 22:32
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Value("${app.appId}")
    private String appId;

    @Value("${app.secret}")
    private String appSecret;

    @Value("${app.grant_type}")
    private String grantType;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ResultUtil onLogin(String encryptedData, String iv, String code) {
//        if (!StringUtils.isNoneBlank(code)){
//            return new ResultUtil(202,"未获取到用户凭证");
//        }
////        String apiUrl="https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+appSecret+"&js_code="+code+"&grant_type="+grantType;
////        System.out.println(apiUrl);
////        String responseBody = HttpClientUtil.doGet(apiUrl);
////        System.out.println(responseBody);
////        JSONObject jsonObject = JSON.parseObject(responseBody);
//        if (StringUtils.isNotBlank(jsonObject.getString("openid"))&&StringUtils.isNotBlank(jsonObject.getString("session_key"))){
//            //解密获取用户信息
//            JSONObject userInfo = WechatGetUserInfoUtil.getUserInfo(encryptedData, jsonObject.getString("session_key"), iv);
//            if (userInfo!=null){
//                Student student = new Student();
//                //将数据存入对象中
//                student.setOpenid((String) userInfo.get("openId"));
//                student.setNickName((String)userInfo.get("nickName"));
//                student.setGender((String)userInfo.get("gender"));
//                student.setCity((String) userInfo.get("city"));
//                student.setProvince((String) userInfo.get("province"));
//                student.setCountry((String) userInfo.get("country"));
//                student.setAvatarurl((String) userInfo.get("avatarUrl"));
//
//                //解密unionId & openId
//                if (userInfo.get("unionId")!=null){
//                    student.setUnionid((String) userInfo.get("unionId"));
//                }
//                //然后根据openId去数据库判断有没有该用户信息，若没有就存入到数据库，有则返回用户数据
//               Student resultStudent = studentMapper.selectByOpenId(student.getOpenid());
//                //如果没有这个openId的用户信息就将信息存到存入到库中
//                if (resultStudent==null){
//                    try {
//                        int selective = studentMapper.insertSelective(student);
//                        System.out.println("学员信息添加成功");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return new  ResultUtil(500,"student数据添加失败");
//                    }
//                }
//                return new ResultUtil(200,"成功登录",student.getOpenid());
//            }
//        }else {
//            return new ResultUtil(202,"未获取到用户openId或session");
//        }
//        return new ResultUtil(500,"请求api或解密失败");
        return null;
    }

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Override
    public Student findByOpenId(String openid) {
        return studentMapper.findByopenId(openid);
    }

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }
}
