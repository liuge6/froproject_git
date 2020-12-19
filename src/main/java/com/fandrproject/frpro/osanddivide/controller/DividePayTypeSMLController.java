package com.fandrproject.frpro.osanddivide.controller;

import com.fandrproject.frpro.data.common.UnifyResult;
import com.fandrproject.frpro.data.utils.DateUtil;
import com.fandrproject.frpro.data.utils.ResponseUtil;
import com.fandrproject.frpro.osanddivide.bean.DividePayTypeConfigBean;
import com.fandrproject.frpro.osanddivide.service.DividePayTypeSMLService;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分成支付方式 controller
 * 自己写的一套增删改查
 * Created by sml
 * 2020/12/12 14:32
 */
@RequestMapping("/osanddivide")
@Controller
public class DividePayTypeSMLController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DividePayTypeSMLController.class);

    @Autowired
    DividePayTypeSMLService dividePayTypeSMLService;

    /**
     * 跳转主页面
     * @return
     */
    @RequestMapping("/toPayCodeConfig")
    public String toPayCodeConfig() {
        return "osanddivide/dividepaytypeconfig/dividePayTypeConfig";
    }

    /**
     * 新增 分成支付方式
     * @param bean
     * @param response
     */
    @RequestMapping("/savePayTypeConfig")
    public void savePayTypeConfig(DividePayTypeConfigBean bean , HttpServletResponse response) {

        try {
            if (StringUtils.isEmpty(bean.getPayCode())) {
                ResponseUtil.out(response, UnifyResult.fail().message("支付方式不能为空！"));
                return;
            }

            //排重校验   根据payCode 和 bankTypeCode
            int repeatNum = dividePayTypeSMLService.checkRepeat(bean);
            if (repeatNum > 0) {
                ResponseUtil.out(response, UnifyResult.fail().message("支付方式和银行类型存在重复！"));
                return;
            }
            bean.setCreateTime(DateUtil.getCurrentDate());
            bean.setUpdateTime(DateUtil.getCurrentDate());

            //入库
            boolean flag = dividePayTypeSMLService.savePayTypeConfigInfo(bean);
            if (flag) {
                ResponseUtil.out(response, UnifyResult.success().message("新增支付方式成功！"));
                return;
            } else {
                ResponseUtil.out(response, UnifyResult.fail().message("新增支付方式失败！"));
                return;
            }
        } catch (Exception e) {
            LOGGER.error("新增分成支付方式异常！" + e);
            ResponseUtil.out(response, UnifyResult.fail().message("新增分成支付方式异常！"));
            return;
        }
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @RequestMapping("/queryPayTypeLimit")
    @ResponseBody
    public String queryPayTypeLimit(HttpServletRequest request) {

        return dividePayTypeSMLService.queryPayTypeLimit(request);
    }
}
