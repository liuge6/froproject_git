package com.fandrproject.frpro.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常数据统计 controller
 * Created by sml
 * 2020/11/07 17:34
 */

@Controller
@RequestMapping("/exceptionData")
public class ExceptionDataCountController {

    /**
     *
     * 异常数据饼图展示
     * @return
     */
    @RequestMapping("/toExceptionPie")
    public String toExceptionPie() {
        return "exceptiondatacount/divideExceptionDataAll";
    }
}
