package com.fandrproject.frpro.test;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sml
 * 2020/08/15 12:26
 */
@SpringBootTest
public class test_1 {



    @Test
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "liuge111");
        map.put("2", "liuge222");
        map.put("3", "liuge333");
        map.put("4", "liuge444");
        map.put("5", "liuge555");
        Set<String> strings = map.keySet();

        for (String str : strings) {
            System.out.println("str=================>" + str);
        }

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> stringStringEntry : entries) {
            final String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            System.out.println("key=====>" + key + "value=======>" + value);
        }


//        Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            System.out.println("strings=================>" + strings);
//        }
    }
}
