package com.yjbo.yjboandroidmodule.util;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * 创建一个进度条
 * Created by yjbo on 2016/8/6.
 */
public class KProgressDialog {
    static KProgressHUD kProg;

    public static void create(Context context) {
        kProg = new KProgressHUD(context).setCancellable(true)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
    }

    public static void show(String str) {
        if (isShowing()) {
            if (CommonUtil.isNull(str)) {
                str = "正在加载中...";
            }
            kProg.setLabel(str)
                    .show();
        }
    }

    public static void dismiss() {
        if (kProg != null) {
            kProg.dismiss();
        }
    }
    public static boolean isShowing() {
        if (kProg != null) {
            kProg.dismiss();
        }
        return true;
    }
}
