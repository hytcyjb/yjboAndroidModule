package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
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
public class SlidFragmentActivity extends BaseYjboSwipeActivity {

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    private MyPagerAdapter adapter;
    List<Fragment> listFrag = new ArrayList<Fragment>();

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_slid_fragment);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
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
