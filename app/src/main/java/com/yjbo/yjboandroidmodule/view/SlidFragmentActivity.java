package com.yjbo.yjboandroidmodule.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.Toast;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.fragment.FirstFragment;
import com.yjbo.yjboandroidmodule.fragment.SecondFragment;
import com.yjbo.yjboandroidmodule.fragment.ThridFragment;
import com.yjbo.yjboandroidmodule.util.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * 横向滑动的顶部fragment
 * 引用自：//横向滑动的布局
 * compile 'com.jpardogo.materialtabstrip:library:1.1.0'
 * 地址： https://github.com/hytcyjb/PagerSlidingTabStrip
 *
 * @yjbo 2016年6月16日18:15:30
 */
public class SlidFragmentActivity extends AppCompatActivity {

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    private MyPagerAdapter adapter;
    List<Fragment> listFrag = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slid_fragment);
        ButterKnife.bind(this);
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
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"    Categories    ", "    Home    ", "    Top Paid    "};
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
