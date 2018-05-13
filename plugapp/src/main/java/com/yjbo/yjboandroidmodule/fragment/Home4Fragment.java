package com.yjbo.yjboandroidmodule.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.CommonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展示轮播图
 * 2016年7月27日16:56:28
 *
 * @author yjbo
 */
public class Home4Fragment extends Fragment {

    @Bind(R.id.buttom_layout)
    LinearLayout buttomLayout;

    public Home4Fragment() {
    }

    Activity mactivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mactivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private long exitTime = 0;

    @OnClick({R.id.back_public_txt, R.id.top_layout, R.id.buttom_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_public_txt:
                break;
            case R.id.top_layout:
                CommonUtil.show(mactivity, "点我干嘛1");
                break;
            case R.id.buttom_layout:
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    CommonUtil.show(mactivity, "再按一次隐藏广告");
                    exitTime = System.currentTimeMillis();
                } else {
                    buttomLayout.setVisibility(View.GONE);
                }
                break;
        }
    }

}
