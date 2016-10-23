package com.yjbo.yjboandroidmodule.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: <描述当前版本功能>
 * <p>
 * 静态变量
 * </p>
 * @author: yjbo
 * @date: 2016-08-08 11:56
 */
public class StaticStr {

    /***
     * 主listview里面的条目
     * 2016年8月2日17:17:27
     *
     * @return
     */
    public static List<String> getListMenu() {
        List<String> list = new ArrayList<>();
        list.add("Material Design侧滑");
        list.add("值得研究知识");
        list.add("基础知识");
        list.add("环信聊天");
        return list;
    }

    /***
     * 学习知识里面的条目
     * 2016年8月2日18:05:43
     *
     * @return
     */
    public static List<String> getListStudyKW() {
        List<String> list = new ArrayList<>();
        list.add("横向滑动fragment");
        list.add("handler的内存泄露处理");
        list.add("录视频");
        list.add("后台录视频");
        list.add("测试eventbus");
        list.add("事件的分发1");
        list.add("创建桌面快捷方式");
        list.add("缓存网页列表");
        list.add("图片加载框架(eventbus监听)");
        list.add("添加头布局(eventbus监听)");
        list.add("仿今日头条");
        return list;
    }
    /***
     * 获取多个图片加载框架
     * 2016年8月17日18:31:18
     *
     * @return
     */
    public static List<String> getListPicMain() {
        List<String> list = new ArrayList<>();
        list.add("glide图片加载框架");
        list.add("Picasso图片加载框架");
        list.add("Universal-Image-Loader图片加载框架");
//        list.add("ImageLoad图片加载框架");
        return list;
    }

    /***
     * 基础知识里面的条目
     * 2016年8月2日18:05:43
     *
     * @return
     */
    public static List<String> getListBaseKW() {
        List<String> list = new ArrayList<>();
        list.add("获取屏幕旋转角度");
        list.add("固定解析json字符串");
        list.add("textstyle的展示");
        list.add("显示通知栏");
        list.add("测试横向滑动Fragment和侧滑同时使用的冲突问题");
        list.add("测试Activity生命周期");
        list.add("测试Service的生命周期");
        list.add("自动打开wifi模块");
        list.add("显示自定义颜色的进度条");
        list.add("密码框");
        list.add("输入框");
        list.add("PopWindow框");
        return list;
    }
    /***
     * 图片列表
     * 2016年8月8日11:57:24
     * @return
     */
    public static List<String> getListPic() {
        List<String> list = new ArrayList<>();
        list.add("http://static.freepik.com/free-photo/d-little-firecrackers-picture-material_38-5487.jpg");
        list.add("http://img3.redocn.com/20130929/Redocn_2013092911261428.jpg");
        list.add("http://pic2.cxtuku.com/00/06/74/b1653eeebedb.jpg");
        list.add("http://s8.rr.itc.cn/g/wapChange/20154_8_10/a52sxk8553415534305.JPG");
        list.add("http://img2.imgtn.bdimg.com/it/u=3250396047,1017289139&fm=206&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=1223947730,1395085776&fm=206&gp=0.jpg");
        list.add("http://www.yooyoo360.com/photo/2009-1-1/20090112202609980.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2242341618,380893767&fm=206&gp=0.jpg");
        list.add("http://download.5tu.cn/attachments/month_1305/1305302028fa07fb0d32848807.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=1960972143,2763708506&fm=206&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2000801586,4168363489&fm=206&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=249104827,2140680360&fm=206&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3982832863,472998661&fm=206&gp=0.jpg");
        list.add("http://pic.qiantucdn.com/58pic/17/82/80/5598dbda430f0_1024.jpg");
        return list;
    }

}
