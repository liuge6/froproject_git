package com.fandrproject.frpro.osanddivide.dao;

import com.fandrproject.frpro.osanddivide.bean.DividePayTypeConfigBean;

import java.util.List;
import java.util.Map;

/**
 * 分成支付方式 Dao
 * Created by sml
 * 2020/12/12 23:42
 */
public interface DividePayTypeSMLDao {

    /**
     * 新增支付方式
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
     * 分页查询数据
     * @param paramMap
     * @return
     */
    List<DividePayTypeConfigBean> queryLimitDatas(Map<String, String> paramMap);
}
