package com.fandrproject.frpro.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sml
 * 2020/11/01 20:09
 */
public class InternetDemo {
    public static void main(String[] args) {
        List<String> contentList = new ArrayList<>();
        contentList.add("[\"HDC-51\"]");
        contentList.add("[\"HDC-51\", \"HDC-55\"]");
        contentList.add("[\"HDC-50\", \"HDC-55\", \"HDC-55-2\"]");
        contentList.add("[\"HDC-51\", \"HDC-55\", \"HDC-55-2\",\"HDC-21N\"]");
        System.out.println(contentList);
        String macType ="HDC-50";

        for (String content : contentList) {

            //去掉content 中的中括号
            String contentStr1 = content.replaceAll("[\\[\\]]", "");
            List<String> content1= Arrays.asList(contentStr1.split(","));

            List<String> list = new ArrayList<>();

            for (String string : content1) {
                list.add(string);
            }

            //判断content中是否已经包含macType
            boolean flag = false;
            for (String string : list) {
                //去掉字符串的引号
                String str= string.replace("\"", "");
                if (macType.equals(str)) {
                    flag = true;
                    break;
                }
            }

            //如果没有macType,则添加
            if (flag == false) {
                StringBuilder sb = new StringBuilder();
                String macTypeStr = sb.append("\"").append(macType).append("\"").toString();
                list.add(macTypeStr);
            }

            String newContent = list.toString();
            System.out.println(newContent);

        }
    }
}
