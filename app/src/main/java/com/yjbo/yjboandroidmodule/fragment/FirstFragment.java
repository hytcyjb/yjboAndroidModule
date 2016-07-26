package com.yjbo.yjboandroidmodule.fragment;


import android.annotation.TargetApi;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yjbo.yjboandroidmodule.R;

/**
 * A fragment with a Google +1 button.
 */
public class FirstFragment extends Fragment {

    public FirstFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }
    /***
     * 这里可以被外部引用。改变值
     */
    public void changeNum() {

    }


}
