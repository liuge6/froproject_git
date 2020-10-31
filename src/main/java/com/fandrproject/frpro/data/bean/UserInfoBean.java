package com.fandrproject.frpro.data.bean;

/**
 * userInfo实体类
 * Created by sml
 * 2020/08/09 09:08
 */
public class UserInfoBean {

    private Integer id;

    private String username;

    private String password;

    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String nickname;

    private Integer age;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public UserInfoBean(Integer id, String username, String password, String gender, String nickname, Integer age, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.nickname = nickname;
        this.age = age;
        this.address = address;
    }




}
