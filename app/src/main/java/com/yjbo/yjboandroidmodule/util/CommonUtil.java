package com.yjbo.yjboandroidmodule.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.yjbo.yjboandroidmodule.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 * Created by yjbo on 2016/6/6.
 */
public class CommonUtil {
    /***
     * 判断是否打开wifi，然后直接打开wifi；
     *
     * @param mactivity
     */
    public static void OpenWifi(Activity mactivity) {
        //2·获取WifiManager
        WifiManager wifiManager = (WifiManager) mactivity.getSystemService(Context.WIFI_SERVICE);
        //3·查看wifi状态
        if (wifiManager.isWifiEnabled()) {//当前是打开状态
        } else {//当前是关闭状态
            wifiManager.setWifiEnabled(true);
        }
    }

    /***
     * 将颜色转换成string型
     *
     * @param color
     * @return
     */
    public static String colorToString(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;

        String red = Integer.toHexString(r);
        red = red.length() == 1 ? "0" + red : red;

        String green = Integer.toHexString(g);
        green = green.length() == 1 ? "0" + green : green;

        String blue = Integer.toHexString(b);
        blue = blue.length() == 1 ? "0" + blue : blue;

        return String.format("#%s%s%s", red,
                green,
                blue);
    }

    /***
     * 土司
     *
     * @param context
     * @param str
     */
    public static void show(Context context, String str) {
        if (!isNull(str)) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断字符是否为空
     * 空--true
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        String str1;
        try {
            str1 = str.trim();
        } catch (Exception e) {
            str1 = str;
        }
        if ("".equals(str1) || str1 == null || "null".equals(str1)
                || str1.length() == 0) {
            return true;
        }
        return false;
    }

    //使用方法
//    int spanCount = getSpanCount(parent);
//    int childCount = parent.getAdapter().getItemCount();
//    if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
//    {
//        outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
//    } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
//    {
//        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
//    } else
//    {
//        outRect.set(0, 0, mDivider.getIntrinsicWidth(),
//                mDivider.getIntrinsicHeight());
//    }

    // 如果是最后一列，则不需要绘制右边
    public static boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                      int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    // 如果是最后一行，则不需要绘制底部
    public static boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                                    int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    /**
     * 防止过快点击造成多次事件
     *
     * @return true 是快速点击，不能往下走
     * false 不是快速点击
     */
    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 这是24小时以内（不包含24小时）采用的方法：
     * 2016年7月15日15:48:01
     *
     * @param dateInt
     * @return 时间格式
     */
    public static String getFormatedDateTime(int dateInt) {
        String dateTime = "";
        if (dateInt < 10) {
            dateTime = "00:0" + dateInt;
        } else if (dateInt < 60) {
            dateTime = "00:" + dateInt;
        } else if (dateInt < (60 * 60 + 1)) {//60' * 60 +1
            int miaoInt = dateInt % 60;
            int fenInt = dateInt / 60;
            //分别判断分钟和秒钟是否大于10，进行赋值；大于10则不需要前面放一个0了
            String fenStr = "";
            String miaoStr = "";
            if (fenInt < 10) {
                fenStr = "0" + fenInt;
            } else {
                fenStr = "" + fenInt;
            }
            if (miaoInt < 10) {
                miaoStr = "0" + miaoInt;
            } else {
                miaoStr = "" + miaoInt;
            }
            dateTime = fenStr + ":" + miaoStr;
        } else if (dateInt < (60 * 60 * 24 + 1)) {//60' * 60' * 24 +1

            int shiInt = dateInt / 60 / 60;

            dateInt = dateInt - shiInt * 60 * 60;
            int miaoInt = dateInt % 60;
            int fenInt = dateInt / 60;

            //分别判断小时，分钟和秒钟是否大于10，进行赋值；大于10则不需要前面放一个0了
            String shiStr = "";
            String fenStr = "";
            String miaoStr = "";

            if (shiInt < 10) {
                shiStr = "0" + shiInt;//这个不会遇到，因为没有到一小时，不走这里
            } else {
                shiStr = "" + shiInt;
            }
            if (fenInt < 10) {
                fenStr = "0" + fenInt;
            } else {
                fenStr = "" + fenInt;
            }
            if (miaoInt < 10) {
                miaoStr = "0" + miaoInt;
            } else {
                miaoStr = "" + miaoInt;
            }

            dateTime = shiStr + ":" + fenStr + ":" + miaoStr;
        } else {
            dateTime = "时间到停止计时";
        }

        return dateTime + "s";
    }



    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对像
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info == null || !info.isAvailable()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void dynamicSetTabLayoutMode(TabLayout tabLayout) {
        int tabWidth = calculateTabWidth(tabLayout);
        int screenWidth = getScreenWith();

        if (tabWidth <= screenWidth) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }
    private static int calculateTabWidth(TabLayout tabLayout) {
        int tabWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0); // 通知父view测量，以便于能够保证获取到宽高
            tabWidth += view.getMeasuredWidth();
        }
        return tabWidth;
    }

    public static int getScreenWith() {
        return MyApplication.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }
}
