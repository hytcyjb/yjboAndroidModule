package com.yjbo.yjboandroidmodule.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.entity.CharacterClass;
import com.yjbo.yjboandroidmodule.util.KProgressDialog;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.WeakHandler;

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
public class Home5Fragment extends Fragment {

    //    @Bind(R.id.viewPager)
//    ViewPager viewPager;
//    @Bind(R.id.indicators)
//    LinearLayout indicators;
//    private RecyclerView recyclerView;
//    LoopViewPagerAdapter mPagerAdapter;
    private final List<CharacterClass> mHeroes = new ArrayList<>();
    Activity mactivity;
    String mNodeId = "";
    @Bind(R.id.child_text)
    TextView childText;
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

    public static Home5Fragment newInstance(String mNodeId) {
        Home5Fragment homePageChildFragment = new Home5Fragment();
        String nodeId = mNodeId;
        Bundle bundle = new Bundle();
        bundle.putString("mNodeId", nodeId);
        homePageChildFragment.setArguments(bundle);
        L.i("newInstance"+nodeId);
        return homePageChildFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_5_page, container, false);
        ButterKnife.bind(this, view);
        mNodeId = getArguments().getString("mNodeId");
//        KProgressDialog.create(mactivity);
//        KProgressDialog.show("正在加载..." + mNodeId);
        L.i("onCreateView"+ mNodeId);
        init();
        return view;
    }

    private void init() {
        weakHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                KProgressDialog.dismiss();
                childText.setText(mNodeId);
            }
        },1000);
    }

//    private void init() {
//        mPagerAdapter = new LoopViewPagerAdapter(viewPager, indicators);
//        viewPager.setAdapter(mPagerAdapter);
//
//        viewPager.addOnPageChangeListener(mPagerAdapter);
//        //全局设置viewpage的背景色
////        viewPager.setBackgroundDrawable(getResources().getDrawable(R.mipmap.bg_viewpager));
//
//        CharacterClass characterClass = new CharacterClass();
//        characterClass.setAvatar(R.drawable.roll_viewpage1);
//        characterClass.setName("1");
//        mHeroes.add(characterClass);
//
//        characterClass = new CharacterClass();
//        characterClass.setAvatar(R.drawable.roll_viewpage2);
//        characterClass.setName("2");
//        mHeroes.add(characterClass);
//
//        characterClass = new CharacterClass();
//        characterClass.setAvatar(R.drawable.roll_viewpage3);
//        characterClass.setName("3");
//        mHeroes.add(characterClass);
//
//        characterClass = new CharacterClass();
//        characterClass.setAvatar(R.drawable.roll_viewpage4);
//        characterClass.setName("4");
//        mHeroes.add(characterClass);
//
//        characterClass = new CharacterClass();
//        characterClass.setAvatar(R.drawable.roll_viewpage5);
//        characterClass.setName("5");
//        mHeroes.add(characterClass);
//
//        mPagerAdapter.setList(mactivity, mHeroes);
//    }

    @Override
    public void onResume() {
        super.onResume();
//        mPagerAdapter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        L.i("onPause"+ mNodeId);
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
