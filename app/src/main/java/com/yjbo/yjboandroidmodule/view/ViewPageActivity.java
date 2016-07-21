package com.yjbo.yjboandroidmodule.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.TestAdapter;
import com.yjbo.yjboandroidmodule.base.BaseYjboSwipeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * 测试横向滑动Fragment和侧滑同时使用的冲突问题---这个好像不冲突
 * @author yjbo
 * 2016年7月21日12:04:38
 */
public class ViewPageActivity extends BaseYjboSwipeActivity {

    @Bind(R.id.viewpager_demo)
    ViewPager viewpagerDemo;

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_page);
        ButterKnife.bind(this);
    }

    @Override
    public void setonView() {
        TestAdapter adapter = new TestAdapter(getSupportFragmentManager());
        viewpagerDemo.setAdapter(adapter);
    }

    @Override
    public void setonData() {

    }

}
