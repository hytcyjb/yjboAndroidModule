package com.yjbo.yjboandroidmodule.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yjbo.yjboandroidmodule.BuildConfig;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.ListAdapter;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.test.testActivity;
import com.yjbo.yjboandroidmodule.test.testService;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.ShowCutUtil;
import com.yjbo.yjboandroidmodule.util.video.TakeVideoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 各种模块的开发
 * 2016年6月6日11:47:55
 *
 * @author yjbo
 */
public class MainActivity extends BaseYjboActivity implements OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    ListAdapter listAdapter;
    @Bind(R.id.top_btn)
    Button topBtn;
    private List<String> list = new ArrayList<>();

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        if (BuildConfig.DEBUG) {
            L.isDebug = true;
            L.i("当前是debug模式");
        }
    }

    @Override
    public void setonView() {
        ButterKnife.bind(MainActivity.this);
        setSGBackVisible();
        setSGTitleStr("各知识模块知识总结");
        initSwipeLayout();
        setSGNextStr("···");
        setSGNextColor(R.color.white);
    }

    private void initSwipeLayout() {
        View hearload = LayoutInflater.from(this).inflate(R.layout.swipe_google_header, swipeToLoadLayout, false);
        View footload = LayoutInflater.from(this).inflate(R.layout.swipe_classic_footer, swipeToLoadLayout, false);
        swipeToLoadLayout.setRefreshHeaderView(hearload);
        swipeToLoadLayout.setLoadMoreFooterView(footload);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        RecyclerView.LayoutManager layoutManager = null;
        layoutManager = new LinearLayoutManager(this);
        swipeTarget.setLayoutManager(layoutManager);
        swipeToLoadLayout.setRefreshEnabled(true);
        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                L.i("--onScrollStateChanged的状态值--" + newState);//手指滑动的时候是1，手指离开之后就是0
//                setSGTitleStr("--onScrollSta=="+newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int spanCount = CommonUtil.getSpanCount(recyclerView);
                    int childCount = recyclerView.getAdapter().getItemCount();
                    setSGTitleStr("--onScrollSta==" + newState + "--" + spanCount + "===" + childCount);
//                    if (CommonUtil.isLastRaw(recyclerView, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
//                    {
//
//                    }
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
        listAdapter.SetonDialogChoose(new ListAdapter.DialogChoose() {
            @Override
            public void pos(int position) {
                if (CommonUtil.isFastClick()) {
                    return;
                }
                switch (position) {
                    case 0:
                        startClass(WifiOpenActivity.class, position);
                        break;
                    case 1:
                        startClass(ScreenDirectionActivity.class, position);
                        break;
                    case 2:
                        startClass(SlidFragmentActivity.class, position);
                        break;
                    case 3://https://github.com/badoo/android-weak-handler/
                        startClass(HandlerOomActivity.class, position);
                        break;
                    case 4:
                        startClass(JsonActivity.class, position);
                        break;
                    case 5:
                        startClass(TextViewLinkActivity.class, position);
                        break;
                    case 6:
                        startClass(NotificationActivity.class, position);
                        break;
                    case 7:
                        startClass(TakeVideoActivity.class, position);
                        break;
                    case 8:
                        startClass(MyCramerActivity.class, position);
                        break;
                    case 9:
                        startClass(EventbusActivity.class, position);
                        break;
                    case 10:
                        startClass(ViewPageActivity.class, position);
                        break;
                    case 11:
                        startClass(testActivity.class, position);
                        break;
                    case 12:
                        startClass(ViewGroupActivity.class, position);
                        break;
                }
            }
        });
    }

    private void startClass(Class<?> cls, int pos) {
        String titleName = list.get(pos);
        startActivity(new Intent(MainActivity.this, cls).putExtra("titleName", titleName));
        //测试服务的启动
        //"测试Service-startService生命周期"
//        if (pos % 2 == 1) {
//            startService(new Intent(MainActivity.this, testService.class));
//        } else {
//            stopService(new Intent(MainActivity.this, testService.class));
//        }
        //"测试Service-bind生命周期"
        if (pos % 2 == 1) {
            bindService(new Intent(MainActivity.this, testService.class), sc, BIND_AUTO_CREATE);
        } else {
            unbindService(sc);
        }
    }

    @Override
    public void setonData() {
        listAdapter = new ListAdapter();
        list.add("自动打开wifi模块");
        list.add("获取屏幕旋转角度");
        list.add("横向滑动fragment");
        list.add("handler的内存泄露处理");
        list.add("固定解析json字符串");
        list.add("textstyle的展示");
        list.add("显示通知栏");
        list.add("录视频");
        list.add("后台录视频");
        list.add("测试eventbus");
        list.add("测试横向滑动Fragment和侧滑同时使用的冲突问题");
        list.add("测试Activity生命周期");
        list.add("事件的分发1");
        listAdapter.bindData(list, MainActivity.this);
        swipeTarget.setAdapter(listAdapter);
        // Android创建桌面快捷方式
        ShowCutUtil.CreateShotCut(MainActivity.this,ShowContentActivity.class,"yjb快捷方式","");
    }

    @Override
    public void onLoadMore() {
//        listAdapter.removeData(3);
        loadover();
    }

    @Override
    public void onRefresh() {
//        listAdapter.addData(3, "你好呀，字符进来了");
        loadover();
    }

    //刷新结束
    private void loadover() {
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @OnClick({R.id.next_public_txt, R.id.top_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_public_txt:
                Intent minten = new Intent(MainActivity.this, WebViewActivity.class);
                minten.putExtra("url", "https://github.com/hytcyjb/yjboAndroidModule");
                minten.putExtra("titleStr", "各种模块的开发");
                startActivity(minten);
                break;
            case R.id.top_btn:

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    ServiceConnection sc = new ServiceConnection() {

        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            System.out.println("onServiceConnected");
        }

        public void onServiceDisconnected(ComponentName arg0) {
            System.out.println("onServiceDisconnected");
        }

    };

}
