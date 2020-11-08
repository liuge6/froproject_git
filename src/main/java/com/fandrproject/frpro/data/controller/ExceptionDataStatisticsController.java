package com.fandrproject.frpro.data.controller;

import com.fandrproject.frpro.data.service.ExceptionDataStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * 异常数据统计 controller
 * Created by sml
 * 2020/11/07 17:34
 */

@Controller
@RequestMapping("/exceptionData")
public class ExceptionDataStatisticsController {

    @Autowired
    private ExceptionDataStatisticsService exceptionDataStatisticsService;

    /**
     *
     * 异常数据饼图展示
     * @return
     */
    @RequestMapping("/toExceptionPie")
    public String toExceptionPie() {
        return "exceptiondatacount/divideExceptionDataStatistics";
    }

    /**
     * 展示柱形图
     * @param request
     * @return
     */
    @RequestMapping("/queryBarByData")
    @ResponseBody
    public String queryBarByData(HttpServletRequest request) {
        return exceptionDataStatisticsService.queryByBarData(request);
    }

}
