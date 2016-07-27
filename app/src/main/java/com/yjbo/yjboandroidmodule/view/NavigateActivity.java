package com.yjbo.yjboandroidmodule.view;

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

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.fragment.Home2Fragment;
import com.yjbo.yjboandroidmodule.fragment.Home3Fragment;
import com.yjbo.yjboandroidmodule.fragment.HomePageFragment;
import com.yjbo.yjboandroidmodule.util.CommonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
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

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        ButterKnife.bind(this);

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
                toolBar.setTitle("使用了侧滑的布局");
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        changeMenuBg(item);
        showNavigationFragment(item.getItemId());
        return false;
    }

    private void changeMenuBg(MenuItem item) {
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            navigation.getMenu().getItem(i).setChecked(false);
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }

    int lastNavItemId = -1;

    void showNavigationFragment(int itemId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment lastFragment = fm.findFragmentByTag(getTag(lastNavItemId));
        lastNavItemId = itemId;
        if (lastFragment != null) {
            ft.detach(lastFragment);
        }
        Fragment fragment = fm.findFragmentByTag(getTag(itemId));
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
                CommonUtil.show(NavigateActivity.this, "--drawer_home--");
                navigationFragment = new Home3Fragment();
                break;
            case R.id.drawer_favourite:
                CommonUtil.show(NavigateActivity.this, "--drawer_favourite--");
                navigationFragment = new Home2Fragment();
                break;
            case R.id.drawer_downloaded:
                CommonUtil.show(NavigateActivity.this, "--drawer_downloaded--");
                navigationFragment = new Home3Fragment();
                break;
            case R.id.drawer_more:
                CommonUtil.show(NavigateActivity.this, "--drawer_more--");
                navigationFragment = new HomePageFragment();
                break;
            case R.id.drawer_settings:
                CommonUtil.show(NavigateActivity.this, "--drawer_settings--");
                navigationFragment = new HomePageFragment();
                break;
        }
        return navigationFragment;
    }
}
