package com.fandrproject.frpro.osanddivide.service.impl;

import com.alibaba.fastjson.JSON;
import com.fandrproject.frpro.osanddivide.bean.DividePayTypeConfigBean;
import com.fandrproject.frpro.osanddivide.dao.DividePayTypeSMLDao;
import com.fandrproject.frpro.osanddivide.service.DividePayTypeSMLService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分成支付方式serviceImpl
 * Created by sml
 * 2020/12/12 23:41
 */
@Service
public class DividePayTypeSMLImpl implements DividePayTypeSMLService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DividePayTypeSMLImpl.class);

    @Autowired
    DividePayTypeSMLDao dividePayTypeSMLDao;

    @Override
    public boolean savePayTypeConfigInfo(DividePayTypeConfigBean bean) {
        return dividePayTypeSMLDao.savePayTypeConfigInfo(bean);
    }

    @Override
    public int checkRepeat(DividePayTypeConfigBean bean) {
        return dividePayTypeSMLDao.checkRepeat(bean);
    }

    @Override
    public String queryPayTypeLimit(HttpServletRequest request) {

        //定义查询结果
        Map<String, Object> resultMap = new HashMap<>();
    
        String rows = request.getParameter("rows");
        String page = request.getParameter("page");
        rows = StringUtils.isEmpty(rows) ? "10" : rows;
        page = StringUtils.isEmpty(page) ? "1" : page;
        resultMap.put("rows", rows);
        resultMap.put("page", page);

        //定义查询条件
        Map<String, String> paramMap = new HashMap<>();
        String payCode = request.getParameter("payCode");
        String bankTypeCode = request.getParameter("bankTypeCode");
        paramMap.put("payCode", payCode);
        paramMap.put("bankTypeCode", bankTypeCode);

        int pageize = Integer.parseInt(rows);
        int pageNum = Integer.parseInt(page);
        int code = 0;
        long totalNum = 0;

        //查询查询数据
        List<DividePayTypeConfigBean> limitDataList = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageize);
            limitDataList =  dividePayTypeSMLDao.queryLimitDatas(paramMap);
            if (!CollectionUtils.isEmpty(limitDataList)){
                PageInfo<DividePayTypeConfigBean> pageList = new PageInfo<>(limitDataList);
                totalNum = pageList.getTotal();
            }
        } catch (Exception e) {
            LOGGER.error("分成支付方式分页查询异常" + e);
            code = -1;
        }

        //将结果写入 结果集map
        resultMap.put("total", totalNum);
        resultMap.put("rows", limitDataList);
        resultMap.put("code", code);

        return JSON.toJSONString(resultMap);
    }

    @Override
    public DividePayTypeConfigBean queryPayConfigById(String id) {
        return dividePayTypeSMLDao.queryPayConfigById(id);
    }
}
