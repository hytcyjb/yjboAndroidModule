package com.yjbo.mvp.PictureActivity.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yjbo.mvp.PictureActivity.contract.UserInfoContract;
import com.yjbo.mvp.PictureActivity.model.UserInfoModel;
import com.yjbo.mvp.PictureActivity.presenter.UserInfoPresenter;
import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.CommonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 图片详情页面
 * 引用：http://www.cnblogs.com/android-blogs/p/5632103.html
 * 2016年8月17日19:04:47
 *
 * @author yjbo
 */
public class PicDetialActivity extends AppCompatActivity implements UserInfoContract.View{

    @Bind(R.id.txt_content)
    TextView txtContent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private UserInfoContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detial);
        ButterKnife.bind(this);
        setonView();
        setonData();

    }


    public void setonView() {
        Intent intent = this.getIntent();
        String content = intent.getStringExtra("content");
        txtContent.setText(content);
        toolbar.setTitle("使用了侧滑的布局");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);

        new UserInfoPresenter(this);
        presenter.start();
    }

    public void setonData() {

    }


    @OnClick({R.id.txt_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_content:
                break;
//            case R.id.fb:
//                CommonUtil.show(PicDetialActivity.this, "您点击了悬浮式按钮");
//                break;
        }
    }

    @Override
    public void showLoading() {
        CommonUtil.show(PicDetialActivity.this, "正在加载中....");
    }

    @Override
    public void dismissLoading() {
        CommonUtil.show(PicDetialActivity.this, "加载结束");
    }

    @Override
    public void showUserInfo(UserInfoModel userInfoModel) {

    }

    @Override
    public String loadUserId() {
        return null;
    }

    @Override
    public void setPresenter(UserInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
