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
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ThridFragment extends Fragment {

    public ThridFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        return view;
    }


}
