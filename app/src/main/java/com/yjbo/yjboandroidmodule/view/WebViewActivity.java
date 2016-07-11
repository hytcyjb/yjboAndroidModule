package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * web页面公共类
 * 2016年7月11日15:57:51
 * @author yjbo
 */
public class WebViewActivity extends BaseYjboActivity {


    @Bind(R.id.webview)
    WebView mWebView;
    String url = "https://123.sogou.com/";
    String titleStr = "";
    KProgressHUD dialog;

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_web);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
        dialog = KProgressHUD.create(WebViewActivity.this);
        dialog.setCancellable(true);
        dialog.show();
    }

    @Override
    public void setonData() {
        Intent i = getIntent();
        Bundle b = i.getExtras();
        url = getIntent().getStringExtra("url");
        titleStr = getIntent().getStringExtra("titleStr");
        setSGTitleStr(titleStr);
        initView();
    }

    private void initView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowFileAccess(true);
        // 设置自动适屏，并且不允许双击缩放
        webSettings.setUseWideViewPort(false);
        webSettings.setSupportZoom(false);
        // 预加载，防止底部空白出现
        webSettings.setLoadWithOverviewMode(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            mWebView.goBack();
            return true;
        } else {
            this.finish();
        }
        return false;
    }
}