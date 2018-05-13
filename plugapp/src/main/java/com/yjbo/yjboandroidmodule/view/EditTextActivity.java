package com.yjbo.yjboandroidmodule.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.util.WeakHandler;

/**
*
* @descr 输入框设置没有原生态的痕迹
* @author yjbo
* @time 2016/9/9 10:05
*/
public class EditTextActivity extends BaseYjboActivity {

    private int time=0;
    Runnable r;
    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_text);
    }

    WeakHandler weakHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });
    @Override
    public void setonView() {
        final EditText editText = (EditText) findViewById(R.id.user_account_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(r!=null)
                weakHandler.removeCallbacks(r);
                final String content = editText.getText() + "";
                r = new Runnable() {
                    @Override
                    public void run() {
                        if (content.equals(editText.getText() + "")) {
                            //说明
                        } else {

                        }
                    }
                };
                if (s.length()>0){
                    weakHandler.postDelayed(r,1000);
                }else {//长度为0，不处理
                }
            }
        });
    }

    @Override
    public void setonData() {

    }

}
