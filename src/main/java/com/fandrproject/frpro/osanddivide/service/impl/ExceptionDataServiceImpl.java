package com.fandrproject.frpro.osanddivide.service.impl;

import com.alibaba.fastjson.JSON;
import com.fandrproject.frpro.osanddivide.bean.ExceptionDataBean;
import com.fandrproject.frpro.osanddivide.dao.ExceptionDataDao;
import com.fandrproject.frpro.osanddivide.service.ExceptionDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异常数据统计impl
 * Created by sml
 * 2020/11/21 23:53
 */
@Service
public class ExceptionDataServiceImpl implements ExceptionDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionDataServiceImpl.class);

    @Autowired
    private ExceptionDataDao exceptionDataDao;

    @Override
    public boolean saveExceptionData(List<ExceptionDataBean> list) {

        for (int i = 0; i < list.size(); i++) {
            ExceptionDataBean bean = list.get(i);
            exceptionDataDao.saveException(bean);
        }
        return true;
    }

    @Override
    public String queryByLimitData(HttpServletRequest request) {
        //定义一个查询结果
        Map<String, Object> resultMap = new HashMap<>();

        String rows = request.getParameter("rows");
        String page = request.getParameter("page");
        rows = StringUtils.isEmpty(rows) ? "10" : rows;
        page = StringUtils.isEmpty(page) ? "1" : page;

        //定义查询条件来查询所有数据
        Map<String, Object> params = new HashMap<>();
        String tableName = request.getParameter("tableName");
        String exceptionAllgross = request.getParameter("exceptionAllgross");
        params.put("tableName", tableName);
        params.put("exceptionAllgross", exceptionAllgross);

        int pageSize = Integer.parseInt(rows);
        int pageNum = Integer.parseInt(page);
        int code = 0;
        long totalNum = 0;

        //利用查询条件来查询出所有数据
        List<ExceptionDataBean> resultList = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            //进行查询
            resultList = exceptionDataDao.queryLimitData(params);
            //计算总数
            PageInfo<ExceptionDataBean> exceBean = new PageInfo<>(resultList);
            totalNum = exceBean.getTotal();
        } catch (Exception e) {
            LOGGER.error("异常数据查询异常: ",e);
            code = 1;
        }

        //将结果写入 结果集map
        resultMap.put("total", totalNum);
        resultMap.put("rows", resultList);
        resultMap.put("code", code);
        return JSON.toJSONString(resultMap);
    }
}
