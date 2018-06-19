package com.imooc.controller;

import com.imooc.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * @author: JefferyJu
 * @date: 2018/6/18
 */
//@Controller
//@ResponseBody
@RestController // 等同于上面两个注解
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    GirlProperties girlProperties;

    /**
     * // method = RequestMethod.POST 可以不加，get/post 都可以获取到
     * @return
     */
    //@RequestMapping(value = "/say", method = RequestMethod.POST)
    @PostMapping(value = "/say")
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId){

        return "id:" + myId;
    }

}
