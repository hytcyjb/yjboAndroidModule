package com.yjbo.yjboandroidmodule.entity;

/**
 * @description: <描述当前版本功能>
 * <p>
 *     保存url的相关属性
 * </p>
 * @author: yjbo
 * @date: 2016-08-11 14:58
 */
public class PersonClass {

    private int _id;//数字
    private String pname;//人的姓名
    private String pid;//人的id，编号
    private String pregister_time;//人的注册app时间
    private String pregister_hx_time;//人的注册环信时间
    private String psign;//人的个性签名时间
    private String other;//其他

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPregister_time() {
        return pregister_time;
    }

    public void setPregister_time(String pregister_time) {
        this.pregister_time = pregister_time;
    }

    public String getPregister_hx_time() {
        return pregister_hx_time;
    }

    public void setPregister_hx_time(String pregister_hx_time) {
        this.pregister_hx_time = pregister_hx_time;
    }

    public String getPsign() {
        return psign;
    }

    public void setPsign(String psign) {
        this.psign = psign;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
