package com.yjbo.yjboandroidmodule.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yjbo.yjboandroidmodule.fragment.Home5Fragment;
import com.yjbo.yjboandroidmodule.fragment.TopNewItemFragment;

import java.util.List;

/**
 * 新首页设置tablayout与viewpager相结合的适配器
 * Created by Coder-djw on 2016/7/13.
 */
public class TopNewAdapter extends FragmentPagerAdapter {

    List<String> stringList;
    List<TopNewItemFragment> mhome5Fragments;
    public TopNewAdapter(FragmentManager fm, List<String> stringList,List<TopNewItemFragment> home5Fragments) {
        super(fm);
        this.stringList = stringList;
        this.mhome5Fragments = home5Fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mhome5Fragments.get(position);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }
}
