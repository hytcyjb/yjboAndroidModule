package com.yjbo.yjboandroidmodule.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yjbo.yjboandroidmodule.fragment.Home5Fragment;

import java.util.List;

/**
 * 新首页设置tablayout与viewpager相结合的适配器
 * Created by Coder-djw on 2016/7/13.
 */
public class NewHomepagerAdapter extends FragmentPagerAdapter {

    List<String> stringList;

    public NewHomepagerAdapter(FragmentManager fm, List<String> stringList) {
        super(fm);
        this.stringList = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return Home5Fragment.newInstance(stringList.get(position),position+"");
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
