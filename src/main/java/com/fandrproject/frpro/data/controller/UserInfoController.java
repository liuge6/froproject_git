package com.fandrproject.frpro.data.controller;

import com.fandrproject.frpro.data.bean.UserInfoBean;
import com.fandrproject.frpro.data.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户名控制层
 * Created by sml
 * 2020/08/09 09:24
 */
@Controller
@RequestMapping(value = "user-curd", method = { RequestMethod.GET, RequestMethod.POST })
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView  addUser(UserInfoBean userInfoBean) {

        ModelAndView view = new ModelAndView("data/data2");
        return view;
    }

    @RequestMapping("/test")
    public String updateUserInfo() {
        return "data/test1.ftl";
    }
}
