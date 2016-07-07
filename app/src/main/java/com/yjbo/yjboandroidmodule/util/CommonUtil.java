package com.yjbo.yjboandroidmodule.util;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;

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

}
