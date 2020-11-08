package com.fandrproject.frpro.data.dao.impl;

import com.fandrproject.frpro.data.bean.UserInfoBean;
import com.fandrproject.frpro.data.dao.UserInfoDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by sml
 * 2020/11/08 09:32
 */
@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private static String name_space = "com.fandrproject.frpro.data.dao.UserInfoDao.";
    @Override
    public int addUser(UserInfoBean userInfoBean) {
        return sqlSessionTemplate.insert(name_space + "addUser" , userInfoBean);
    }

}
