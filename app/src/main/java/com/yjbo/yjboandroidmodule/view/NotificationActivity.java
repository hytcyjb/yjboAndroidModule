package com.yjbo.yjboandroidmodule.view;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseIntanClass;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.base.BaseYjboSwipeActivity;
import com.yjbo.yjboandroidmodule.util.L;

import butterknife.ButterKnife;
import butterknife.OnClick;
/***
 * 各种模块的开发 之通知
 * 2016年7月11日15:34:15
 *
 * @author yjbo
 */
public class NotificationActivity extends BaseYjboSwipeActivity {

    private final int NOTIFICATION_BASE_NUMBER = 110;
    private Notification.Builder builder = null;
    private Notification n = null;

    private NotificationManager nm = null;
    private PendingIntent contentIntent = null;

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notification);
    }

    @Override
    public void setonView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setonData() {
        ShowNotion();
        L.d("yjbo", "Product Model:" + Build.MODEL + "\n" + Build.VERSION.SDK + "\n" + Build.VERSION.RELEASE);
        //Product Model:SCL-TL00  //        22   //        5.1.1
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick({R.id.notifica_txt, R.id.add_notification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notifica_txt:
                if (nm != null) {
                    nm.cancel(NOTIFICATION_BASE_NUMBER);
                }
                break;
            case R.id.add_notification:
                if (nm != null && n != null) {
                    nm.notify(NOTIFICATION_BASE_NUMBER, n);//显示通知
                }
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void ShowNotion() {
        nm = (NotificationManager) NotificationActivity.this.getSystemService(NOTIFICATION_SERVICE);
        Resources res = NotificationActivity.this.getResources();
        builder = new Notification.Builder(NotificationActivity.this);
        contentIntent = PendingIntent.getActivity(this, 0, getIntent(), 0);
        builder.setContentIntent(contentIntent).
                setSmallIcon(R.mipmap.ic_launcher)//设置状态栏里面的图标（小图标）
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))//下拉下拉列表里面的图标（大图标）
                .setTicker("android各模块开发!") //设置状态栏的显示的信息
                .setWhen(System.currentTimeMillis())//设置时间发生时间
                .setAutoCancel(false)//设置可以清除
                .setContentTitle("android-yjbo")//设置下拉列表里的标题
                .setContentText("android各模块开发进行中");//设置上下文内容

        n = builder.getNotification();//获取一个Notification
        n.defaults = Notification.DEFAULT_SOUND;//设置为默认的声音
        n.flags = Notification.FLAG_NO_CLEAR;//无法清除
        n.flags = Notification.FLAG_ONGOING_EVENT;//正在进行的
//        nm.notify(NOTIFICATION_BASE_NUMBER, n);//显示通知 break; }
    }

//    //无法清除
//    noti.flags=Notification.FLAG_NO_CLEAR;
//    //正在进行的
//    noti.flags=Notification.FLAG_ONGOING_EVENT;
}
