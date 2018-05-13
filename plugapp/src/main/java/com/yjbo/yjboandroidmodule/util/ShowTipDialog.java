package com.yjbo.yjboandroidmodule.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;


/***
 * 提示信息
 *
 * @author yjbo 2016年7月1日12:16:36
 */
public class ShowTipDialog {
    static Dialog customDialog;
    private static DialogChoose mdialogChoose;

    static Activity mactivity;
    static TextView txt_content = null;
    static TextView txt_pos = null;
    static TextView txt_nev = null;
    static TextView txt_title = null;

    public static void SetonDialog(DialogChoose dialogChoose) {
        mdialogChoose = dialogChoose;
    }

    public interface DialogChoose {
        void query(String payPassword);
    }

    /***
     * @paramcontext
     * kindActivity
     * @parammsg --中间信息
     * @paramflag -- 是否隐藏“确定按钮”，同时把"取消"变为“返回”
     */
    @SuppressLint({"NewApi", "InflateParams"})
    public static void layDialog(final Activity activity, String title, String other,final String kindActivity
            ,String posStr,String nevStr,boolean isCancel) {
        mactivity = activity;
        customDialog = new Dialog(activity, R.style.dialog_layout);
        View view = LayoutInflater.from(activity).inflate(
                R.layout.dialog_tip_show, null);

        txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_pos = (TextView) view.findViewById(R.id.txt_pos);
        txt_nev = (TextView) view.findViewById(R.id.txt_nev);
        if ("".equals(nevStr)) {
            txt_nev.setVisibility(View.GONE);
        }else {
            txt_nev.setVisibility(View.VISIBLE);
        }
        if ("".equals(posStr)) {
            txt_pos.setVisibility(View.VISIBLE);
        }else {
            txt_pos.setVisibility(View.VISIBLE);
            txt_pos.setText(posStr);
        }
        txt_title.setText(title);
        txt_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(kindActivity)) {
                    dimissDia();
                }else{
                    mdialogChoose.query(kindActivity);
                }

            }
        });
        txt_nev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimissDia();
            }
        });
        txt_content.setText(other);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(view);
        Window dialogWindow = customDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);// 布局文件居中
        dialogWindow.setLayout(lp.WRAP_CONTENT, lp.WRAP_CONTENT);// 为了让对话框宽度铺满
        dialogWindow.setAttributes(lp);
        customDialog.show();
        /** 按对话框其他地方取消对话框  true可以；false 不可以*/
        customDialog.setCanceledOnTouchOutside(isCancel);
    }

    public static void dimissDia() {
        try {
            if (customDialog != null) {
                if (customDialog.isShowing()) {
                    customDialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean isShowingDia() {
        try {
            if (customDialog != null && customDialog.isShowing()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
