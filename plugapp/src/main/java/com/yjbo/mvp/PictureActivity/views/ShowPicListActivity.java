package com.yjbo.mvp.PictureActivity.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yjbo.mvp.PictureActivity.adapter.ImageListAdapter;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboSwipeActivity;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.StaticStr;
import com.yjbo.yjboandroidmodule.util.view.DividerItemDecorationHx;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * glide 引用：http://www.jianshu.com/p/31c82862ef19
 * picasso 引用：http://blog.csdn.net/ashqal/article/details/48005833
 * Universal-Image-Loader 引用：http://blog.csdn.net/androidxiaogang/article/details/49304621
 * <p/>
 * 显示加载图片的列表
 * 2016年8月17日18:47:26
 *
 * @author yjbo
 */
public class ShowPicListActivity extends BaseYjboSwipeActivity implements OnRefreshListener, OnLoadMoreListener {
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    ImageListAdapter listAdapter;
    private List<String> list = new ArrayList<>();
    private int kind = 0;

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_show_pic_list);
        Intent intent = this.getIntent();
        kind = intent.getIntExtra("pos", 0);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
        initSwipeLayout();
        setSGNextStr("···");
        setSGNextColor(R.color.white);
    }

    @Override
    public void setonData() {
        listAdapter = new ImageListAdapter(ShowPicListActivity.this);
        list = StaticStr.getListPic();
        listAdapter.bindData(list,  kind,"");
        swipeTarget.setAdapter(listAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        listAdapter.SetonDialogChoose(new ImageListAdapter.DialogChoose() {
            @Override
            public void pos(int position) {
                if (CommonUtil.isFastClick()) {
                    return;
                }
                startClass(PicDetialActivity.class,position);
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
        startActivity(new Intent(ShowPicListActivity.this, cls).putExtra("content", titleName));
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


    @OnClick({R.id.next_public_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_public_txt:
                CommonUtil.show(ShowPicListActivity.this, "点击了");
                break;
        }
    }
}

