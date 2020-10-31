package com.fandrproject.frpro.data.controller;

import com.fandrproject.frpro.data.bean.UserInfoBean;
import com.fandrproject.frpro.data.util.HzExcelExportUtil;
import com.fandrproject.frpro.data.util.HzExportExcelDataFormater;
import com.fandrproject.frpro.data.util.HzFileUtil;
import com.fandrproject.frpro.data.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by sml
 * 2020/07/26 20:31
 */
@Controller
@RequestMapping("book-info")
public class BookInfoController {

    @Autowired
    private RedisUtil redisUtil;



    @RequestMapping("book-info-web")
    @ResponseBody
    public ModelAndView bookInfoWeb() {
//        StringBuilder aa = new StringBuilder();
//        StringBuilder bb = new StringBuilder();
//
//        List<String> testStr = new ArrayList<>();
//
//
//        List<String> getStr = new ArrayList<>();
//
//        Map<String, Object> mp = new HashMap<>();
//
//
//        Random r = new Random();
//        for(int i = 0; i < 1000000;i++) {
//            String s = String.valueOf(r.nextInt(1000000));
//            testStr.add(s);
//            mp.put(s, "");
//        }
//
//        for (int i = 10000; i  < 1000;i++) {
//            String s1 = String.valueOf(r.nextInt(123213213));
//            getStr.add(s1);
//        }
//
//        long ls = System.currentTimeMillis();
//        for(int i = 0; i < testStr.size();i++) {
//            for(int j = 0; j < getStr.size();i++) {
//                if(testStr.get(i).equals(getStr.get(i))) {
//                    aa.append(testStr.get(i));
//                }
//            }
//        }
//        long le = System.currentTimeMillis();
//
//        System.out.println("======>forend"+ aa + (le-ls));
//
//queryInfo()
//
//        long ms = System.currentTimeMillis();
//        for (int i = 10000; i  < 1000;i++) {
//            String s1 = String.valueOf(r.nextInt(123213213));
//            if(mp.containsKey(s1)) {
//                bb.append(s1);
//            }
//        }
//        long me = System.currentTimeMillis();
//        System.out.println("======>mapend"+ bb + (me - ms));
//
//        System.out.println(aa);
        ModelAndView mv = new ModelAndView("data/demo");
        return mv;
    }


    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "book-info-login", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView bookInfoTest() {
        ModelAndView mv = new ModelAndView("data/login");

        return mv;
    }

    @RequestMapping("book-info-init")
    @ResponseBody
    public void initBookInfo() {
        List<Object> bookList = new ArrayList<>();
        Map<String, Object> book = new HashMap<>();
        Random r = new Random();
        book.put("id", r.nextInt(999999));
        book.put("title", "maoxuan");
        book.put("price", "100");
        book.put("author", "xxx");
        bookList.add(book);

        Map<String, Object> book2 = new HashMap<>();
        book2.put("id", r.nextInt(999999));
        book2.put("title", "java");
        book2.put("price", "80");
        book2.put("author", "aaa");
        bookList.add(book2);

        Map<String, Object> book3 = new HashMap<>();
        book3.put("id", r.nextInt(999999));
        book3.put("title", "python");
        book3.put("price", "22");
        book3.put("author", "ccc");
        bookList.add(book3);
        redisUtil.lSet("bookLIst", bookList);
    }

    @RequestMapping(value = "book-info-list", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView bookInfoList() {
        ModelAndView mv = new ModelAndView("data/bookinfolist");
        List<Object> bookLIst = redisUtil.lGet("bookLIst", 1, 10);
        mv.addObject("bookLIst", bookLIst);
        return mv;
    }



    @RequestMapping(value = "delete-by-id", method = RequestMethod.POST)
    @ResponseBody
    public String deleteById(@RequestBody String bookId) {
        String[] split = bookId.split("=");
        //取出所有数据
        long bookLIst1 = redisUtil.lGetListSize("bookLIst");
        List<Object> bookLIst = redisUtil.lGet("bookLIst", 0, bookLIst1);
        //循环list
        for (int i = 0; i < bookLIst.size(); i++) {
            //取出下标对应的实体
            Map<String, Object> map = (Map<String, Object>) bookLIst.get(i);
            //判断如果下标取出的实体的id 等于 前台传的id，则把这个实体从list中杀出
            if (String.valueOf(map.get("id")).equals(split[1])) {
                bookLIst.remove(i);
                break;
            }
        }
        //将处理后的list，重新塞入 “bookLIst” 这个key中
        redisUtil.del("bookLIst");
        redisUtil.lSet("bookLIst", bookLIst);
        return "删除成功";
    }

    //查出所有数据详情
    //填充到界面中
    //根据id修改内容
    @RequestMapping(value = "queryInfo-by-id", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryInfoById(String bookId) {
//        String[] split = bookId.split("=");
        //查询所有
        long bookLIst1 = redisUtil.lGetListSize("bookLIst");
        List<Object> bookLIst = redisUtil.lGet("bookLIst", 0, bookLIst1);
        Map<String, Object> map = new HashMap<>();
        for(int i = 0;i<bookLIst.size();i++) {
            map = (Map<String, Object>) bookLIst.get(i);
            if(String.valueOf(map.get("id")).equals(bookId)) {
                return map;
            }
        }
        return null;
    }


    @RequestMapping(value = "save-id", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveId(String bookId, String author, String price, String title) {
        long size = redisUtil.lGetListSize("bookLIst");
        List<Object> bookLIst = redisUtil.lGet("bookLIst", 0, size);
        Map<String, Object> map = new HashMap<>();
        for(int i = 0;i<bookLIst.size();i++) {
            map = (Map<String, Object>) bookLIst.get(i);
            if(String.valueOf(map.get("id")).equals(bookId)) {
                map.put("author", author);
                map.put("price", price);
                map.put("title", title);
                bookLIst.remove(i);
                bookLIst.set(0, map);
                break;
            }
        }
        redisUtil.del("bookLIst");
        redisUtil.lSet("bookLIst", bookLIst);
        return null;
    }

    /**
     * sui导出excel20200920兄嘚浩的自写的那种方案
     * 页面在demo.html
     * @param request
     * @param response
     */
    @GetMapping("testExport")
    @ResponseBody
    public void testExport(HttpServletRequest request, HttpServletResponse response) {
        //创建一个文件夹
        String path = "F:\\excel";
        File excelFile = new File(path);
        OutputStream outputStream;
        BufferedOutputStream out = null;
        FileInputStream file = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        InputStream inputStream = null;
        //创建一个excel文档
        String fileName = "text.xlsx";

        try {

            if (!excelFile.exists()) {
                excelFile.mkdirs();
            }
            excelFile = new File(path + "\\" + fileName);
            outputStream = new FileOutputStream(excelFile);
            //创建excel显示的中文名称
            String[] titles = {"标识 ", "用户名", "密码", "性别","昵称","年龄","地址"};
            //创建execl显示的实体名称
            String[] field = {"id", "username", "password", "gender", "nickname", "age", "address"};
            HzExcelExportUtil excel = new HzExcelExportUtil(outputStream, true);

            HzExportExcelDataFormater formater = new HzExportExcelDataFormater() {
                @Override
                public Object format(Field filed, Object value, Object rowValue) {
                    if ("gender".equals(filed.getName())) {
                        if (value.equals("1")) {
                            return "男";
                        } else if (value.equals("2")) {
                            return "女";
                        } else if (value == "") {
                            return "无性别";
                        }
                    }
                    return value;
                }
            };

            List<UserInfoBean> dataList = new ArrayList<>();
            UserInfoBean user1 = new UserInfoBean(1001, "zhangsan"
                    , "123123", "1", "asd", 18, "nanjing");

            dataList.add(user1);

                excel.creatSheet("第一个sheet  页")
                        .addTitle(titles)
                        .setField(field, UserInfoBean.class)
                        .addData(dataList, formater)
                        .bulid();

            response.setHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
            response.setContentType(HzFileUtil.setContentType(fileName));
            out = new BufferedOutputStream(response.getOutputStream());
            int index;
            fis = new FileInputStream(excelFile);
            bis = new BufferedInputStream(fis);

            if (bis.available() > 0) {
                byte[] bufs = new byte[10240];
                boolean var55 = false;
                while ((index = bis.read(bufs, 0, 10240)) != -1) {
                    out.write(bufs, 0, index);
                }

            } else {
                System.out.println("文件下载失败，失败原因【空文件】");
            }
            return;
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }   catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
