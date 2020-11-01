package com.fandrproject.frpro.data.common;

import java.util.HashMap;
import java.util.Map;

public class UnifyResult {


    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Map<String, Object> data = new HashMap<>();


    /**

     * 私有化无参构造

     */
    private UnifyResult(){}

    /**

     * 成功的静态方法

     */
    public static UnifyResult success() {
        UnifyResult ur = new UnifyResult();
        ur.setSuccess(true);
        ur.setCode(ResultCode.SUCCESS);
        ur.setMessage("成功");
        return ur;
    }

    /**
     * 失败的静态方法
     */
    public static UnifyResult fail() {
        UnifyResult ur = new UnifyResult();
        ur.setSuccess(false);
        ur.setCode(ResultCode.FAIL);
        ur.setMessage("失败");
        return ur;
    }

    /**
     * 实现一个链式编程
     */
    public UnifyResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public UnifyResult message(String message){
        this.setMessage(message);
        return this;
    }

    public UnifyResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public UnifyResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public UnifyResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
