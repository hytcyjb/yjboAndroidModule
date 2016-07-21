package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboSwipeActivity;
import com.yjbo.yjboandroidmodule.fragment.FirstFragment;
import com.yjbo.yjboandroidmodule.fragment.SecondFragment;
import com.yjbo.yjboandroidmodule.fragment.ThridFragment;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 横向滑动的顶部fragment
 * 引用自：//横向滑动的布局
 * compile 'com.jpardogo.materialtabstrip:library:1.1.0'
 * 地址： https://github.com/hytcyjb/PagerSlidingTabStrip
 *
 * @yjbo 2016年6月16日18:15:30
 */
public class SlidFragmentActivity extends BaseYjboSwipeActivity implements OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    private MyPagerAdapter adapter;
    List<Fragment> listFrag = new ArrayList<Fragment>();
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    @Bind(R.id.swipe_target)
    LinearLayout expressageList;
    View view = null, view1 = null;
    LayoutInflater mInflater;
    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_slid_fragment);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
        initSwipeLayout();
    }

    private void initSwipeLayout() {
        mInflater = LayoutInflater.from(this);
        view = mInflater.inflate(R.layout.swipe_classic_footer, swipeToLoadLayout, false);
        view1 = mInflater.inflate(R.layout.swipe_google_header, swipeToLoadLayout, false);
        if (view1 != null) {
            swipeToLoadLayout.setRefreshHeaderView(view1);
        }
        if (view != null) {
            swipeToLoadLayout.setLoadMoreFooterView(view);
        }
        //初始化的时候运行一下，
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }
    @Override
    public void onLoadMore() {
        CommonUtil.show(SlidFragmentActivity.this,"加载更多成功\n和Fragment内部的scrollview冲突");
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {
        CommonUtil.show(SlidFragmentActivity.this,"刷新成功\n和Fragment内部的scrollview冲突");
        swipeToLoadLayout.setRefreshing(false);
    }
    @Override
    public void setonData() {
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThridFragment thridFragment = new ThridFragment();
        listFrag.add(firstFragment);
        listFrag.add(secondFragment);
        listFrag.add(thridFragment);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(0);
        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                Toast.makeText(SlidFragmentActivity.this, "Tab reselected: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        setSGNextStr("官网说明");
    }

    @OnClick({R.id.next_public_txt, R.id.tabs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_public_txt:
                CommonUtil.show(SlidFragmentActivity.this,"引用自：//横向滑动的布局 compile 'com.jpardogo.materialtabstrip:library:1.1.0'");
                Intent minten = new Intent(SlidFragmentActivity.this,WebViewActivity.class);
                minten.putExtra("url","https://github.com/hytcyjb/PagerSlidingTabStrip");
                minten.putExtra("titleStr","横向滑动github下载");
                startActivity(minten);
                break;
            case R.id.tabs:
                break;
        }
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"首页", "第二页", "第三页"};

        //        , "Top Free", "Top Grossing", "Top New Paid",
//                "Top New Free", "Trending"
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {

            return listFrag.get(position);
        }
    }
}
