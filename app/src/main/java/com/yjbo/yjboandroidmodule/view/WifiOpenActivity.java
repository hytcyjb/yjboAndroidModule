package com.yjbo.yjboandroidmodule.view;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.BaseYjboActivity;
import com.yjbo.yjboandroidmodule.util.CommonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 打开wifi的模块功能
 * 2016年6月6日11:48:37
 *
 * @author yjbo
 */
public class WifiOpenActivity extends BaseYjboActivity {

    WifiManager wifiManager;
    @Bind(R.id.wifi_txt_show)
    TextView wifiTxtShow;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_wifi_open);
        ButterKnife.bind(this);
        showWifiState();
        //打开wifi
        CommonUtil.OpenWifi(WifiOpenActivity.this);
    }

    private void showWifiState() {
        //2·获取WifiManager
        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        //3·查看wifi状态
        if (wifiManager.isWifiEnabled()) {
            System.out.println("当前是打开状态");
            wifiTxtShow.setText("\n " + "--当前是打开状态--" + "\n \n \n \n 点击可以关闭wifi" + "\n ");
        } else {
            System.out.println("当前是关闭状态");
            wifiTxtShow.setText("\n " + "--当前是关闭状态--" + "\n \n \n \n 点击可以打开wifi" + "\n ");
        }
    }

    private void openWifi() {
        //3·开启、关闭wifi
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
            showWifiState();
        } else {
            wifiManager.setWifiEnabled(true);
            showWifiState();
        }

    }


    @OnClick(R.id.wifi_txt_show)
    public void onClick() {
        openWifi();
    }
}
