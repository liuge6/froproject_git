package com.fandrproject.frpro.data.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by sml
 * 异常数据统计实体 bean
 * 2020/11/08 20:10
 */
public class ExceptionDataStatisticsBean {

    private String id;

    private String tableName;

    /**
     * 异常数据总量
     */
    private String exceptionAllgross;

    private String createDate;

    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
