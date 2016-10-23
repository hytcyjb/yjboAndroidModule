package com.yjbo.yjboandroidmodule;

import android.app.Application;
import android.content.Context;


/**
 * Created by Administrator on 2016/8/26.
 */
public class MyApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }
    private static Context sAppContext;
    public static Context getAppContext() {
        return sAppContext;
    }
}
