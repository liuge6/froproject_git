package com.fandrproject.frpro.data.controller;

import com.fandrproject.frpro.data.bean.UserInfoBean;
import com.fandrproject.frpro.data.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户名控制层
 * Created by sml
 * 2020/08/09 09:24
 */
@Controller
@RequestMapping(value = "CRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public UserInfoBean addUser(UserInfoBean userInfoBean) {
        return userInfoService.addUserInfo(userInfoBean);
    }
}
