package com.yjbo.yjboandroidmodule.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.util.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProgressSimpleActivity extends BaseYjboActivity {

    @Bind(R.id.pb_progressbar)
    ProgressBar pbProgressbar;

    int progressInt = 10;

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_progress_simple);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setonData() {
        pbProgressbar.setProgress(progressInt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.pb_progressbar, R.id.add_btn, R.id.down_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pb_progressbar:
                break;
            case R.id.add_btn:
                if (progressInt >= 0 && progressInt <= 90) {
                    progressInt += 10;
                }
                changeProgr(progressInt);
                break;
            case R.id.down_btn:
                if (progressInt >= 10 && progressInt <= 100) {
                    progressInt -= 10;
                }
                changeProgr(progressInt);
                break;
        }
    }

    private void changeProgr(int progressInt) {
        L.e(progressInt+"====s");
        pbProgressbar.setProgress(progressInt);
    }


}
