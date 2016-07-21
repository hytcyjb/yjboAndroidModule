package com.yjbo.yjboandroidmodule.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 参考：
 * https://github.com/GeniusVJR/Good-Android-development-habits/blob/master/StandardDemo/app/src/main/java/com/geniusvjr/standarddemo/base/BaseActivity.java
 * Created by dream on 16/6/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        setContentView();
        findViews();
        getData();
        showContent();
    }

    protected abstract void setContentView();

    protected abstract void findViews();

    protected abstract void getData();

    protected abstract void showContent();
}