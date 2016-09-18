package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.yjbo.mvp.PictureActivity.views.PicMainActivity;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.ListAdapter;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.entity.MainMessage;
import com.yjbo.yjboandroidmodule.test.testActivity;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.StaticStr;
import com.yjbo.yjboandroidmodule.util.video.TakeVideoActivity;
import com.yjbo.yjboandroidmodule.util.view.DividerItemDecorationHx;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/***
 * 学习知识
 * 2016年8月2日17:57:02
 *
 * @author yjbo
 */
public class StudyKWActivity extends BaseYjboActivity implements OnRefreshListener, OnLoadMoreListener,SwipeTrigger {

    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    ListAdapter listAdapter;
    private List<String> list = new ArrayList<>();
    private List<String> listAdd = new ArrayList<>();

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_study_kw);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
        setSGBackStr("主页");
        initSwipeLayout();

//        setSGNextStr("···");
//        setSGNextColor(R.color.white);
    }

    //主线程中执行
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMainEventBus(String msg) {
        L.d("onEventBus() returned: " + Thread.currentThread()+"==msg=="+msg);
        if ("0".equals(msg)) {
            listAdapter.addData(0, "添加字符1");
        }else if ("1".equals(msg)){
            listAdd.clear();
            listAdd.add("新增数组0");
            listAdd.add("新增数组1");
            listAdd.add("新增数组2");
            listAdapter.addData(listAdd);
        }
    }
    private void initSwipeLayout() {
        View hearload = LayoutInflater.from(this).inflate(R.layout.swipe_google_header, swipeToLoadLayout, false);
        View footload = LayoutInflater.from(this).inflate(R.layout.swipe_classic_footer, swipeToLoadLayout, false);
        swipeToLoadLayout.setRefreshHeaderView(hearload);
        swipeToLoadLayout.setLoadMoreFooterView(footload);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //添加list的分割线
        swipeTarget.addItemDecoration(new DividerItemDecorationHx(getApplicationContext(), DividerItemDecorationHx.VERTICAL_LIST));
        swipeTarget.setLayoutManager(layoutManager);
        swipeToLoadLayout.setRefreshEnabled(true);
        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        listAdapter.SetonDialogChoose(new ListAdapter.DialogChoose() {
            @Override
            public void pos(int position) {
                if (CommonUtil.isFastClick()) {
                    return;
                }
                switch (position) {
                    case 0://横向滑动fragment
                        startClass(SlidFragmentActivity.class, position);
                        break;
                    case 1://handler的内存泄露处理https://github.com/badoo/android-weak-handler/
                        startClass(HandlerOomActivity.class, position);
                        break;
                    case 2://录视频
                        startClass(TakeVideoActivity.class, position);
                        break;
                    case 3://后台录视频
                        startClass(MyCramerActivity.class, position);
                        break;
                    case 4://测试eventbus
                        startClass(EventbusActivity.class, position);
                        break;
                    case 5://事件的分发1
                        startClass(ViewGroupActivity.class, position);
                        break;
                    case 6://创建桌面快捷方式
                        startClass(ShowContentActivity.class, position);
                        break;
                    case 7://缓存网页列表
                        startClass(ShowHttpListActivity.class, position);
                        break;
                    case 8://图片加载框架
                        startClass(PicMainActivity.class, position);
                        break;
                    case 9://添加头布局
                        startClass(AddHeaderActivity.class, position);
                        break;
                    default:
                        CommonUtil.show(StudyKWActivity.this,"点击了"+position);
                        break;
                }
            }
        });
    }

    private void startClass(Class<?> cls, int pos) {
        String titleName = list.get(pos);
        startActivity(new Intent(StudyKWActivity.this, cls).putExtra("titleName", titleName));
    }


    @Override
    public void setonData() {
        listAdapter = new ListAdapter();
        list = StaticStr.getListStudyKW();
        listAdapter.bindData(list, StudyKWActivity.this);
        swipeTarget.setAdapter(listAdapter);
    }

    @Override
    public void onLoadMore() {
        loadover();
    }

    @Override
    public void onRefresh() {
        CommonUtil.show(StudyKWActivity.this,"onRefresh====");
        loadover();
    }

    //刷新结束
    private void loadover() {
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onSwipe(int i, boolean b) {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void complete() {
        CommonUtil.show(StudyKWActivity.this,"刷新完成====");
    }

    @Override
    public void onReset() {

    }

}
