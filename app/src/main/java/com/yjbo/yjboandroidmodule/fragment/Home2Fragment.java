package com.yjbo.yjboandroidmodule.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 展示轮播图
 * 2016年7月27日16:56:28
 * @author yjbo
 */
public class Home2Fragment extends Fragment {

    public Home2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2_page, container, false);
        ButterKnife.bind(this, view);
        return view;
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
