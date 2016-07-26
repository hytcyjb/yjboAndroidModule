package com.yjbo.yjboandroidmodule.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * Android创建桌面快捷方式
 * 参考：http://blog.csdn.net/bjp000111/article/details/51363981
 * 2016年7月26日18:25:39
 */
public class ShowContentActivity extends BaseYjboActivity {

    @Bind(R.id.title_public)
    TextView titlePublic;
    @Bind(R.id.show_content_txt)
    TextView showContentTxt;

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_show_content);

    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);

    }

    @Override
    public void setonData() {
        titlePublic.setText("创建的快捷桌面显示内容");
        String userId = this.getIntent().getStringExtra("userId");
        showContentTxt.setText(userId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
