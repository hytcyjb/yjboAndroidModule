package com.yjbo.yjboandroidmodule.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.ListAdapter;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.test.testActivity;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.StaticStr;
import com.yjbo.yjboandroidmodule.util.video.TakeVideoActivity;
import com.yjbo.yjboandroidmodule.util.view.DividerItemDecorationHx;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * 基础知识
 * 2016年8月2日17:57:02
 *
 * @author yjbo
 */
public class BaseKWActivity extends BaseYjboActivity implements OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    ListAdapter listAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base_kw);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
        setSGBackStr("主页");
        initSwipeLayout();
//        setSGNextStr("···");
//        setSGNextColor(R.color.white);
    }

    private void initSwipeLayout() {
        View hearload = LayoutInflater.from(this).inflate(R.layout.swipe_google_header, swipeToLoadLayout, false);
        View footload = LayoutInflater.from(this).inflate(R.layout.swipe_classic_footer, swipeToLoadLayout, false);
        swipeToLoadLayout.setRefreshHeaderView(hearload);
        swipeToLoadLayout.setLoadMoreFooterView(footload);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        //添加list的分割线
        swipeTarget.addItemDecoration(new DividerItemDecorationHx(getApplicationContext(), DividerItemDecorationHx.VERTICAL_LIST));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

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
    protected void onStart() {
        super.onStart();
        listAdapter.SetonDialogChoose(new ListAdapter.DialogChoose() {
            @Override
            public void pos(int position) {
                if (CommonUtil.isFastClick()) {
                    return;
                }
                switch (position) {
                    case 0://获取屏幕旋转角度
                        startClass(ScreenDirectionActivity.class, position);
                        break;
                    case 1://固定解析json字符串
                        startClass(JsonActivity.class, position);
                        break;
                    case 2://textstyle的展示
                        startClass(TextViewLinkActivity.class, position);
                        break;
                    case 3://显示通知栏
                        startClass(NotificationActivity.class, position);
                        break;
                    case 4://测试横向滑动Fragment和侧滑同时使用的冲突问题
                        startClass(ViewPageActivity.class, position);
                        break;
                    case 5://测试Activity生命周期
                        startClass(testActivity.class, position);
                        break;
                    case 6://测试Service的生命周期
                        startClass(TestServiceActivity.class, position);
                        break;
                    case 7://自动打开wifi模块
                        startClass(WifiOpenActivity.class, position);
                        break;
                    case 8://显示自定义颜色的进度条
                        startClass(ProgressSimpleActivity.class, position);
                        break;
                    case 9://密码框
                        startClass(PassWordActivity.class, position);
                        break;
                }
            }
        });
    }

    private void startClass(Class<?> cls, int pos) {
        String titleName = list.get(pos);
        startActivity(new Intent(BaseKWActivity.this, cls).putExtra("titleName", titleName));
    }


    @Override
    public void setonData() {
        listAdapter = new ListAdapter();
        list = StaticStr.getListBaseKW();
        listAdapter.bindData(list, BaseKWActivity.this);
        swipeTarget.setAdapter(listAdapter);
    }

    @Override
    public void onLoadMore() {
        loadover();
    }

    @Override
    public void onRefresh() {
        loadover();
    }

    //刷新结束
    private void loadover() {
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
