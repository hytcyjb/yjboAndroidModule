package com.yjbo.yjboandroidmodule.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
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
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.L;
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
        ButterKnife.bind(this);
        setSGBackVisible();
        setSGTitleStr("各知识模块知识总结");
        initSwipeLayout();
        setSGNextStr("···");
        setSGNextColor(R.color.white);
    }

    @TargetApi(Build.VERSION_CODES.M)
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
                    int spanCount = CommonUtil.getSpanCount(recyclerView);
                    int childCount = recyclerView.getAdapter().getItemCount();
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
        swipeTarget.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });
        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        listAdapter.SetonDialogChoose(new ListAdapter.DialogChoose() {
            @Override
            public void pos(int position) {
                if (CommonUtil.isFastClick()) {
                    return;
                }
                switch (position) {
                    case 0://6.0的侧滑框架
                        startClass(NavigateActivity.class, position);
                        break;
                    case 1://研究的知识
                        startClass(StudyKWActivity.class, position);
                        break;
                    case 2://基础的知识
                        startClass(BaseKWActivity.class, position);
                        break;
                }
            }
        });
    }

    private void startClass(Class<?> cls, int pos) {
        String titleName = list.get(pos);
        startActivity(new Intent(MainActivity.this, cls).putExtra("titleName", titleName));
    }

    @Override
    public void setonData() {
        listAdapter = new ListAdapter();
        list = CommonUtil.getListMenu();
        listAdapter.bindData(list, MainActivity.this);
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

    @OnClick({R.id.next_public_txt, R.id.top_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_public_txt:
                Intent minten = new Intent(MainActivity.this, WebViewActivity.class);
                minten.putExtra("url", "https://github.com/hytcyjb/yjboAndroidModule");
                minten.putExtra("titleStr", "github主页");
                startActivity(minten);
                break;
            case R.id.top_btn:

                break;
        }
    }



    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                CommonUtil.show(MainActivity.this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
