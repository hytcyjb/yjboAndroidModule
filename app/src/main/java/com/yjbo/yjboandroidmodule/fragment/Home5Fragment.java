package com.yjbo.yjboandroidmodule.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yjbo.mvp.PictureActivity.adapter.ImageListAdapter;
import com.yjbo.mvp.PictureActivity.views.PicDetialActivity;
import com.yjbo.mvp.PictureActivity.views.ShowPicListActivity;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.ListAdapter;
import com.yjbo.yjboandroidmodule.entity.CharacterClass;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.KProgressDialog;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.StaticStr;
import com.yjbo.yjboandroidmodule.util.WeakHandler;
import com.yjbo.yjboandroidmodule.util.view.DividerItemDecorationHx;
import com.yjbo.yjboandroidmodule.view.Webview3Activity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 图片标签，新闻
 * 2016年9月3日09:57:043
 *
 * @author yjbo
 */
public class Home5Fragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    ImageListAdapter listAdapter;
    private List<String> list = new ArrayList<>();

    Activity mactivity;
    String mNodeId = "";
    String mPosId = "";
    WeakHandler weakHandler = new WeakHandler();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mactivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static Home5Fragment newInstance(String mNodeId,String PosId) {
        Home5Fragment homePageChildFragment = new Home5Fragment();
        String nodeId = mNodeId;
        Bundle bundle = new Bundle();
        bundle.putString("mNodeId", nodeId);
        bundle.putString("mPosId", PosId);
        homePageChildFragment.setArguments(bundle);
        L.i("newInstance" + nodeId);
        return homePageChildFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_5_page, container, false);
        ButterKnife.bind(this, view);
        mNodeId = getArguments().getString("mNodeId");
        mPosId = getArguments().getString("mPosId");
//        KProgressDialog.create(mactivity);
//        KProgressDialog.show("正在加载..." + mNodeId);
        L.i("onCreateView" + mNodeId);
//        init();
        initSwipeLayout();
        setonData();
        return view;
    }

    private void init() {
//        weakHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                KProgressDialog.dismiss();
//                childText.setText(mNodeId);
//            }
//        },1000);
    }

    public void setonData() {
        listAdapter = new ImageListAdapter(mactivity);
        KProgressDialog.create(getActivity());
        KProgressDialog.show(mPosId+"正在加载中...");
        weakHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list = StaticStr.getListPic();
                listAdapter.bindData(list,  1,mPosId);
                swipeTarget.setAdapter(listAdapter);
                KProgressDialog.dismiss();
            }
        },5000);
    }

    @Override
    public void onStart() {
        super.onStart();
        listAdapter.SetonDialogChoose(new ImageListAdapter.DialogChoose() {
            @Override
            public void pos(int position) {
                if (CommonUtil.isFastClick()) {
                    return;
                }
                CommonUtil.show(mactivity,"---"+position+"==当前页面=="+mNodeId);
            }
        });
    }

    private void initSwipeLayout() {
        View hearload = LayoutInflater.from(mactivity).inflate(R.layout.swipe_google_header, swipeToLoadLayout, false);
        View footload = LayoutInflater.from(mactivity).inflate(R.layout.swipe_classic_footer, swipeToLoadLayout, false);
        swipeToLoadLayout.setRefreshHeaderView(hearload);
        swipeToLoadLayout.setLoadMoreFooterView(footload);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        // 设置item动画
        swipeTarget.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mactivity);

        swipeTarget.setLayoutManager(layoutManager);

        //添加list的分割线
        swipeTarget.addItemDecoration(new DividerItemDecorationHx(mactivity, DividerItemDecorationHx.VERTICAL_LIST));

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
        startActivity(new Intent(mactivity, cls).putExtra("titleName", titleName).putExtra("pos", pos));
        mactivity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPagerAdapter.start();
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
    public void onPause() {
        super.onPause();
        L.i("onPause" + mNodeId);
//        mPagerAdapter.stop();
    }

    /***
     * 这里可以被外部引用。改变值
     */
    public void changeNum() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        KProgressDialog.dismiss();
        weakHandler.removeCallbacksAndMessages(null);
        L.i("onDestroyView" + mNodeId);
    }
}
