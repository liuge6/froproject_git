package com.fandrproject.frpro.data.service.impl;

import com.fandrproject.frpro.data.bean.UserInfoBean;
import com.fandrproject.frpro.data.dao.UserInfoDao;
import com.fandrproject.frpro.data.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sml
 * 2020/11/08 11:04
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserInfoBean addUserInfo(UserInfoBean userInfoBean) {
        userInfoDao.addUser(userInfoBean);
        return userInfoBean;
    }
}
