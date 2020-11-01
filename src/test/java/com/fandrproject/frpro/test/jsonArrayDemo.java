package com.fandrproject.frpro.test;

/**
 * JSONArray 实例demo 参考：下面这个博客
 * https://blog.csdn.net/xinyuezitang/article/details/89213783
 * Created by sml
 * 2020/11/01 19:36
 */


/**
 * 需求：
 * 四个字符串:
 * 	 "[\"HDC-51\"]",
 * 	"[\"HDC-51\", \"HDC-55\"]",
 * 	"[\"HDC-50\", \"HDC-55\", \"HDC-55-2\"]",
 * 	"[\"HDC-51\", \"HDC-55\", \"HDC-55-2\",\"HDC-21N\"]",
 * 分别向四个字符串中添加String macType ="HDC-50" ,
 * 并判断字符串中各个元素是否与macType相同, 相同则不添加, 不相同则添加.
 * 最后输出四个字符串,要求格式同开始字符串格式一致.
 */

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 不使用 jsonArray 用数组实现
 */
public class jsonArrayDemo {
//    public static void main(String[] args) {
//        List<String> contentList = new ArrayList<>();
//        contentList.add("[\"HDC-51\"]");
//        contentList.add("[\"HDC-51\", \"HDC-55\"]");
//        contentList.add("[\"HDC-50\", \"HDC-55\", \"HDC-55-2\"]");
//        contentList.add("[\"HDC-51\", \"HDC-55\", \"HDC-55-2\",\"HDC-21N\"]");
//        String macType ="HDC-50";
//        System.out.println(contentList);
//
//        //循环将这5个字符串 中的 [] 替换掉
//        for (String content : contentList) {
//            String replaceContent = content.replaceAll("[\\[\\]]", "");
//
//            //替换完后 按 , 分割
//            String[] split = replaceContent.split(",");
//            //将数组转换为 list
//            List<String> contentSplitList = Arrays.asList(split);
//
//            //定义一个list 用于接收操作完之后的数据
//            List<String> list = new ArrayList<>();
//            //将切割完的 数组 转换为list 这些数据循环
//            for (String contentNew : contentSplitList) {
//                list.add(contentNew);
//            }
//
//            //判断content 中是否含有这个 macType
//            boolean flag = false;
//            for (String string : list) {
//                //去掉字符串中的引号
//                String replaceAll = string.replaceAll("\"", "");
//                if (macType.equals(replaceAll)) {
//                    flag = true;
//                    break;
//                }
//            }
//
//            //如果没有macType,则添加
//            if (flag == false) {
//                StringBuilder sb = new StringBuilder();
//                String macTypeStr = sb.append("\"").append(macType).append("\"").toString();
//                list.add(macTypeStr);
//            }
//
//            //将结果打印且直观看出
//            String newContent = list.toString();
//            System.out.println(newContent);
//        }
//    }

    /**
     * 使用jsonArray 实现
     * @param args
     */
    public static void main(String[] args) {
        List<String> contentList = new ArrayList<>();
        contentList.add("[\"HDC-51\"]");
        contentList.add("[\"HDC-51\", \"HDC-55\"]");
        contentList.add("[\"HDC-50\", \"HDC-55\", \"HDC-55-2\"]");
        contentList.add("[\"HDC-51\", \"HDC-55\", \"HDC-55-2\",\"HDC-21N\"]");
        String macType ="HDC-50";
        System.out.println(contentList);

        try {
            for (String string : contentList) {
                //将如上的字符串转换为  json数组格式的字符串
                //此api 的作用就是代替如上 用数据实现的  原理过程，逻辑清晰
                JSONArray jsonArray = JSONArray.parseArray(string);
                if (!jsonArray.contains(macType)) {
                    jsonArray.add(macType);
                }
                System.out.println("contentArray后 : " + jsonArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
