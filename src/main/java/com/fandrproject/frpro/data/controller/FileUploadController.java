package com.fandrproject.frpro.data.controller;

import com.fandrproject.frpro.data.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sml
 * 2020/08/01 17:56
 */
@Controller
@RequestMapping("uploadFile")
public class FileUploadController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("ploadfile-infou")
    @ResponseBody
    public ModelAndView uploadFile() {
        ModelAndView mv = new ModelAndView("data/upload");
        return mv;
    }






}
