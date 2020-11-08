package com.fandrproject.frpro.data.dao;

import com.fandrproject.frpro.data.bean.ExceptionDataStatisticsBean;

import java.util.List;
import java.util.Map;

/**
 * Created by sml
 * 数据异常统计dao层接口
 * 2020/11/08 20:03
 */

public interface ExceptionDataStatisticsDao {

    /**
     * 柱状图  查询出所有的数据
     * @param params
     * @return
     */
    List<ExceptionDataStatisticsBean> queryByBarData(Map<String, Object> params);
}
