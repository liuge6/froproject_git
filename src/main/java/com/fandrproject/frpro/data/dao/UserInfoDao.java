package com.fandrproject.frpro.data.dao;

import com.fandrproject.frpro.data.bean.UserInfoBean;
import org.springframework.stereotype.Component;

/**
 * dao层 用于跟数据库交互接口
 * Created by sml
 * 2020/08/09 09:13
 */
@Component
public interface UserInfoDao {

    /**
     * 用户新增
     * @param userInfoBean
     * @return
     */
    int addUser(UserInfoBean userInfoBean);
}
