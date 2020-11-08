package com.fandrproject.frpro.data.controller;

import com.alibaba.fastjson.JSONArray;
import com.fandrproject.frpro.data.bean.UserInfoBean;
import com.fandrproject.frpro.data.common.UnifyResult;
import com.fandrproject.frpro.data.service.UserInfoService;
import com.fandrproject.frpro.data.utils.ResponseUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户名控制层
 * Created by sml
 * 2020/08/09 09:24
 */
@RestController
@RequestMapping(value = "/user-curd")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 两种方式跳转页面的方法
     * 1.就是如下 利用ModelAndView 可以将后台的值与前台的值进行一个绑定；
     * @param userInfoBean
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView  addUser(UserInfoBean userInfoBean) {

        ModelAndView view = new ModelAndView("data/data2");
        return view;
    }

    /**
     * 异常数据跳转 跳转页面
     * @return
     */
    @RequestMapping(value = "/toDataException", method = RequestMethod.POST)
    public ModelAndView  toDataException() {

        ModelAndView view = new ModelAndView("exceptiondatacount/newAllException");
        return view;
    }


    /**
     * 验证mysql是否能进入数据库
     * @param userInfoBean
     */

    @RequestMapping(value = "/saveData")
    public void  saveData(UserInfoBean userInfoBean) {
        userInfoBean.setNickname("zhansan");
        userInfoBean.setAddress("xxxx");
        userInfoBean.setGender("男");
        userInfoService.addUserInfo(userInfoBean);
    }



    /**
     * 2.就是利用 String这种知直接跳页面的方式
     * @return
     */
    @RequestMapping("/test")
    public String updateUserInfo() {
        return "data/test1";
    }

    /**
     * 利用echarts 做折线图demo
     * 2020/10/31
     * @return
     */
    @RequestMapping("test-echarts")
    public String echarts(HttpServletResponse response) {

        return "echarts/echarts_datas";

    }

    /**
     * 创建代码块用于组装tableName
     */
    public static List<String> tableList = new ArrayList<>();
    static {
        tableList.add("nbdivide_exception_cloudcard_handle");
        tableList.add("nbdivide_exception_co_handle");
        tableList.add("nbdivide_exception_divide");
    }

    /**
     * 动态获取echarts  并组装里面的数据
     * 需求是：获取从今天起往前数7天  三张异常表中的数据
     * 组装 tableName ， tableDate 好组装
     * 难点是如何动态组装  data: [150, 232, 201, 154] 里面的数据
     * 2020/11/1
     * @param response
     * @return
     */
    @RequestMapping("/getEchartsDatas")
    public void getEchartsDatas(HttpServletResponse response) {

        //定义一个JSonObject 用于组装动态数据
        JSONObject resultobj = new JSONObject();

        //1.放入tableName
        resultobj.put("tableName", tableList);

        //2.放入tableDate  获取7天的日期
        ArrayList<String> sevenDays = getDays(7);
        resultobj.put("tableDate", sevenDays);

        //3.放入tableDataList
        JSONArray tableDataList = new JSONArray();
        for (Object table : tableList) {
            JSONObject tableDateObj = new JSONObject();
            tableDateObj.put("name", table);
            tableDateObj.put("type", "line");
            tableDateObj.put("stack", "总量");

            List<Integer> dataNumList = new ArrayList<>();
            for (int i = 0; i < sevenDays.size(); i++) {
                Random random = new Random();
                int dayNum = Math.abs(random.nextInt(100));
                dataNumList.add(dayNum);
            }
            tableDateObj.put("data", dataNumList);
            tableDataList.add(tableDateObj);
        }
        resultobj.put("tableDataList", tableDataList);
        ResponseUtil.out(response, UnifyResult.success().data("result", resultobj));
        return ;
    }


    /**
     * 获取过去7天内的日期数组
     * @param intervals
     * @return
     */
    public static ArrayList<String> getDays(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals - 1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }

    private static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }


    public static void main(String[] args) {
        JSONArray contentList = new JSONArray();
        contentList.add("[\"HDC-51\"]");
        contentList.add("[\"HDC-51\", \"HDC-55\"]");
        contentList.add("[\"HDC-50\", \"HDC-55\", \"HDC-55-2\"]");
        contentList.add("[\"HDC-51\", \"HDC-55\", \"HDC-55-2\",\"HDC-21N\"]");
        System.out.println(contentList);

        String str = "HDC-50";
        for (Object content : contentList) {
            JSONArray jsonArray = JSONArray.parseArray((String) content);
            if (!jsonArray.contains(str)) {
                jsonArray.add(str);
            }
            System.out.println("contentArray后 : " + jsonArray);
        }
    }
}
