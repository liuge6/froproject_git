package com.fandrproject.frpro.osanddivide.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 分成支付方式 Bean
 * Created by sml
 * 2020/12/12 17:22
 */
public class DividePayTypeConfigBean {

    private Integer id;

    private String payCode;

    private String bankTypeCode;

    private String createTime;

    private String updateTime;

    private int isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getBankTypeCode() {
        return bankTypeCode;
    }

    public void setBankTypeCode(String bankTypeCode) {
        this.bankTypeCode = bankTypeCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
