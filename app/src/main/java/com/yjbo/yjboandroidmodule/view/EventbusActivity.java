package com.yjbo.yjboandroidmodule.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.entity.AsyncMessage;
import com.yjbo.yjboandroidmodule.entity.BackgroundMessage;
import com.yjbo.yjboandroidmodule.entity.MainMessage;
import com.yjbo.yjboandroidmodule.entity.PostMessage;
import com.yjbo.yjboandroidmodule.service.CramerThread;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/***
 * eventbus的四种模式的测试
 * 2016年7月19日10:31:27
 *
 * @author yjbo
 */
public class EventbusActivity extends AppCompatActivity {

    private TextView tv;
    private final static String TAG = "EventBusTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        tv = ((TextView) findViewById(R.id.tv));
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                EventBus.getDefault().post(new MainMessage("Hello EventBus"));
                break;
            case R.id.btn2:
                EventBus.getDefault().post(new MainMessage("Hello EventBus"));
                break;
            case R.id.btn3:
                EventBus.getDefault().post(new MainMessage("Hello EventBus"));
                break;
            case R.id.btn4:
                EventBus.getDefault().post(new MainMessage("Hello EventBus"));
                break;
        }
    }

    //主线程中执行
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMainEventBus(MainMessage msg) {
        tv.setText(msg.getMsg());
        Log.d(TAG, "onEventBus() returned: " + Thread.currentThread());
    }

    //后台线程
    @Subscribe(threadMode = ThreadMode.BackgroundThread)
    public void onBackgroundEventBus(MainMessage msg) {
        Log.d(TAG, "onEventBusBackground() returned: " + Thread.currentThread());
    }

    //强制后台执行
    @Subscribe(threadMode = ThreadMode.Async)
    public void onAsyncEventBus(MainMessage msg) {
        Log.d(TAG, "onEventBusAsync() returned: " + Thread.currentThread());
    }

    //默认情况，和发送事件在同一个线程
    @Subscribe(threadMode = ThreadMode.PostThread)
    public void onPostEventBus(MainMessage msg) {
        Log.d(TAG, "onEventBusPost() returned: " + Thread.currentThread());
    }
}
