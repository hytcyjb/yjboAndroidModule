package com.yjbo.yjboandroidmodule.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.yjbo.yjboandroidmodule.R;

/**
 * @description: <描述当前版本功能>
 * <p>
 *      Android创建桌面快捷方式
 * 参考：http://blog.csdn.net/bjp000111/article/details/51363981
 * </p>
 * @author: yjbo
 * @date: 2016-07-26 19:00
 */
public class ShowCutUtil {

    public static void CreateShotCut(final Context context, final Class<?> clazz,
                              final String name, final String image) {

        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        // 加入action,和category之后，程序卸载的时候才会主动将该快捷方式也卸载
        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcutIntent.setClass(context, clazz);
        /**
         * 创建一个Bundle对象让其保存将要传递的值
         */
        Bundle bundle = new Bundle();
        bundle.putString("userId", "杨建波的快捷桌面");
        shortcutIntent.putExtras(bundle);
        /**
         * 设置这条属性，可以使点击快捷方式后关闭其他的任务栈的其他activity，然后创建指定的acticity
         */
        shortcutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // 创建快捷方式的Intent
        Intent shortcut = new Intent(Intent.ACTION_CREATE_SHORTCUT);
        // 不允许重复创建
        shortcut.putExtra("duplicate", false);
        // 点击快捷图片，运行的程序主入口
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // 需要现实的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

//        //1. 快捷图片
//        Parcelable icon = Intent.ShortcutIconResource.fromContext(
//                getApplicationContext(), R.drawable.ic_launcher);
//        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        //2. 快捷图片
        // Intent.EXTRA_SHORTCUT_ICON 是bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON,bitmap);
        shortcut.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(shortcut);
    }

}
