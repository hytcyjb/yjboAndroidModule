package com.yjbo.yjboandroidmodule.util;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

/**
 * 工具类
 * Created by yjbo on 2016/6/6.
 */
public class CommonUtil {
    /***
     * 判断是否打开wifi，然后直接打开wifi；
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
                                int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else
            {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }
    // 如果是最后一行，则不需要绘制底部
    public static boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public static int getSpanCount(RecyclerView parent)
    {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }
    /**
     * 防止过快点击造成多次事件
     * @return true 是快速点击，不能往下走
     *          false 不是快速点击
     */
    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
