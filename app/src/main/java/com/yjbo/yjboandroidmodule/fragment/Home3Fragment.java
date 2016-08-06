package com.yjbo.yjboandroidmodule.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.LoopViewPagerAdapter;
import com.yjbo.yjboandroidmodule.entity.CharacterClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 展示轮播图
 * 2016年7月27日16:56:28
 *
 * @author yjbo
 */
public class Home3Fragment extends Fragment {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicators)
    LinearLayout indicators;
    private RecyclerView recyclerView;
    LoopViewPagerAdapter mPagerAdapter;
    private final List<CharacterClass> mHeroes = new ArrayList<>();
    Activity mactivity;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mactivity = activity;
    }

    public Home3Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3_page, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mPagerAdapter = new LoopViewPagerAdapter(viewPager, indicators);
        viewPager.setAdapter(mPagerAdapter);

        viewPager.addOnPageChangeListener(mPagerAdapter);
        //全局设置viewpage的背景色
//        viewPager.setBackgroundDrawable(getResources().getDrawable(R.mipmap.bg_viewpager));

        CharacterClass characterClass = new CharacterClass();
        characterClass.setAvatar(R.drawable.roll_viewpage1);
        characterClass.setName("1");
        mHeroes.add(characterClass);

        characterClass = new CharacterClass();
        characterClass.setAvatar(R.drawable.roll_viewpage2);
        characterClass.setName("2");
        mHeroes.add(characterClass);

        characterClass = new CharacterClass();
        characterClass.setAvatar(R.drawable.roll_viewpage3);
        characterClass.setName("3");
        mHeroes.add(characterClass);

        characterClass = new CharacterClass();
        characterClass.setAvatar(R.drawable.roll_viewpage4);
        characterClass.setName("4");
        mHeroes.add(characterClass);

        characterClass = new CharacterClass();
        characterClass.setAvatar(R.drawable.roll_viewpage5);
        characterClass.setName("5");
        mHeroes.add(characterClass);

        mPagerAdapter.setList(mactivity,mHeroes);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPagerAdapter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPagerAdapter.stop();
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
