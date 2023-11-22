package com.lz.applet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lz.applet.annotation.PermissionAnnotation;
import org.springframework.web.bind.annotation.*;

@RestController
public class AopController {

    @GetMapping(value = "getTest")
    public JSONObject aopTest(){
        System.out.println("222222222222");
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @RequestMapping(value = "check",method = RequestMethod.POST)
    @ResponseBody
    @PermissionAnnotation()
    public JSONObject getGroupList(@RequestBody JSONObject request) {
        System.out.println("2222222222222");
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @RequestMapping(value = "before",method = RequestMethod.POST)
    @ResponseBody
    @PermissionAnnotation()
    public JSONObject testBefore(){
        System.out.println("1111111111111111111111111111111111111111111");
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }
}
