package com.fandrproject.frpro.osanddivide.dao;

import com.fandrproject.frpro.osanddivide.bean.ExceptionDataBean;

/**
 * 异常数据统计dao
 * 因为用了@MapperScan 此注解所以不用写 impl 直接到 xml
 * Created by sml
 * 2020/11/21 23:54
 */
public interface ExceptionDataDao {
    /**
     * 新增保存数据
     * @param bean
     */
    void saveException(ExceptionDataBean bean);
}
