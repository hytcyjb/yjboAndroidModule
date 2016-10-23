package com.yjbo.yjboandroidmodule.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.TopNewAdapter;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.fragment.TopNewItemFragment;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TopNewActivity extends BaseYjboActivity {
    private final List<String> mHeroes = new ArrayList<>();
    Activity mactivity;
    //    @Bind(R.id.home_tabLayout)
//    PagerSlidingAdd home_tabLayout;
    @Bind(R.id.home_viewpager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout mTabs;
    private FragmentManager fragmentManager;
    List<TopNewItemFragment> listHome5Fra = new ArrayList<>();

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_top_new);
        ButterKnife.bind(this);
    }

    @Override
    public void setonView() {
/***
 *
 *    viewpager每次切换的时候， 会重新创建当前界面及左右界面三个界面， 每次切换都要重新oncreate,
 *    所以只要设置viewPager setOffscreenPageLimit即可避免这个问题。
 *    viewPager.setOffscreenPageLimit(3);表示三个界面之间来回切换都不会重新加载
 */
//        viewPager.setOffscreenPageLimit(3);

        init();
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            mHeroes.add("页面" + i);
        }
        fragmentManager = getSupportFragmentManager();
        List<TopNewItemFragment> home5Fragments = setListFragment(mHeroes);

        viewPager.setAdapter(new TopNewAdapter(fragmentManager, mHeroes,home5Fragments));

        mTabs.setupWithViewPager(viewPager);

        CommonUtil.dynamicSetTabLayoutMode(mTabs);
        viewPager.setCurrentItem(0, false);
        //设置tabayout和viewpager相关联
//        home_tabLayout.setViewPager(viewPager);
//        home_tabLayout.setSmoothScrollingEnabled(true);
//        home_tabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    private List<TopNewItemFragment> setListFragment(List<String> mHeroes) {
        listHome5Fra.clear();
        for (int i =0;i<mHeroes.size();i++) {
            TopNewItemFragment newsListFragment = newInstance(mHeroes.get(i),i+"");
            listHome5Fra.add(newsListFragment);
        }
        return  listHome5Fra;
    }


    @Override
    public void setonData() {

    }

    public TopNewItemFragment newInstance(String mNodeId, String PosId) {
        TopNewItemFragment homePageChildFragment = new TopNewItemFragment();
        String nodeId = mNodeId;
        Bundle bundle = new Bundle();
        bundle.putString("mNodeId", nodeId);
        bundle.putString("mPosId", PosId);
        homePageChildFragment.setArguments(bundle);
//        L.i("newInstance" + nodeId);
        return homePageChildFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
