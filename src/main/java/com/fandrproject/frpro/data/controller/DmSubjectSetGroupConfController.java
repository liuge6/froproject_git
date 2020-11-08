package com.fandrproject.frpro.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 科目设置组配置
 * Created by sml
 * 2020/11/01 20:51
 */
@RequestMapping("/dmSubjectSerGroupInfo")
@Controller
public class DmSubjectSetGroupConfController {

    @RequestMapping("/toDmSubjectSerGroupInfo")
    public String dmSubjectSerGroupInfo() {
        return "os_dmSubjectSetGroupConf/dmSubjectSetGroupConfInfo";
    }

    /**
     * 分成异常数据统计
     */
    @RequestMapping("/toDivideExceptionCount")
    public String toDivideExceptionCount() {
        return "exceptiondatacount/divideExceptionDataCount";
    }

    /**
     * 分成异常数据统计-柱状图测试demo
     */
    @RequestMapping("/toDivideExceptionCount1")
    public String toDivideExceptionCount2() {
        return "exceptiondatacount/test";
    }

    /**
     * 跳转异常页面统计饼图 pie
     * @return
     */
    @RequestMapping("toDivideExceptionDatas")
    public String toDivideExceptionDatas() {
        return "exceptiondatacount/divideExceptionDataAll";
    }


    /**
     * 饼图展示
     * @return
     */
    @RequestMapping("/queryPieByData")
    @ResponseBody
    public String queryPieByData() {

        return "exceptiondatacount/divideExceptionDataAll";
    }

    @RequestMapping("/querByPieData")
    public String querByPieData() {
        return "exceptiondatacount/newAllException";
    }

}
