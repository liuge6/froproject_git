package com.fandrproject.frpro.test_demo_rumentojingtong1104;

/**
 * 演示构造方法的 demo
 * Created by sml
 * 2020/11/04 23:04
 */
public class test1 {
    private String bookName;
    private String bookAuthor;
    private double bookPrice;

    public test1(String bookName, String bookAuthor, double bookPrice) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
    }


    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public double getPrice() {
        return bookPrice;
    }

    public static void main(String[] args) {
        test1 test = new test1("英语", "suiming", 6.66);
        test.getBookName();
        test.getBookAuthor();
        test.getPrice();
        System.out.println( test.getBookName() + test.getBookAuthor() + test.getPrice());
    }
}
