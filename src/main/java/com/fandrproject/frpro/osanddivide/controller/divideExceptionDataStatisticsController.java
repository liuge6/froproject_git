package com.fandrproject.frpro.osanddivide.controller;

import com.fandrproject.frpro.data.common.UnifyResult;
import com.fandrproject.frpro.data.utils.ResponseUtil;
import com.fandrproject.frpro.osanddivide.bean.ExceptionDataBean;
import com.fandrproject.frpro.osanddivide.service.ExceptionDataService;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * osanddivide项目
 * 分成异常统计
 * Created by sml
 * 2020/11/14 21:20
 */
@RequestMapping("/osanddivide")
@Controller
public class divideExceptionDataStatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(divideExceptionDataStatisticsController.class);

    @Autowired
    private ExceptionDataService exceptionDataService;

    /**
     * 跳转 分成异常统计页面
     * @return
     */
    @RequestMapping("/toDivideExeptionDataStatistic")
    public String toDivideExeptionDataStatistic() {
        return "osanddivide/divideExceptionDataStatistics";
    }

    /**
     * 新增保存数据
     * @param response
     * @param tableListStr
     * @param valueListStr
     */
    @RequestMapping("/addExceptionData")
    @ResponseBody
    public void addExceptionData(HttpServletResponse response, String tableListStr, String valueListStr ) {

        //参数校验
        if (StringUtils.isEmpty(tableListStr) || StringUtils.isEmpty(valueListStr)) {
            ResponseUtil.out(response, UnifyResult.fail().message("新增参数异常！"));
            return;
        }

        try {
            //将前台传过来的参数进行处理
            String[] tableArr = tableListStr.split("-");
            String[] valueArr = valueListStr.split("&");
            List<ExceptionDataBean> list = new ArrayList<>();
            for (int i = 0; i < tableArr.length; i++) {
                ExceptionDataBean bean = new ExceptionDataBean();
                //塞值
                bean.setTableName(tableArr[i]);
                bean.setExceptionAllgross(valueArr[i]);
                //入list
                if (Integer.parseInt(valueArr[i]) == -1) {
                    continue;
                }
                list.add(bean);
            }

            //进行入库
            boolean result = exceptionDataService.saveExceptionData(list);
            if (result) {
                ResponseUtil.out(response, UnifyResult.success().message("新增异常数据成功！"));
                return;
            } else {
                ResponseUtil.out(response, UnifyResult.fail().message("新增异常数据失败！"));
                return;
            }
        } catch (Exception e) {
            LOGGER.error("维护数据失败，请检查！");
            ResponseUtil.out(response, UnifyResult.fail().message("新增异常数据失败！"));
            return;
        }
    }

}

