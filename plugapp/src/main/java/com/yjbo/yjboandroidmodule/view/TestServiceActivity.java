package com.yjbo.yjboandroidmodule.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboSwipeActivity;
import com.yjbo.yjboandroidmodule.test.testService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 测试服务的生命周期
 */
public class TestServiceActivity extends BaseYjboSwipeActivity {

    @Bind(R.id.show_content_txt)
    TextView showContentTxt;

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test_service);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setonData() {
        showContentTxt.setText(getResources().getString(R.string.testservice_result_string));
    }

    //测试服务的启动
    @OnClick({R.id.start_txt, R.id.stop_txt, R.id.bind_txt, R.id.unbind_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_txt:
                startService(new Intent(TestServiceActivity.this, testService.class));
                break;
            case R.id.stop_txt:
                stopService(new Intent(TestServiceActivity.this, testService.class));
                break;
            case R.id.bind_txt:
                bindService(new Intent(TestServiceActivity.this, testService.class), sc, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_txt:
                unbindService(sc);
                break;
        }
    }
    ServiceConnection sc = new ServiceConnection() {

        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            System.out.println("onServiceConnected");
        }

        public void onServiceDisconnected(ComponentName arg0) {
            System.out.println("onServiceDisconnected");
        }

    };
}
