package com.yjbo.yjboandroidmodule.plug;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yjbo.yjboandroidmodule.R;

public class PluginMainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_main);
        findViewById(R.id.btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(thisContext,SecondActivity.class));
    }
}
