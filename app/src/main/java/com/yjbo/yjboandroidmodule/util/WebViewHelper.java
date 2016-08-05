/*
 * Copyright (c) 2016. Vv <envyfan@qq.com><http://www.v-sounds.com/>
 *
 * This file is part of AndroidReview (Android面试复习)
 *
 * AndroidReview is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  AndroidReview is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 * along with AndroidReview.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.yjbo.yjboandroidmodule.util;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Author：Vv on 2015/7/21 16:20
 * Mail：envyfan@qq.com
 * Description：UI帮助类
 */
public class WebViewHelper {

    public static final String LinkCss = "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/css/common.css\">";
    public static final String LinkJS = "<script type=\"text/javascript\" src=\"file:///android_asset/js/common.js\"></script>";
    public static final String Function_RemoveImageAttribute="<script> javascript:removeImageAttribute()</script>";

    public static void initWebViewSettings(WebView webView) {
        WebSettings settings = webView.getSettings();
        //这个单位是SP
        settings.setDefaultFontSize(15);

        settings.setJavaScriptEnabled(true);  //支持js

        settings.setUseWideViewPort(false);  //将图片调整到适合webview的大小

        settings.setSupportZoom(true);  //支持缩放

        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//无论是否有网络，只要本地有缓存，都使用缓存。本地没有缓存时才从网络上获取。 这里的WebView主要是用来加载图片和解析Html文本

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使超链接失效
                return true;
            }
        });

    }

    /**
     * 拼凑HTML的头部
     *
     * @return
     */
    public static String getWebViewHead() {
        StringBuffer htmlHead = new StringBuffer();
        htmlHead.append("<head>");
        htmlHead.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
//        htmlHead.append(WebViewHelper.setWebViewPadding(2, 2));
        htmlHead.append(WebViewHelper.LinkCss);
        htmlHead.append(WebViewHelper.LinkJS);
        htmlHead.append("</head>");
        return htmlHead.toString();
    }

    public static String getWebViewHtml(String content) {
        StringBuffer htmlBody = new StringBuffer();
        String body = content;
        htmlBody.append(getWebViewHead());
        htmlBody.append("<body>");
//        htmlBody.append("<div>");
        htmlBody.append(body);
//        htmlBody.append("</div>");
        htmlBody.append(Function_RemoveImageAttribute);
        htmlBody.append("</body>");
        return htmlBody.toString();
    }

    /**
     * 全局DIV标签CSS样式
     *
     * @return
     */
    public static String setWebViewPadding(int left, int right, int top) {
        String p = "<style type=\"text/css\">\n" +
                "  div {" +
                "       padding-left:" + 20 + "px;" +
                "       padding-right:" + 20 + "px;" +
                "       padding-top:" + 20 + "px;" +
                "}\n" +
                "</style>";
        return p;
    }

    public static String setWebViewPadding(int rt, int top) {
        return setWebViewPadding(rt, rt, top);
    }
//    public static float dpToPixel(float dp) {
//        return dp * (getDisplayMetrics().densityDpi / 160F);
//    }
//    public static DisplayMetrics getDisplayMetrics() {
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        ((WindowManager) Context.getSystemService(
//                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
//                displaymetrics);
//        return displaymetrics;
//    }
}
