package com.fandrproject.frpro.data.controller;

import com.fandrproject.frpro.data.util.RedisUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sml
 * 2020/07/26 02:20
 */

@Controller
@RequestMapping("model-data")
public class DataController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("data-page")
    @ResponseBody
    public ModelAndView dataPage() {
        ModelAndView mv = new ModelAndView("data/data");
        mv.addObject("key", redisUtil.get("data"));
        return mv;
    }

    @GetMapping("data-page2")
    public String dataPage2() {
        return "data/data2";
    }


    @GetMapping("get-data/{id}")
    @ResponseBody
    public Map<String, Object> getData(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", redisUtil.get("data"));
        return map;
    }


    @GetMapping("set-data/{key}/{value}")
    @ResponseBody
    public String setData(@PathVariable String key,@PathVariable String value) {
        redisUtil.set(key, value);
        return "添加redis数据成功！";
    }


    @GetMapping("getTest/{id}")
    @ResponseBody
    public ModelAndView testNet() {
        ModelAndView mv = new ModelAndView("data/data");
        mv.addObject("key", redisUtil.get("data"));
        return mv;
    }









}
