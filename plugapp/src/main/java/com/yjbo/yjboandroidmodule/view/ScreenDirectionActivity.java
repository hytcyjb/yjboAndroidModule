package com.yjbo.yjboandroidmodule.view;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 手机屏幕旋转方向
 *
 * @author yjbo
 * @time 2016年6月6日14:29:51
 */
public class ScreenDirectionActivity extends BaseYjboActivity implements SensorEventListener {

    @Bind(R.id.screen_direct_txt_show)
    TextView screenDirectTxtShow;
    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_screen_direction);
        ButterKnife.bind(this);
        getScreenDirec();
    }

    @Override
    public void setonView() {

    }

    @Override
    public void setonData() {

    }

    private void getScreenDirec() {
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();

        int degree = 90 * rotation;

        float rad = (float) Math.PI / 2 * rotation;

        screenDirectTxtShow.setText("--"+degree+"--\n"+"--"+rad+"--");
    }

    @OnClick(R.id.screen_direct_txt_show)
    public void onClick() {
        getScreenDirec();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
