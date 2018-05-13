package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.ShowCutUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * Android创建桌面快捷方式
 * 参考：http://blog.csdn.net/bjp000111/article/details/51363981
 *
 *  // Android创建桌面快捷方式
 * ShowCutUtil.CreateShotCut(MainActivity.this, ShowContentActivity.class, "yjb快捷方式", "");
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
        ButterKnife.bind(this);
    }

    @Override
    public void setonView() {


    }

    @Override
    public void setonData() {
        titlePublic.setText("标题没有获取成功");
        Intent intent = this.getIntent();
        String userId = intent.getStringExtra("userId");
        showContentTxt.setText(userId);
        // Android创建桌面快捷方式---这里不做处理是因为快捷方式如果已经存在就不会重复创建
        ShowCutUtil.CreateShotCut(ShowContentActivity.this, ShowContentActivity.class, "yjb快捷方式", "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
