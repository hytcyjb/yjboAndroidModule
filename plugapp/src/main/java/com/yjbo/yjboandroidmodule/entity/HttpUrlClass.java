package com.yjbo.yjboandroidmodule.entity;

/**
 * @description: <描述当前版本功能>
 * <p>
 *     保存url的相关属性
 * </p>
 * @author: yjbo
 * @date: 2016-08-11 14:58
 */
public class HttpUrlClass {

    private int _id;//数字
    private String time;//时间
    private String content;//内容
    private String title;//主标题
    private String smail_title;//副标题
    private String http_url;//http路径
    private String http_url_hashcode;//http路径的哈希code值
    private String love_num;//收藏次数，0次则没收藏，其他则说明你对这个知识点比较重视
    private String is_cache;//是否保存缓存
    private String kind;//属于哪一类目：cache，rxjava，retrofit等
    private String share_num;//分享次数
    private String other;//其他

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmail_title() {
        return smail_title;
    }

    public void setSmail_title(String smail_title) {
        this.smail_title = smail_title;
    }

    public String getHttp_url() {
        return http_url;
    }

    public void setHttp_url(String http_url) {
        this.http_url = http_url;
    }

    public String getHttp_url_hashcode() {
        return http_url_hashcode;
    }

    public void setHttp_url_hashcode(String http_url_hashcode) {
        this.http_url_hashcode = http_url_hashcode;
    }

    public String getLove_num() {
        return love_num;
    }

    public void setLove_num(String love_num) {
        this.love_num = love_num;
    }

    public String getIs_cache() {
        return is_cache;
    }

    public void setIs_cache(String is_cache) {
        this.is_cache = is_cache;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getShare_num() {
        return share_num;
    }

    public void setShare_num(String share_num) {
        this.share_num = share_num;
    }

}
