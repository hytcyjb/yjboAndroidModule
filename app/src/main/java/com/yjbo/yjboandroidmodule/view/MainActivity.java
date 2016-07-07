package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.view.View;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.BaseYjboActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 各种模块的开发
 * 2016年6月6日11:47:55
 *
 * @author yjbo
 */
public class MainActivity extends BaseYjboActivity {


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
    }

    @OnClick({R.id.wifi_txt, R.id.scree_direct_txt,R.id.top_slid_fragment,R.id.handler_exception_catch
            ,R.id.json_txt,R.id.textstyle_txt,R.id.notification_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wifi_txt:
                startActivity(new Intent(MainActivity.this, WifiOpenActivity.class));
                break;
            case R.id.scree_direct_txt:
                startActivity(new Intent(MainActivity.this, ScreenDirectionActivity.class));
                break;
            case R.id.top_slid_fragment:
                startActivity(new Intent(MainActivity.this, SlidFragmentActivity.class));
                break;
            case R.id.handler_exception_catch://https://github.com/badoo/android-weak-handler/
                startActivity(new Intent(MainActivity.this, HandlerOomActivity.class));
                break;
            case R.id.json_txt:
                startActivity(new Intent(MainActivity.this, JsonActivity.class));
                break;
            case R.id.textstyle_txt:
                startActivity(new Intent(MainActivity.this, TextViewLinkActivity.class));
                break;
            case R.id.notification_txt:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
        }
    }

}
