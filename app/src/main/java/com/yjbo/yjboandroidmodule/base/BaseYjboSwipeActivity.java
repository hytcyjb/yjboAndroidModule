package com.yjbo.yjboandroidmodule.base;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.swipeUtil.SwipeBackActivity;

/**
 * 基类，这个基类得认真弄
 * <p>
 * 一：这个类以及继承这个类的Activity类里面的运行顺序：
 * BaseYjboActivity：
 * 1.onCreate；
 * 2.setonCreate：
 * (注意：如果这里想让SouGUMainActivity类中声明的返回按钮有效，则就在2内部声明butterknife初始化，
 * 如果直接使用BaseYjboActivity类中的返回事件，则直接使用默认就好。
 * 换言之：接口回调没有作用，可以删除	)
 * 3.setonView：
 * SouGUMainActivity：(某个Activity类)
 * 4.onCreate：
 *
 * 二：如果butterknife在setonView()方法内声明了，而不是在setonCreate方法内声明，那么在某个Activity类内
 *      顶部的nextPublicTxt等点击事件都会被
 * </p>
 * Created by yjbo on 2016/6/6.
 */
public abstract class BaseYjboSwipeActivity extends SwipeBackActivity {

    protected TextView backPublicTxt;//返回键和图标
    protected TextView nextPublicTxt;//最右侧的键和图标
    protected TextView rightNextPublicTxt;//右侧旁边的键和图标
    protected TextView titlePublic;//中间的标题
    RelativeLayout publicBody;//背景条目
    BaseIntanClass souGuClass;

    // 设置当前的view布局
    public abstract void setonCreate(Bundle savedInstanceState);

    public abstract void setonView();

    public abstract void setonData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setonCreate(savedInstanceState);
//        try {
//            getSupportActionBar().hide();
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
        //默认不打开软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        souGuClass = BaseIntanClass.getInstance();
        souGuClass.addActivity(this);
        publicBody = (RelativeLayout) findViewById(R.id.public_body);
        backPublicTxt = (TextView) findViewById(R.id.back_public_txt);
        nextPublicTxt = (TextView) findViewById(R.id.next_public_txt);
        rightNextPublicTxt = (TextView) findViewById(R.id.right_next_public_txt);
        titlePublic = (TextView) findViewById(R.id.title_public);

        if (backPublicTxt != null) {
            backPublicTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        if (nextPublicTxt != null) {
            nextPublicTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//					finish();
                }
            });
        }
        if (rightNextPublicTxt != null) {
            rightNextPublicTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//					finish();
                }
            });
        }
        setonView();
        setonData();
    }

    /***
     * 设置返回按钮的各个属性
     */
    public void setSGBackStr(String txtStr) {//左侧写上Str文字
        backPublicTxt.setText(txtStr);
    }

    public void setSGBackInt(int resID) {//左侧写上int文字
        backPublicTxt.setText(getString(resID));
    }

    public void setSGBackColor(int color) {//左侧文字改变颜色
        backPublicTxt.setTextColor(getResources().getColor((color)));
    }

    public void setSGBackVisible() {//左侧所有内容隐藏
        backPublicTxt.setVisibility(View.GONE);
    }

    public void setSGBackDrawablGone() {//保留文字，隐藏左侧图标
        try {
            backPublicTxt.setCompoundDrawables(null, null, null, null);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setSGBackDrawabl(int drawInt) {//设置左侧图标
        try {
            Drawable drawable = getResources().getDrawable(drawInt);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            backPublicTxt.setCompoundDrawables(drawable, null, null, null);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * 设置标题的各种属性
     */
    public void setSGTitleStr(String txtStr) {
        titlePublic.setText(txtStr);
    }

    public void setSGTitleInt(int resID) {//左侧写上int文字
        titlePublic.setText(getString(resID));
    }

    public void setSGTitleColor(int color) {
        titlePublic.setTextColor(getResources().getColor((color)));
    }

    /***
     * 设置标题的背景颜色
     */
    public void setSGBgColor(int color) {
        publicBody.setBackgroundResource(color);
    }

    public void setSGBgColor(Drawable drawable) {
        publicBody.setBackgroundDrawable(drawable);
    }

    /***
     * 设置下一步的各个属性
     */
    public void setSGNextStr(String txtStr) {//左侧写上Str文字
        nextPublicTxt.setVisibility(View.VISIBLE);
        nextPublicTxt.setText(txtStr);
    }

    public void setSGNextInt(int resID) {//左侧写上int文字
        nextPublicTxt.setVisibility(View.VISIBLE);
        nextPublicTxt.setText(getString(resID));
    }

    public void setSGNextColor(int color) {//左侧文字改变颜色
        nextPublicTxt.setVisibility(View.VISIBLE);
        nextPublicTxt.setTextColor(getResources().getColor((color)));
    }

    public void setSGNextVisible() {//左侧所有内容隐藏
        nextPublicTxt.setVisibility(View.VISIBLE);
        nextPublicTxt.setVisibility(View.GONE);
    }

    public void setSGNextDrawablGone() {//保留文字，隐藏左侧图标
        try {
            nextPublicTxt.setVisibility(View.VISIBLE);
            nextPublicTxt.setCompoundDrawables(null, null, null, null);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setSGNextDrawabl(int drawInt) {//设置左侧图标
        try {
            nextPublicTxt.setVisibility(View.VISIBLE);
            Drawable drawable = getResources().getDrawable(drawInt);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            nextPublicTxt.setCompoundDrawables(drawable, null, null, null);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * 设置下一步左边的按钮的各个属性
     */
    public void setSGRightNextStr(String txtStr) {//左侧写上Str文字
        rightNextPublicTxt.setVisibility(View.VISIBLE);
        rightNextPublicTxt.setText(txtStr);
    }

    public void setSGRightNextInt(int resID) {//左侧写上int文字
        rightNextPublicTxt.setVisibility(View.VISIBLE);
        rightNextPublicTxt.setText(getString(resID));
    }

    public void setSGRightNextColor(int color) {//左侧文字改变颜色
        rightNextPublicTxt.setVisibility(View.VISIBLE);
        rightNextPublicTxt.setTextColor(getResources().getColor((color)));
    }

    public void setSGRightNextVisible() {//左侧所有内容隐藏
        rightNextPublicTxt.setVisibility(View.VISIBLE);
        rightNextPublicTxt.setVisibility(View.GONE);
    }

    public void setSGRightNextDrawablGone() {//保留文字，隐藏左侧图标
        try {
            rightNextPublicTxt.setVisibility(View.VISIBLE);
            rightNextPublicTxt.setCompoundDrawables(null, null, null, null);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setSGRightNextDrawabl(int drawInt) {//设置左侧图标,如果想让文字去掉，则同时设置setSGRightNextStr("");
        try {
            rightNextPublicTxt.setVisibility(View.VISIBLE);
            Drawable drawable = getResources().getDrawable(drawInt);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            rightNextPublicTxt.setCompoundDrawables(drawable, null, null, null);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //默认不打开软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
