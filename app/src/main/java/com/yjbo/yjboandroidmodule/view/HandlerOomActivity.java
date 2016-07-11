package com.yjbo.yjboandroidmodule.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.CommonUtil;
import com.yjbo.yjboandroidmodule.util.WeakHandler;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * //https://github.com/badoo/android-weak-handler/
 *
 * @author yjbo
 * @time 2016年6月21日17:51:25
 * @deprecated handler内存处理
 */
public class HandlerOomActivity extends AppCompatActivity {

    @Bind(R.id.txt_show_handler)
    TextView txtShowHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_oom);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Message message2 = weakHandler.obtainMessage();
                    message2.obj = "很obtainMessage好";
                    message2.what = 2;
                    weakHandler.sendMessage(message2);

                    Snackbar.make(view, "https://github.com/badoo/android-weak-handler/", Snackbar.LENGTH_LONG)
                            .setAction("访问原项目地址", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Uri uri = Uri.parse("https://github.com/badoo/android-weak-handler/");
                                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(it);
                                }
                            }).show();
                }
            });
        }
        Message message = new Message();
        message.obj = "很好";
        message.what = 0;
        weakHandler.sendMessage(message);

        Message message1 = new Message();
        message1.obj = "很好";
        message1.what = 1;
        weakHandler.sendMessage(message1);
        weakHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
               String  colorAccent = CommonUtil.colorToString(getResources().getColor(R.color.colorAccent));
               String  colorPrimary = CommonUtil.colorToString(getResources().getColor(R.color.colorPrimary));
                String x = "111";
                txtShowHandler.setText(Html.fromHtml(String.format("<font size='%s' color='%s'>%s</font>" +
                        "<font size='%s' color='%s'>&nbsp;&nbsp;&nbsp;" + "<br />以及博客上课" +
                        "</font>",5,colorAccent,"yjb"+x,500,colorPrimary)));
                String num = txtShowHandler.getText() +"";
                SpannableString spannableString = new SpannableString(num);
                spannableString.setSpan(new RelativeSizeSpan(0.7f), num.length() - 1, num.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                txtShowHandler.setText(Html.fromHtml(String.format(num)));
            }
        }, 5000);
    }

    Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            txtShowHandler.setText("正常的handler，没有处理");

        }
    };

    WeakHandler weakHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    txtShowHandler.setText("WeakHandler，0第一个显示的" + msg.obj);

                    break;
                case 1:
                    txtShowHandler.setText(txtShowHandler.getText()+"\n"+"WeakHandler，1第一个显示的" + msg.obj);
                    break;
                case 2:
                    txtShowHandler.setText("weakHandler.obtainMessage();"+msg.obj);
                    break;
            }
            return false;
        }
    });

    @OnClick(R.id.txt_show_handler)
    public void onClick() {
    }
}
