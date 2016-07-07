package com.yjbo.yjboandroidmodule.base;

import android.app.Activity;

import com.yjbo.yjboandroidmodule.util.L;

import java.util.LinkedList;
import java.util.List;



/**
 * 用户类 ：使用单例模式写的
 *
 * @author yjbo
 */
public class SouGuClass {

    public static final String TAG_STRING = "SouGu";

    private static SouGuClass souGuClass = new SouGuClass();

    public static SouGuClass getInstance() {

        return souGuClass;
    }

    ///n{"error":"1","message":"网络连接正常","sougu":"200","date":"2016-04-11 13:56:07",
    // "time":"1460354167","microtime":"0.015600","session":"GlobalVar"}

    private String error;
    private String message;
    private String sougu;
    private String date;
    private String time;
    private String microtime;
    private String session;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSougu() {
        return sougu;
    }

    public void setSougu(String sougu) {
        this.sougu = sougu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMicrotime() {
        return microtime;
    }

    public void setMicrotime(String microtime) {
        this.microtime = microtime;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    private static List<Activity> mList = new LinkedList<Activity>();

    // add Activity,
    public void addActivity(Activity activity) {
        if (!mList.contains(activity)) {
            mList.add(activity);
        }
    }


    // 正常情况下退出app
    public static void exitApp_nomal() {
        tuiChuData();
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 退出账号
    public static void logoutApp_nomal() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    if ("LoginActivity".equals(activity.getLocalClassName())) {
                        continue;
                    } else {
                        activity.finish();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 关闭某个activity
    public static void destory_Activity(String activityName) {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    L.i("展示：" + activityName + "===" + activity.getLocalClassName());
                    if (activityName.equals(activity.getLocalClassName())) {
                        mList.remove(activity);
                        activity.finish();
                        if (activityName.equals("QueryOrderActivity")) {
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 捕捉异常（崩了）
    public static void exitApp_catch() {
        tuiChuData();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // System.exit(0);
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 退出和第一次进入app的处理应该是一样的：
     * 退出之前将数据恢复为初始值 2016年2月1日18:31:13
     */
    public static void tuiChuData() {

    }

    /**
     * 展示所有的activity名称
     * 2016年6月23日14:17:48
     */
    public static String showAllActivityName() {
        try {
            String allActivityName = "展示所有的activity名称：";
            for (Activity activity : mList) {
                if (activity != null) {
//                    if (QueryOrderActivity.class.getSimpleName().equals(activity.getLocalClassName())) {
//                        activity.finish();
//                    }
                    allActivityName += activity.getLocalClassName() + "--";
                }
            }
            return allActivityName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "出现异常，无法展示所有的activity名称";
    }

    /**
     * 推送app的时候再次登录得初始化数据
     * 2016年2月26日09:59:44
     */
    public static void umengSongData() {
    }

    // 只显示SouGUMainActivity和HomeMainActivity2个activity
    public static void show_sougu_home_Activity() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    if (!"HomeMainActivity".equals(activity.getLocalClassName())
                            && !"SouGUMainActivity".equals(activity.getLocalClassName())) {
                        mList.remove(activity);
                        activity.finish();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //只保留HomeMainActivity页面，但是发现有问题
    public static void destorySomeActivity() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    if (!"HomeMainActivity".equals(activity.getLocalClassName())) {
                        mList.remove(activity);
                        activity.finish();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
