package com.fandrproject.frpro.test_demo_rumentojingtong1104;

/**
 * 演示的是 1+2+3+4+5+6+7+8+9 = ？
 * Created by+ sml
 * 2020/11/04 23:18
 */
public class test2 {
    public static void main(String[] args) {
        int num[] = new int[]{1,2,3,4,5,6,7,8,9,10};
        int sum = 0 ;
        for (int i = 0; i < 10; i++) {

            if (i == 9) {
                System.out.println(num[i] + "=");
            } else {
                System.out.println(num[i] + "+");
            }
            sum = sum + num[i];
        }

        System.out.println(sum);
    }
}
