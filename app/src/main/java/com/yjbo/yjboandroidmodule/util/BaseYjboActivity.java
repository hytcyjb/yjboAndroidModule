package com.yjbo.yjboandroidmodule.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 基类
 * Created by yjbo on 2016/6/6.
 */
public abstract class BaseYjboActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        getSupportActionBar().hide();
    }

    public abstract void setContentView();
}
