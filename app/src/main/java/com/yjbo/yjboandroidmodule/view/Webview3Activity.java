package com.yjbo.yjboandroidmodule.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboSwipeActivity;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.KProgressDialog;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.ShowTipDialog;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 引用：http://www.open-open.com/lib/view/open1392188052301.html
 * <p/>
 * 缓存模式(5种)
 * LOAD_CACHE_ONLY:  不使用网络，只读取本地缓存数据
 * LOAD_DEFAULT:  根据cache-control决定是否从网络上取数据。
 * LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式
 * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
 * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
 * 如：www.taobao.com的cache-control为no-cache，在模式LOAD_DEFAULT下，无论如何都会从网络上取数据，如果没有网络，就会出现错误页面；
 * 在LOAD_CACHE_ELSE_NETWORK模式下，无论是否有网络，只要本地有缓存，都使用缓存。本地没有缓存时才从网络上获取。
 * www.360.com.cn的cache-control为max-age=60，在两种模式下都使用本地缓存数据。
 * 总结：根据以上两种模式，建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT，无网络时，使用LOAD_CACHE_ELSE_NETWORK。
 * <p/>
 *
 * @author yjbo
 *         2016年8月6日12:19:51
 */


public class Webview3Activity extends BaseYjboSwipeActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    @Bind(R.id.webview)
    WebView mWebView;
    private String url = "https://www.baidu.com/";

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_webview3);
        KProgressDialog.create(Webview3Activity.this);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
        setSGTitleStr("有点击事件的");
        setSGNextStr("···");
        setSGNextColor(R.color.white);
    }

    @Override
    public void setonData() {
//url:http://m.dianhua.cn/detail/31ccb426119d3c9eaa794df686c58636121d38bc?apikey=jFaWGVHdFVhekZYWTBWV1ZHSkZOVlJWY&app=com.yulore.yellowsdk_ios&uid=355136051337627
//        url = "http://m.dianhua.cn/detail/31ccb426119d3c9eaa794df686c58636121d38bc?apikey=jFaWGVHdFVhekZYWTBWV1ZHSkZOVlJWY&app=com.yulore.yellowsdk_ios&uid=355136051337627";
//        findView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        KProgressDialog.show("加载中...");

        String ipTopStr1 = this.getIntent().getStringExtra("ipTopStr");

        L.e("==1==" + ipTopStr1);
        if (!CommonUtil.isNull(ipTopStr1)) {
            url = ipTopStr1;
            findView();
        } else {
            ClipboardManager clip = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//        clip.setText(payPassword); // 复制
            final String clipStr = clip.getText() + ""; // 粘贴
            if (!CommonUtil.isNull(clipStr)) {
                if (clipStr.contains("http") || clipStr.contains("www")) {
                    ShowTipDialog.layDialog(Webview3Activity.this, "是否打开网页", clipStr, "显示", "确定", "取消", true);
                    ShowTipDialog.SetonDialog(new ShowTipDialog.DialogChoose() {
                        @Override
                        public void query(String payPassword) {
                            ShowTipDialog.dimissDia();
                            url = clipStr;
                            findView();
                        }
                    });
                } else {
                    findView();
                }
            } else {
                findView();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void findView() {
        initWebView();

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onLoadResource(WebView view, String url) {

                Log.i(TAG, "onLoadResource url=" + url);

                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webview, String url) {

                Log.i(TAG, "intercept url=" + url);

                webview.loadUrl(url);

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                Log.e(TAG, "onPageStarted");
//                rl_loading.setVisibility(View.VISIBLE); // 显示加载界面
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                String title = view.getTitle();

                Log.e(TAG, "onPageFinished WebView title=" + title);

//                tv_topbar_title.setText(title);
//                tv_topbar_title.setVisibility(View.VISIBLE);
//
//                rl_loading.setVisibility(View.GONE); // 隐藏加载界面
                KProgressDialog.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

//                rl_loading.setVisibility(View.GONE); // 隐藏加载界面
                KProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "加载失败",
                        Toast.LENGTH_LONG).show();
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                Log.e(TAG, "onJsAlert " + message);

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                result.confirm();

                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

                Log.e(TAG, "onJsConfirm " + message);

                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

                Log.e(TAG, "onJsPrompt " + url);

                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

        mWebView.loadUrl(url);
    }

    private void initWebView() {

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式
        // 开启 DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        mWebView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
//      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        Log.i(TAG, "cacheDirPath=" + cacheDirPath);
        //设置数据库缓存路径
        mWebView.getSettings().setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        mWebView.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        mWebView.getSettings().setAppCacheEnabled(true);
    }

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache() {
        KProgressDialog.show("加载中...");
        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);
        Log.e(TAG, "appCacheDir path=" + appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");
        Log.e(TAG, "webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());

        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
        KProgressDialog.dismiss();
    }

    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {

        Log.i(TAG, "delete file path=" + file.getAbsolutePath());

        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
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

    @OnClick({R.id.next_public_txt, R.id.title_public})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_public_txt:
                clearWebViewCache();
                break;
            case R.id.title_public:
                break;
        }
    }
}