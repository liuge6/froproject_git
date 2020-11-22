package com.fandrproject.frpro.osanddivide.service;

import com.fandrproject.frpro.osanddivide.bean.ExceptionDataBean;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 分页查询
     * @param request
     * @return
     */
    String queryByLimitData(HttpServletRequest request);
}
