package com.fandrproject.frpro.data.service;

import com.fandrproject.frpro.data.bean.UserInfoBean;

/**
 * 用户名实现接口
 * Created by sml
 * 2020/08/09 09:22
 */
public interface UserInfoService {

    /**
     * 用户新增接口
     * @return
     */
    UserInfoBean addUserInfo(UserInfoBean userInfoBean);
}
