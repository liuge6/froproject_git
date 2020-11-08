package com.fandrproject.frpro.data.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sml
 * 异常数据统计接口
 * 2020/11/08 19:58
 */
public interface ExceptionDataStatisticsService {

    String queryByBarData(HttpServletRequest request);
}
