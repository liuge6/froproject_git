package com.fandrproject.frpro.osanddivide.service;

import com.fandrproject.frpro.osanddivide.bean.ExceptionDataBean;

import java.util.List;

/**
 * 异常数据统计service
 * Created by sml
 * 2020/11/21 23:53
 */
public interface ExceptionDataService {
    /**
     * 新增保存数据
     * @param list
     * @return
     */
    boolean saveExceptionData(List<ExceptionDataBean> list);
}
