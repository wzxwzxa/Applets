package com.lz.applet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lz.applet.entity.Student;
import com.lz.applet.service.StudentService;
import com.lz.applet.util.HttpClientUtil;
import com.lz.applet.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 小程序登录
 *
 * @author dell
 * @date 2020-06-05 13:36
 */
@Controller
@CrossOrigin
@Slf4j
public class StudentLoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${app.appId}")
    private String appId;

    @Value("${app.secret}")
    private String appSecret;

    @Value("${app.grant_type}")
    private String grantType;

    /**
     * 小程序端的用户登录
     *
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    @RequestMapping(value = "onLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil onLogin(String encryptedData, String iv, String code) {

        return studentService.onLogin(encryptedData, iv, code);

    }

    /**
     * 小程序授权登录
     *
     * @param code      临时登录凭证
     * @param rawData   用户非铭感信息
     * @param signature 签名
     *                  //@param encryptedData 用户敏感信息
     *                  //@param iv 解密算法的向量
     * @return
     * @RequestParam(value = "encryptedData",required = false) String encryptedData,
     * @RequestParam(value = "iv",required = false) String iv
     * @RequestParam(value = "rawData",required = false) String rawData,
     * @RequestParam(value = "signature",required = false) String signature
     */
    @RequestMapping(value = "studentLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil studentLogin(String code, String rawData, String signature) {
        try {
            if (rawData == null) {
                return new ResultUtil(500, "用户信息不能为空");
            }
            System.out.println(code);
            String apiUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=" + grantType;
            String responseBody = HttpClientUtil.doGet(apiUrl);
            JSONObject jsonObject = JSON.parseObject(responseBody);
            String openid = jsonObject.getString("openid");
            System.out.println("openId" + openid);
            String session_key = jsonObject.getString("session_key");
            System.out.println("sessionKey" + session_key);

            //将用户非敏感信息获取到转换为json对象
            JSONObject rawDataJson = JSON.parseObject(rawData);

            System.out.println("用户信息rawDate" + rawData);
            System.out.println("用户信息rawDateJson" + rawDataJson);
            System.out.println("用户昵称" + rawDataJson.getString("nickName"));

            //签名
            System.out.println("签名" + signature);

            //请求微信客户端封装的方法并返回一个jsonObject
            //JSONObject sessionKeyOrOpenId = UrlUtil.getSessionKeyOrOpenId(code, appId, appSecret);
            //System.out.println("请求获取的SessionAndOpenId="+sessionKeyOrOpenId);
            //openId
            //String openid = sessionKeyOrOpenId.getString("openid");
            //session_key
            //String sessionKey = sessionKeyOrOpenId.getString("session_key");
            //System.out.println("openId="+openid+",session_key="+sessionKey);
            //调用数据库查询是否有openId对应的用户
            Student student = studentService.findByOpenId(openid);
            //uuid生成唯一key
            //String sKey = UUID.randomUUID().toString();
            //判断查询的用户是否为空，如果等于空的话就需要将用户信息入库保存起来


            if (null == student) {
                String nickName = rawDataJson.getString("nickName");
                String avatarUrl = rawDataJson.getString("avatarUrl");
                String gender = rawDataJson.getString("gender");
                String city = rawDataJson.getString("city");
                String country = rawDataJson.getString("country");
                String province = rawDataJson.getString("province");
                //给对象赋值
                student = new Student();
                student.setOpenid(openid);
                student.setNickName(nickName);
                student.setAvatarurl(avatarUrl);
                student.setGender(gender);
                student.setCity(city);
                student.setCountry(country);
                student.setProvince(province);
                //将用户信息添加到数据库
                System.out.println(student);
                studentService.insert(student);
            } else {
                log.info("登录失败");
            }
            return new ResultUtil(200, "登录成功", openid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(500, "请求失败", e.getMessage());
        }
    }
}