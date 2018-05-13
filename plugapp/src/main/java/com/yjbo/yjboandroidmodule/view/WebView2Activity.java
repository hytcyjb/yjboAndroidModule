package com.yjbo.yjboandroidmodule.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboSwipeActivity;
import com.yjbo.yjboandroidmodule.interfa.HttpService;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.ShowTipDialog;
import com.yjbo.yjboandroidmodule.util.WebViewHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/****
 * 这个显示webview是有问题的
 *
 * @author yjbo
 *         2016年8月8日11:52:37
 */
public class WebView2Activity extends BaseYjboSwipeActivity {

    @Bind(R.id.webview)
    WebView mWebView;
    private String TMP_URL = "https://www.baidu.com/";
    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final static int FILECHOOSER_RESULTCODE = 1;// 表单的结果回调</span>
    private Uri imageUri;
    String titleStr = "";
    String ipTopStr = TMP_URL;
    String ipBottomStr = "";
    static int maxAge = 6; // 在线缓存在1分钟内可读取

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_2);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setonData() {
        setSGNextStr("刷新");
        setSGTitleStr("只是缓存文字图片，不能点击");
        setSGNextDrawablGone();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String ipTopStr1 = this.getIntent().getStringExtra("ipTopStr");
        String ipBottomStr1 = this.getIntent().getStringExtra("ipBottomStr");

        L.e("==1==" + ipTopStr1 + "----" + ipBottomStr1);
        if (!CommonUtil.isNull(ipTopStr1)) {
            ipTopStr = ipTopStr1;
            ipBottomStr = ipBottomStr1;
            initGet();
        } else {
            ClipboardManager clip = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//        clip.setText(payPassword); // 复制
            final String clipStr = clip.getText() + ""; // 粘贴
            L.e("==3==" + ipTopStr + "----" + ipBottomStr);
            if (!CommonUtil.isNull(clipStr)) {
                if (clipStr.contains("http") || clipStr.contains("www")) {
                    ShowTipDialog.layDialog(WebView2Activity.this, "是否打开网页", clipStr, "显示", "确定", "取消", true);
                    ShowTipDialog.SetonDialog(new ShowTipDialog.DialogChoose() {
                        @Override
                        public void query(String payPassword) {
                            ShowTipDialog.dimissDia();
                            ipTopStr = clipStr.substring(0, clipStr.lastIndexOf("/") + 1);
                            ipBottomStr = clipStr.substring(clipStr.lastIndexOf("/") + 1, clipStr.length());
                            initGet();
                        }
                    });
                } else {
                    initGet();
                }
            } else {
                initGet();
            }
        }
        setWeb();
        L.e("==2==" + ipTopStr + "----" + ipBottomStr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        titleStr = getIntent().getStringExtra("titleStr");
//        TMP_URL = getIntent().getStringExtra("url");

//        mWebView.loadUrl(TMP_URL);
//
//        mWebView.loadDataWithBaseURL(null, WebViewHelper.getWebViewHtml("----"), "text/html", "UTF-8", null);

    }

    private void setWeb() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             FileChooserParams fileChooserParams) {
                mUploadCallbackAboveL = filePathCallback;
                take();
                return true;
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                take();
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                take();
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                take();
            }
        });
    }


    /***
     * 获取服务器数据
     */
    private void initGet() {
        //设置缓存
        File httpCacheDirectory = new File(WebView2Activity.this.getCacheDir(), "cache_responses_yjbo");

        Cache cache = null;
        try {
            cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(interceptor)
                .build();
        L.e("==4==" + ipTopStr + "----" + ipBottomStr);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipTopStr)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        final HttpService service = retrofit.create(HttpService.class);
        Call<ResponseBody> call = service.getFirstBlog10(ipBottomStr);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    Log.i("yjbo-获取数据10：", "===" + response.body() + "---" + response.message() + "---" + response
                            + "=---" + response.headers() + "----" + response.toString() + "-----" + response.body().toString()
                            + "====");
                    mWebView.loadDataWithBaseURL(null, WebViewHelper.getWebViewHtml(response.body().string() + ""), "text/html", "UTF-8", null);
                } catch (Exception e) {
                    e.printStackTrace();
                    mWebView.loadDataWithBaseURL(null, WebViewHelper.getWebViewHtml("异常报错"), "text/html", "UTF-8", null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /***
     * 拦截器，保存缓存的方法
     * 2016年7月29日11:22:47
     */
    Interceptor interceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (CommonUtil.checkNet(WebView2Activity.this)) {
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                Log.e("yjbo-cache", "在线缓存在1分钟内可读取" + cacheControl);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                Log.e("yjbo-cache", "离线时缓存时间设置");
                request = request.newBuilder()
                        .cacheControl(FORCE_CACHE1)//此处设置了7秒---修改了系统方法
                        .build();

                Response response = chain.proceed(request);
                //下面注释的部分设置也没有效果，因为在上面已经设置了
                return response.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=50")
                        .build();
            }
        }
    };
    //这是设置在多长时间范围内获取缓存里面
    public static final CacheControl FORCE_CACHE1 = new CacheControl.Builder()
            .onlyIfCached()
            .maxStale(maxAge, TimeUnit.SECONDS)//这里是7s，CacheControl.FORCE_CACHE--是int型最大值
            .build();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                Log.e("result", result + "");
                if (result == null) {
//	            		mUploadMessage.onReceiveValue(imageUri);
                    mUploadMessage.onReceiveValue(imageUri);
                    mUploadMessage = null;

                    Log.e("imageUri", imageUri + "");
                } else {
                    mUploadMessage.onReceiveValue(result);
                    mUploadMessage = null;
                }


            }
        }
    }


    @SuppressWarnings("null")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE
                || mUploadCallbackAboveL == null) {
            return;
        }

        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                results = new Uri[]{imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        if (results != null) {
            mUploadCallbackAboveL.onReceiveValue(results);
            mUploadCallbackAboveL = null;
        } else {
            results = new Uri[]{imageUri};
            mUploadCallbackAboveL.onReceiveValue(results);
            mUploadCallbackAboveL = null;
        }

        return;
    }


    private void take() {
        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
        // Create the storage directory if it does not exist
        if (!imageStorageDir.exists()) {
            imageStorageDir.mkdirs();
        }
        File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        imageUri = Uri.fromFile(file);

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent i = new Intent(captureIntent);
            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            i.setPackage(packageName);
            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntents.add(i);

        }
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
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

    @OnClick({R.id.right_next_public_txt, R.id.next_public_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_next_public_txt:
                break;
            case R.id.next_public_txt://刷新
                initGet();
                setWeb();
                break;
        }
    }
}
