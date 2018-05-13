package com.yjbo.yjboandroidmodule.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yjbo.yjboandroidmodule.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomePageFragment extends Fragment {
    // implements OnRefreshListener, OnLoadMoreListener
//    @Bind(R.id.swipe_target)
//    RecyclerView swipeTarget;
//    @Bind(R.id.swipeToLoadLayout)
//    SwipeToLoadLayout swipeToLoadLayout;
//    ListAdapter listAdapter;
    @Bind(R.id.top_btn)
    Button topBtn;
//    private List<String> list = new ArrayList<>();
//    private List<String> otherlist = new ArrayList<>();
    Activity mactivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mactivity = activity;
    }

    public HomePageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(mactivity);
//        initSwipeLayout();
//        initData();
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }
//    private void initSwipeLayout() {
//        View hearload = LayoutInflater.from(mactivity).inflate(R.layout.swipe_google_header, swipeToLoadLayout, false);
//        View footload = LayoutInflater.from(mactivity).inflate(R.layout.swipe_classic_footer, swipeToLoadLayout, false);
//        swipeToLoadLayout.setRefreshHeaderView(hearload);
//        swipeToLoadLayout.setLoadMoreFooterView(footload);
//        swipeToLoadLayout.setOnRefreshListener(this);
//        swipeToLoadLayout.setOnLoadMoreListener(this);
//
//        RecyclerView.LayoutManager layoutManager = null;
//        layoutManager = new LinearLayoutManager(mactivity);
//        swipeTarget.setLayoutManager(layoutManager);
//        swipeToLoadLayout.setRefreshEnabled(true);
//        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                L.i("--onScrollStateChanged的状态值--" + newState);//手指滑动的时候是1，手指离开之后就是0
////                setSGTitleStr("--onScrollSta=="+newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int spanCount = CommonUtil.getSpanCount(recyclerView);
//                    int childCount = recyclerView.getAdapter().getItemCount();
////                    setSGTitleStr("--onScrollSta==" + newState + "--" + spanCount + "===" + childCount);
////                    if (CommonUtil.isLastRaw(recyclerView, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
////                    {
////
////                    }
//                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
//                        swipeToLoadLayout.setLoadingMore(true);
//                    }
//                }
//            }
//        });
//        listAdapter.SetonDialogChoose(new ListAdapter.DialogChoose() {
//            @Override
//            public void pos(int position) {
//                if (CommonUtil.isFastClick()) {
//                    return;
//                }
//                switch (position) {
//                    case 0:
//                        startClass(WifiOpenActivity.class, position);
//                        break;
//                    case 1:
//                        startClass(ScreenDirectionActivity.class, position);
//                        break;
//                    case 2:
//                        startClass(SlidFragmentActivity.class, position);
//                        break;
//                    case 3://https://github.com/badoo/android-weak-handler/
//                        startClass(HandlerOomActivity.class, position);
//                        break;
//                    case 4:
//                        startClass(JsonActivity.class, position);
//                        break;
//                    case 5:
//                        startClass(TextViewLinkActivity.class, position);
//                        break;
//                    case 6:
//                        startClass(NotificationActivity.class, position);
//                        break;
//                    case 7:
//                        startClass(TakeVideoActivity.class, position);
//                        break;
//                    case 8:
//                        startClass(MyCramerActivity.class, position);
//                        break;
//                    case 9:
//                        startClass(EventbusActivity.class, position);
//                        break;
//                    case 10:
//                        startClass(ViewPageActivity.class, position);
//                        break;
//                    case 11:
//                        startClass(testActivity.class, position);
//                        break;
//                    case 12:
//                        startClass(ViewGroupActivity.class, position);
//                        break;
//                    case 13:
//                        startClass(ShowContentActivity.class, position);
//                        break;
//                    case 14:
//                        startClass(TestServiceActivity.class, position);
//                        break;
//                }
//            }
//        });
//    }
//
//    private void startClass(Class<?> cls, int pos) {
//        String titleName = list.get(pos);
//        startActivity(new Intent(mactivity, cls).putExtra("titleName", titleName));
//    }
//
//    public void initData() {
//        listAdapter = new ListAdapter();
//        list.add("自动打开wifi模块");
//        list.add("获取屏幕旋转角度");
//        list.add("横向滑动fragment");
//        list.add("handler的内存泄露处理");
//        list.add("固定解析json字符串");
//        list.add("textstyle的展示");
//        list.add("显示通知栏");
//        list.add("录视频");
//        list.add("后台录视频");
//        list.add("测试eventbus");
//        list.add("测试横向滑动Fragment和侧滑同时使用的冲突问题");
//        list.add("测试Activity生命周期");
//        list.add("事件的分发1");
//        list.add("创建桌面快捷方式");
//        list.add("测试Service的生命周期");
//        listAdapter.bindData(list,mactivity);
//        swipeTarget.setAdapter(listAdapter);
//    }
//
//    @Override
//    public void onLoadMore() {
////        listAdapter.removeData(3);
//        loadover();
//    }
//
//    @Override
//    public void onRefresh() {
////        listAdapter.addData(3, "你好呀，字符进来了");
//        loadover();
//    }
//
//    //刷新结束
//    private void loadover() {
//        swipeToLoadLayout.setRefreshing(false);
//        swipeToLoadLayout.setLoadingMore(false);
//    }
//
//    @OnClick({R.id.next_public_txt, R.id.top_btn, R.id.right_next_public_txt})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.next_public_txt:
//                Intent minten = new Intent(mactivity, WebViewActivity.class);
//                minten.putExtra("url", "https://github.com/hytcyjb/yjboAndroidModule");
//                minten.putExtra("titleStr", "各种模块的开发");
//                startActivity(minten);
//                break;
//            case R.id.top_btn:
//
//                break;
//            case R.id.right_next_public_txt:
//                if (otherlist.size() > 0) {
//                    if (otherlist.get(0).contains("yjbo")) {
//                        listAdapter.updateData(list);
//                        otherlist = new ArrayList<>();
//                    }
//                } else {
//                    otherlist = new ArrayList<>();
//                    for (int i = 0; i < list.size(); i++) {
//                        if (!list.get(0).contains("yjbo")) {
//                            otherlist.add(list.get(i) + "yjbo");
//                        }
//                    }
//                    listAdapter.updateData(otherlist);
//                }
//
//                break;
//        }
//    }

}
