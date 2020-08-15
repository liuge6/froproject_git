package com.fandrproject.frpro.data.controller;

import com.fandrproject.frpro.data.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        StringBuilder aa = new StringBuilder();
        StringBuilder bb = new StringBuilder();

        List<String> testStr = new ArrayList<>();


        List<String> getStr = new ArrayList<>();

        Map<String, Object> mp = new HashMap<>();


        Random r = new Random();
        for(int i = 0; i < 1000000;i++) {
            String s = String.valueOf(r.nextInt(1000000));
            testStr.add(s);
            mp.put(s, "");
        }

        for (int i = 10000; i  < 1000;i++) {
            String s1 = String.valueOf(r.nextInt(123213213));
            getStr.add(s1);
        }

        long ls = System.currentTimeMillis();
        for(int i = 0; i < testStr.size();i++) {
            for(int j = 0; j < getStr.size();i++) {
                if(testStr.get(i).equals(getStr.get(i))) {
                    aa.append(testStr.get(i));
                }
            }
        }
        long le = System.currentTimeMillis();

        System.out.println("======>forend"+ aa + (le-ls));



        long ms = System.currentTimeMillis();
        for (int i = 10000; i  < 1000;i++) {
            String s1 = String.valueOf(r.nextInt(123213213));
            if(mp.containsKey(s1)) {
                bb.append(s1);
            }
        }
        long me = System.currentTimeMillis();
        System.out.println("======>mapend"+ bb + (me - ms));

        System.out.println(aa);
        ModelAndView mv = new ModelAndView("data/demo");
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

}
