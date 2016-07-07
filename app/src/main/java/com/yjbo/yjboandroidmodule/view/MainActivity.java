package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.ListAdapter;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;

import org.json.JSONObject;

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
    private List<String> list = new ArrayList<>();
    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
    }

    @Override
    public void setonView() {
        setSGBackVisible();
        setSGTitleStr("各知识模块知识总结");
        initSwipeLayout();
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
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
        listAdapter.bindData(list, MainActivity.this);
        swipeTarget.setAdapter(listAdapter);
    }

    @Override
    public void onLoadMore() {
        loadover();
    }

    @Override
    public void onRefresh() {
        listAdapter.addData(3,"你好呀，字符插进来了");
        loadover();
    }
    //刷新结束
    private void loadover() {
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }
    @OnClick({R.id.wifi_txt, R.id.scree_direct_txt, R.id.top_slid_fragment, R.id.handler_exception_catch
            , R.id.json_txt, R.id.textstyle_txt, R.id.notification_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wifi_txt:
                startActivity(new Intent(MainActivity.this, WifiOpenActivity.class));
                break;
            case R.id.scree_direct_txt:
                startActivity(new Intent(MainActivity.this, ScreenDirectionActivity.class));
                break;
            case R.id.top_slid_fragment:
                startActivity(new Intent(MainActivity.this, SlidFragmentActivity.class));
                break;
            case R.id.handler_exception_catch://https://github.com/badoo/android-weak-handler/
                startActivity(new Intent(MainActivity.this, HandlerOomActivity.class));
                break;
            case R.id.json_txt:
                startActivity(new Intent(MainActivity.this, JsonActivity.class));
                break;
            case R.id.textstyle_txt:
                startActivity(new Intent(MainActivity.this, TextViewLinkActivity.class));
                break;
            case R.id.notification_txt:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
        }
    }


}
