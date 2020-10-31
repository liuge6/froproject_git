package com.fandrproject.frpro.data.controller;

import com.fandrproject.frpro.data.bean.UserInfoBean;
import com.fandrproject.frpro.data.util.HzExcelExportUtil;
import com.fandrproject.frpro.data.util.HzExportExcelDataFormater;
import com.fandrproject.frpro.data.util.HzFileUtil;
import com.fandrproject.frpro.data.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sml
 * 2020/07/26 02:20
 */

@Controller
@RequestMapping("model-data")
public class DataController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("data-page")
    @ResponseBody
    public ModelAndView dataPage() {
        ModelAndView mv = new ModelAndView("data/data");
        mv.addObject("key", redisUtil.get("data"));
        return mv;
    }

    @GetMapping("data-page2")
    public String dataPage2() {
        return "data/data2";
    }


    @GetMapping("get-data/{id}")
    @ResponseBody
    public Map<String, Object> getData(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", redisUtil.get("data"));
        return map;
    }


    @GetMapping("set-data/{key}/{value}")
    @ResponseBody
    public String setData(@PathVariable String key, @PathVariable String value) {
        redisUtil.set(key, value);
        return "添加redis数据成功！";
    }


    @GetMapping("getTest/{id}")
    @ResponseBody
    public ModelAndView testNet() {
        ModelAndView mv = new ModelAndView("data/data");
        mv.addObject("key", redisUtil.get("data"));
        return mv;
    }


    /**
     * 兄弟浩批量导出的逻辑20200919
     * 页面在login.html
     * @param response
     * @param request
     */
    @GetMapping("export-data")
    @ResponseBody
    public void testNet(HttpServletResponse response, HttpServletRequest request) {
        String _path = "E:\\excel";
        File excelFile = new File(_path);
        OutputStream outputStream;
        BufferedOutputStream out = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        InputStream inputStream = null;
        String _fileName = "aaaa.xlsx";
        try {
            if (!excelFile.exists()) {
                excelFile.mkdirs();
            }
            excelFile = new File(_path + "\\" + _fileName);
            outputStream = new FileOutputStream(excelFile);
            String[] titles = {"标识 ", "用户名", "密码", "性别","昵称","年龄","地址"};
            String[] field = {"id", "username", "password", "gender","nickname","age","address"};
            HzExcelExportUtil excel = new HzExcelExportUtil(outputStream, true);

            HzExportExcelDataFormater formater = new HzExportExcelDataFormater() {
                @Override
                public Object format(Field filed, Object value, Object rowValue) {
                    if("gender".equals(filed.getName())) {
                        if (value.equals("1")) {
                            return "男";
                        }  else if (value.equals("2")) {
                            return "女";
                        } else if (value == "") {
                            return "无性别";
                        }
                    }

                    if("address".equals(filed.getName())) {
                        if (value.equals("nanjing")) {
                            return "南京";
                        } else {
                            return "不知道啥地方";
                        }
                    }
                    return value;
                }
            };


            List<UserInfoBean> dataList = new ArrayList<>();
            UserInfoBean user1 = new UserInfoBean(1001, "zhangsan"
                , "123123", "1", "asd", 18, "nanjing");
            UserInfoBean user2 = new UserInfoBean(1002, "lisi"
                    , "123123", "1", "asd", 18, "nanjing");
            UserInfoBean user3 = new UserInfoBean(1003, "wangwu"
                    , "123123", "2", "asd", 18, "nanjing");
            UserInfoBean user4 = new UserInfoBean(1004, "wew"
                    , "123123", "2", "asd", 18, "nanjing");
            UserInfoBean user5 = new UserInfoBean(1005, "wewess"
                    , "123123", "1", "asd", 18, "nanjing");
            UserInfoBean user6 = new UserInfoBean(1006, ""
                    , "123123", "", "asd", 18, "asdsadsa");
            dataList.add(user1);
            dataList.add(user2);
            dataList.add(user3);
            dataList.add(user4);
            dataList.add(user5);
            dataList.add(user6);


            excel.creatSheet("第一个sheet")
                .addTitle(titles)
                .setField(field, UserInfoBean.class)
                .addData(dataList, formater)
                .bulid();


            response.setHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Disposition", "attachment; filename*=UTF-8''" + _fileName);
            response.setContentType(HzFileUtil.setContentType(_fileName));
            out = new BufferedOutputStream(response.getOutputStream());
            int _index;
            fis = new FileInputStream(excelFile);
            bis = new BufferedInputStream(fis);

            if (bis.available() > 0) {
                byte[] bufs = new byte[10240];
                boolean var55 = false;

                while ((_index = bis.read(bufs, 0, 10240)) != -1) {
                    out.write(bufs, 0, _index);
                }
            } else {
                System.out.println("文件下载失败，失败原因【空文件】");
            }
            return;
        } catch (Exception e) {
            System.out.println(e.getCause());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException var44) {
            }


            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException var42) {
            }

            try {
                if (null != bis) {
                    bis.close();
                }
            } catch (IOException var41) {
            }

            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException var40) {
            }
        }
    }


}
