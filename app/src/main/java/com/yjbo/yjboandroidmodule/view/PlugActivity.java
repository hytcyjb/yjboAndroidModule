package com.yjbo.yjboandroidmodule.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.view.plug.PluginManager;
import com.yjbo.yjboandroidmodule.view.plug.ProxyActivity;
import com.yjbo.yjboandroidmodule.view.plug.ProxySecActivity;

public class PlugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug);
    }

    public void loadApk(View view) {
        //注意：使用运行时权限
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    public void startApk(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        String otherApkMainActivityName = PluginManager.getInstance().getPluginPackageArchiveInfo(1).activities[33].name;
        intent.putExtra("className", otherApkMainActivityName);
        startActivity(intent);
    }
    public void startApk2(View view) {
        Intent intent = new Intent(this, ProxySecActivity.class);
        String otherApkMainActivityName = PluginManager.getInstance().getPluginPackageArchiveInfo(1).activities[34].name;
        intent.putExtra("className", otherApkMainActivityName);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PluginManager.getInstance().setContext(this);
        PluginManager.getInstance().loadApk(Environment.getExternalStorageDirectory().getAbsolutePath()+"/yjboAnPlug.apk",1);
        PluginManager.getInstance().loadApk(Environment.getExternalStorageDirectory().getAbsolutePath()+"/threeapk-debug.apk",2);
    }


}
