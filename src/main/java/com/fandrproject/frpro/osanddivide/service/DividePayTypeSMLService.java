package com.fandrproject.frpro.osanddivide.service;

import com.fandrproject.frpro.osanddivide.bean.DividePayTypeConfigBean;

import javax.servlet.http.HttpServletRequest;

/**
 * 分成支付方式service
 * Created by sml
 * 2020/12/12 23:40
 */
public interface DividePayTypeSMLService {

    /**
     * 新增支付方式保存数据
     * @param bean
     * @return
     */
    boolean savePayTypeConfigInfo(DividePayTypeConfigBean bean);

    /**
     * 排重校验
     * @param bean
     * @return
     */
    int checkRepeat(DividePayTypeConfigBean bean);

    /**
     * 分页查询
     * @param request
     * @return
     */
    String queryPayTypeLimit(HttpServletRequest request);

    /**
     *根据id 查询所有的信息
     * @param id
     * @return
     */
    DividePayTypeConfigBean queryPayConfigById(String id);
}
