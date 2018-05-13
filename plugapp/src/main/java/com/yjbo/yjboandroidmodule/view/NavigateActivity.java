package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.fragment.Home2Fragment;
import com.yjbo.yjboandroidmodule.fragment.Home3Fragment;
import com.yjbo.yjboandroidmodule.fragment.Home4Fragment;
import com.yjbo.yjboandroidmodule.fragment.Home5Fragment;
import com.yjbo.yjboandroidmodule.fragment.HomePageFragment;
import com.yjbo.yjboandroidmodule.fragment.HomePicAddFragment;
import com.yjbo.yjboandroidmodule.fragment.HomePicFlagFragment;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.picture.AvatarImageView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/***
 * <p/>
 * 如何引用navigationview_header布局内的id控件
 * View drawview = navigation.inflateHeaderView(R.layout.navigationview_header);
 * myToppic = (AvatarImageView) drawview.findViewById(R.id.my_toppic);
 * </P>
 * 引用自：http://blog.csdn.net/amazing7/article/details/52035324
 * 2016年7月27日15:41:30
 *
 * @author yjbo
 */
public class NavigateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.framelayout_main)
    FrameLayout framelayoutMain;

    AvatarImageView myToppic;
    TextView git_name_txt;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        ButterKnife.bind(this);
        View drawview = navigation.inflateHeaderView(R.layout.navigationview_header);
        myToppic = (AvatarImageView) drawview.findViewById(R.id.my_toppic);
        git_name_txt = (TextView) drawview.findViewById(R.id.git_name_txt);

        RxView.clicks(git_name_txt)
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        Intent minten = new Intent(NavigateActivity.this, WebViewActivity.class);
                        minten.putExtra("url", "https://github.com/hytcyjb/yjboAndroidModule");
                        minten.putExtra("titleStr", "github主页");
                        startActivity(minten);
                        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);

                    }
                });
        initData();
        toolBar.setTitle("使用了侧滑的布局");
        toolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolBar.setTitle("菜单");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                toolBar.setTitle("使用了侧滑的布局");
            }
        };
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            navigation.setCheckedItem(R.id.drawer_home);
            showNavigationFragment(R.id.drawer_home);
        }
        navigation.setNavigationItemSelectedListener(this);
    }

    private void initData() {
        myToppic.setAfterCropListener(new AvatarImageView.AfterCropListener() {
            @Override
            public void afterCrop(Bitmap photo) {
                CommonUtil.show(NavigateActivity.this, "未将图片保存起来，下次读取图片将显示默认图片");
                myToppic.setImageBitmap(photo);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        changeMenuBg(item);
        showNavigationFragment(item.getItemId());
        return false;
    }

    /***
     * 选中什么，点亮什么
     *
     * @param item
     */
    private void changeMenuBg(MenuItem item) {
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            navigation.getMenu().getItem(i).setChecked(false);
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }

    int lastNavItemId = -1;

    void showNavigationFragment(int itemId) {

        if (itemId == R.id.drawer_settings) {
            Intent minten = new Intent(NavigateActivity.this, WebViewActivity.class);
            minten.putExtra("url", "https://github.com/hytcyjb/yjboAndroidModule");
            minten.putExtra("titleStr", "github主页");
            startActivity(minten);
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment lastFragment = fm.findFragmentByTag(getTag(lastNavItemId));
        lastNavItemId = itemId;
        if (lastFragment != null) {
            ft.detach(lastFragment);
        }
        Fragment fragment = fm.findFragmentByTag(getTag(itemId));
        changeTop(itemId);
        if (fragment == null) {
            fragment = getItem(itemId);
            ft.add(R.id.framelayout_main, fragment, getTag(itemId));
        } else {
            ft.attach(fragment);
        }
        ft.commit();
    }


    private String getTag(int itemId) {
        return String.valueOf(itemId);
    }

    private Fragment getItem(int itemId) {
        Fragment navigationFragment = new HomePageFragment();
        switch (itemId) {
            case R.id.drawer_home:
                navigationFragment = new Home3Fragment();
                break;
            case R.id.drawer_downloaded:
                navigationFragment = new Home4Fragment();
                break;
            case R.id.drawer_pic_frag:
                navigationFragment = new HomePicFlagFragment();
                break;
            case R.id.drawer_pic_add:
                navigationFragment = new HomePicAddFragment();
                break;
        }
        return navigationFragment;
    }

    /***
     * 改变顶部标题
     *
     * @param itemId
     */
    public void changeTop(int itemId) {
        String string = "";
        switch (itemId) {
            case R.id.drawer_home:
                string = "轮播图";
                break;
            case R.id.drawer_downloaded:
                string = "广告标题：Home4Fragment";
                break;
            case R.id.drawer_pic_frag:
                string = "图片标签(不加载下一页)";
                break;
            case R.id.drawer_pic_add:
                string = "图片标签(加载下一页)";
                break;
            case R.id.drawer_settings:
                string = "github主页";
                break;
        }
        if (toolBar != null) {
            toolBar.setTitle(string);
        }
    }

    //接收当前位置和目标位置的返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.i("--onActivityResult" + requestCode + "--" + resultCode + "---");
        //在拍照、选取照片、裁剪Activity结束后，调用的方法
        if (myToppic != null) {
            myToppic.onActivityResult(requestCode, resultCode, data);
        }
    }
}
