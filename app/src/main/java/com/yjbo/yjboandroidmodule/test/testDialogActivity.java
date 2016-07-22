package com.yjbo.yjboandroidmodule.test;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.L;

public class testDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);
        L.i("testDialogActivity--onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.i("testDialogActivity--onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.i("testDialogActivity--onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.i("testDialogActivity--onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.i("testDialogActivity--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.i("testDialogActivity--onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.i("testDialogActivity--onDestroy");
    }
}
