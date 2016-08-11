package com.yjbo.yjboandroidmodule.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.HttpListAdapter;
import com.yjbo.yjboandroidmodule.adapter.ListAdapter;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.db.HttpsDBManager;
import com.yjbo.yjboandroidmodule.entity.HttpUrlClass;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.view.DividerItemDecorationHx;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/****
 * 将数据库中的数据显示出来
 * 2016年8月11日16:23:31
 *
 * @author yjbo
 * @qq：1457521527
 */
public class ShowHttpListActivity extends BaseYjboActivity implements OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    HttpListAdapter listAdapter;
    @Bind(R.id.top_btn)
    Button topBtn;
    private List<String> list = new ArrayList<>();
    HttpsDBManager httpsDBManager = null;

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_show_http_list);

    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
        setSGTitleStr("缓存http网页列表");
        initSwipeLayout();
        setSGNextStr("缓存列表");
        setSGNextColor(R.color.red);
        httpsDBManager = new HttpsDBManager(this);
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void initSwipeLayout() {
        View hearload = LayoutInflater.from(this).inflate(R.layout.swipe_google_header, swipeToLoadLayout, false);
        View footload = LayoutInflater.from(this).inflate(R.layout.swipe_classic_footer, swipeToLoadLayout, false);
        swipeToLoadLayout.setRefreshHeaderView(hearload);
        swipeToLoadLayout.setLoadMoreFooterView(footload);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        // 设置item动画
        swipeTarget.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        swipeTarget.setLayoutManager(layoutManager);

        //添加list的分割线
        swipeTarget.addItemDecoration(new DividerItemDecorationHx(getApplicationContext(), DividerItemDecorationHx.VERTICAL_LIST));

        swipeToLoadLayout.setRefreshEnabled(true);
        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int spanCount = CommonUtil.getSpanCount(recyclerView);
                    int childCount = recyclerView.getAdapter().getItemCount();
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
        swipeTarget.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });
        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    /***
     * 因为回调了这个，不然点击事件会继续使用之前的点击事件
     * //防止退出之后还对别的点击事件有影响---listAdapter.SetonDialogChoose(null); 这个方法都是错误的，因为这样之后，别的用到该接口就报空指针
     * <p/>
     * 所以在onStart方法里面写，第一次调用，onrestart之后还调用，就可以了
     * 2016年8月2日18:32:17
     */
    @Override
    protected void onStart() {
        super.onStart();

        listAdapter.SetonDialogChoose(new HttpListAdapter.DialogChoose() {
            @Override
            public void pos(int position) {
                if (CommonUtil.isFastClick()) {
                    return;
                }
                startClass(Webview3Activity.class, position);
            }
        });
    }

    private void startClass(Class<?> cls, int pos) {
        String ipTopStr = list.get(pos);
        startActivity(new Intent(ShowHttpListActivity.this, cls).putExtra("ipTopStr", ipTopStr));
    }

    @Override
    public void setonData() {
        listAdapter = new HttpListAdapter();
        list = queryHttpsDb();
        listAdapter.bindData(list, ShowHttpListActivity.this);
        swipeTarget.setAdapter(listAdapter);
    }

    private List<String> queryHttpsDb() {
        List<String> listdb = new ArrayList<>();
        List<HttpUrlClass> listdb2 = new ArrayList<>();
        listdb2 = httpsDBManager.query();
        for (int i = 0; i < listdb2.size(); i++) {
            listdb.add(listdb2.get(i).getHttp_url());
        }
        return listdb;
    }

    @Override
    public void onLoadMore() {
        loadover();
    }

    @Override
    public void onRefresh() {
        loadover();
    }

    //刷新结束
    private void loadover() {
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @OnClick({R.id.next_public_txt, R.id.top_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_public_txt:
                Intent minten = new Intent(ShowHttpListActivity.this, WebViewActivity.class);
                minten.putExtra("url", "https://github.com/hytcyjb/yjboAndroidModule");
                minten.putExtra("titleStr", "github主页");
                startActivity(minten);
                break;
            case R.id.top_btn:

                break;
        }
    }


}
