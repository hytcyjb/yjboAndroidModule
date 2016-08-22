package com.yjbo.mvp.PictureActivity.views;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yjbo.yjboandroidmodule.BuildConfig;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.ListAdapter;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.base.BaseYjboSwipeActivity;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.StaticStr;
import com.yjbo.yjboandroidmodule.util.view.DividerItemDecorationHx;
import com.yjbo.yjboandroidmodule.view.BaseKWActivity;
import com.yjbo.yjboandroidmodule.view.NavigateActivity;
import com.yjbo.yjboandroidmodule.view.ShowHttpListActivity;
import com.yjbo.yjboandroidmodule.view.StudyKWActivity;
import com.yjbo.yjboandroidmodule.view.Webview3Activity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicMainActivity extends BaseYjboSwipeActivity implements OnRefreshListener, OnLoadMoreListener {
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    ListAdapter listAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pic_main);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
        initSwipeLayout();
    }

    @Override
    public void setonData() {
        listAdapter = new ListAdapter();
        list = StaticStr.getListPicMain();
        listAdapter.bindData(list, PicMainActivity.this);
        swipeTarget.setAdapter(listAdapter);
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
                    case 0:// "glide图片加载框架"
                        startClass(ShowPicListActivity.class, position);
                        break;
                    case 1://"Picasso图片加载框架"
                        startClass(ShowPicListActivity.class, position);
                        break;
                    case 2://"Universal-Image-Loader图片加载框架"
                        startActivity(new Intent(PicMainActivity.this, Webview3Activity.class)
                                .putExtra("titleName", "Universal-Image-Loader的github地址")
                                .putExtra("ipTopStr", "https://github.com/nostra13/Android-Universal-Image-Loader"));
                        break;
                }
            }
        });
    }
    private void initSwipeLayout() {
        View hearload = LayoutInflater.from(this).inflate(R.layout.swipe_google_header, swipeToLoadLayout, false);
        View footload = LayoutInflater.from(this).inflate(R.layout.swipe_classic_footer, swipeToLoadLayout, false);
        swipeToLoadLayout.setRefreshHeaderView(hearload);
        swipeToLoadLayout.setLoadMoreFooterView(footload);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        // 设置item动画
        swipeTarget.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        swipeTarget.setLayoutManager(layoutManager);

        //添加list的分割线
        swipeTarget.addItemDecoration(new DividerItemDecorationHx(getApplicationContext(), DividerItemDecorationHx.VERTICAL_LIST));

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

    }
    private void startClass(Class<?> cls, int pos) {
        String titleName = list.get(pos);
        startActivity(new Intent(PicMainActivity.this, cls).putExtra("titleName", titleName).putExtra("pos", pos));
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
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

}
