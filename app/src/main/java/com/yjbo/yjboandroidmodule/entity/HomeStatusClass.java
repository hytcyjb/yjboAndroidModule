package com.yjbo.yjboandroidmodule.entity;

/**
 * @description: <描述当前版本功能>
 * <p>
 * 所有的情况
 * </p>
 * @author: yjbo
 * @date: 2016-07-12 19:25
 */
public class HomeStatusClass {

    String statusStr;

    public HomeStatusClass(String mstatusStr) {
        statusStr = mstatusStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
