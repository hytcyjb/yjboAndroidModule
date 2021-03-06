package com.yjbo.yjboandroidmodule.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.NewHomepagerAdapter;
import com.yjbo.yjboandroidmodule.util.NoPreloadViewPager;
import com.yjbo.yjboandroidmodule.util.PagerSlidingAdd;
import com.yjbo.yjboandroidmodule.util.PagerSlidingFirst;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 图片标签，新闻
 * 普通的预加载 viewPager.setOffscreenPageLimit(0);
 * 2016年9月3日09:57:043
 *
 * @author yjbo
 */
public class HomePicAddFragment extends Fragment {

    private final List<String> mHeroes = new ArrayList<>();
    Activity mactivity;
    @Bind(R.id.home_tabLayout)
    PagerSlidingAdd home_tabLayout;
    @Bind(R.id.home_viewpager)
    ViewPager viewPager;
    private FragmentManager fragmentManager;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mactivity = activity;
    }

    public HomePicAddFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picadd_page, container, false);
        ButterKnife.bind(this, view);
        /***
         *
         *    viewpager每次切换的时候， 会重新创建当前界面及左右界面三个界面， 每次切换都要重新oncreate,
         *    所以只要设置viewPager setOffscreenPageLimit即可避免这个问题。
         *    viewPager.setOffscreenPageLimit(3);表示三个界面之间来回切换都不会重新加载
         */
        viewPager.setOffscreenPageLimit(3);
        fragmentManager = getFragmentManager();
        init();
        return view;
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            mHeroes.add("页面" + i);
        }

        viewPager.setAdapter(new NewHomepagerAdapter(fragmentManager, mHeroes));
        //设置tabayout和viewpager相关联
        home_tabLayout.setViewPager(viewPager);
        home_tabLayout.setSmoothScrollingEnabled(true);
        home_tabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
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
    }
}
