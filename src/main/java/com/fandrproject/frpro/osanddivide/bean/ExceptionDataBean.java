package com.fandrproject.frpro.osanddivide.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 异常数据统计 bean
 * Created by sml
 * 2020/11/21 23:28
 */
public class ExceptionDataBean {

    private Integer id;

    private String tableName;

    private String exceptionAllgross;

    private String createDate;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getExceptionAllgross() {
        return exceptionAllgross;
    }

    public void setExceptionAllgross(String exceptionAllgross) {
        this.exceptionAllgross = exceptionAllgross;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
