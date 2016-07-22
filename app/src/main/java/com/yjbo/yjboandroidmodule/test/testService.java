package com.yjbo.yjboandroidmodule.test;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.yjbo.yjboandroidmodule.util.L;

/***
 * 测试Service的各个生命周期
 *
 * @author yjbo
 *         2016年7月21日18:39:58
 */
public class testService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        L.i("testService--onBind");
//        throw new UnsupportedOperationException("Not yet implemented");
        return  null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.i("testService--onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        L.i("testService--onStart");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.i("testService--onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.i("testService--onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        L.i("testService--onRebind");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.i("testService--onDestroy");
    }

}
