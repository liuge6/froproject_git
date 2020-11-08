package com.fandrproject.frpro.data.service.impl;

import com.fandrproject.frpro.data.bean.ExceptionDataStatisticsBean;
import com.fandrproject.frpro.data.dao.ExceptionDataStatisticsDao;
import com.fandrproject.frpro.data.service.ExceptionDataStatisticsService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sml
 * 异常数据统计实现类
 * 2020/11/08 20:02
 */
@Service
public class ExceptionDataStatisticsServiceImpl implements ExceptionDataStatisticsService {

    @Autowired
    private ExceptionDataStatisticsDao exceptionDataStatisticsDao;

    @Override
    public String queryByBarData(HttpServletRequest request) {
        //定义表名集合 x轴
        List<String> tableNameList = new ArrayList<>();

        //定义数据几何体 y轴
        List<String> tableDataValue = new ArrayList<>();
        JSONObject dataJson = new JSONObject();

        //数据在哪里来
        List<ExceptionDataStatisticsBean> dataList = new ArrayList<>();
        dataList = exceptionDataStatisticsDao.queryByBarData(null);

        //循环获取的数据
        for (ExceptionDataStatisticsBean bean : dataList) {
            String tableName = bean.getTableName();
            String exceptionAllgross = bean.getExceptionAllgross();
            tableNameList.add(tableName);
            tableDataValue.add(exceptionAllgross);
        }

        //组装数据填充到 大的jsonObject 中
        dataJson.put("tableName", tableNameList);
        dataJson.put("tableDataValue", tableDataValue);

        return dataJson.toJSONString();
    }
}
