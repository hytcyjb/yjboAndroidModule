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
 * A fragment with a Google +1 button.
 */
public class SecondFragment extends Fragment {

    @Bind(R.id.second_txt)
    TextView secondTxt;
    Handler mhandler = new Handler();
    public SecondFragment() {
       System.out.print("是干嘛的啊，初始化");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        ButterKnife.bind(this, view);
        secondTxt.setText("正在加载中....");
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                secondTxt.setText("secondTxt加载完成");
            }
        },3000);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
