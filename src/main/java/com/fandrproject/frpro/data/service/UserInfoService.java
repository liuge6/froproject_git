package com.fandrproject.frpro.data.service;

import com.fandrproject.frpro.data.bean.UserInfoBean;
import org.springframework.stereotype.Service;

/**
 * 用户名实现接口
 * Created by sml
 * 2020/08/09 09:22
 */
@Service
public interface UserInfoService {



    /**
     * 用户新增接口
     * @return
     */
    public UserInfoBean addUserInfo(UserInfoBean userInfoBean);
}
