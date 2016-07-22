package com.yjbo.yjboandroidmodule.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.ClipboardManager;
import android.view.View;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.L;
import com.yjbo.yjboandroidmodule.util.ShowTipDialog;
import com.yjbo.yjboandroidmodule.view.EventbusActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 测试Android的各个生命周期
 *
 * @author yjbo
 *         2016年7月21日18:39:58
 */
public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        L.i("testActivity--onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.i("testActivity--onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.i("testActivity--onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.i("testActivity--onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.i("testActivity--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.i("testActivity--onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.i("testActivity--onDestroy");
    }


    @OnClick({R.id.show_dialog, R.id.show_otheractivity,R.id.show_dialog_activity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_dialog:
                ShowTipDialog.layDialog(testActivity.this, "标题", "内容", "", "确定", "取消", false);
                ShowTipDialog.SetonDialog(new ShowTipDialog.DialogChoose() {
                    @Override
                    public void query(String payPassword) {
                        ClipboardManager clip = (ClipboardManager) testActivity.this.getSystemService(testActivity.this.CLIPBOARD_SERVICE);
                        clip.setText(payPassword); // 复制
                        //clip.getText(); // 粘贴
                        CommonUtil.show(testActivity.this, "“" + payPassword + "”复制成功");
                        ShowTipDialog.dimissDia();
                    }
                });
                break;
            case R.id.show_otheractivity:
                startActivity(new Intent(testActivity.this, EventbusActivity.class));
                break;
            case R.id.show_dialog_activity:
                startActivity(new Intent(testActivity.this, testDialogActivity.class));
                break;
        }
    }
}
