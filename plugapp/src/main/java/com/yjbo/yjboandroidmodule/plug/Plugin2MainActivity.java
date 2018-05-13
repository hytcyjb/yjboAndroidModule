package com.yjbo.yjboandroidmodule.plug;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.plug.second.Base2Activity;


public class Plugin2MainActivity extends Base2Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_main);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setText("我是杨建波");
        findViewById(R.id.btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(thisContext,PluginMainActivity.class));
    }
}
