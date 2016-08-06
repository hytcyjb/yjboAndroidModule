package com.yjbo.yjboandroidmodule.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * 在application中声明的类，。 // 可以只声明一次，在主类中声明，其他类中就不声明了。
 *
 * @author cfs 2015年9月18日10:42:37
 */
public class ComApplicaUtil {
	static Context activity;

	public static void init(Context mactivity) {
		activity = mactivity;
	};

	public static void show(String str) {
		Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
	}

	public static void show(int str) {
		Toast.makeText(activity, activity.getString(str), Toast.LENGTH_SHORT).show();
	}

	/**
	 * 检测文件是否有该文件，没有则创建
	 *
	 * @param FILE_SAVEPATH
	 *            2015年11月15日16:18:59
	 */
	public static void IsFile(String FILE_SAVEPATH) {
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			File savedir = new File(FILE_SAVEPATH);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		} else {
			Toast.makeText(activity, "无法保存照片，请检查SD卡是否被移除", Toast.LENGTH_SHORT)
					.show();
			return;
		}
	}

	/**
	 * 检测文件是否有该文件，没有则创建
	 *
	 * @param FILE_SAVEPATH
	 *            2015年11月14日12:23:05
	 */
	public static void checkFile(String FILE_SAVEPATH) {
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			File savedir = new File(FILE_SAVEPATH);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		} else {
			Toast.makeText(activity, "无法保存照片，请检查SD卡是否被移除", Toast.LENGTH_SHORT)
					.show();
			return;
		}
	}

	/**
	 * 已进入app，在application中就已经声明了
	 */
	/**手机很目录 */
	public static String ComFILE = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/com.yjbo.yjboandroidmodule/";

	// 手机内一级目录
	public static String First_SAVEPATH(String str) {
		// 已进入app，在application中就把这个cfs119这个目录加载进去了。
		String strRoot = ComFILE + str;
		// 创建
		checkFile(strRoot);
		return strRoot;
	}

	// 一下为cfs119软件的数据-二级目录

	/** 火警监测 */
	/**
	 * 说明：这里面也有这个是因为为了在初始化app时即启动application时创建目录 下同：
	 *
	 * @return
	 */
	public static String FIRE_SAVEPATHfirst = First_SAVEPATH("CFS_Fire/");

	/**
	 * 说明：这里面也有这个是因为为了在在每次引用这个目录时检验一下它是否真的有此目录。没有则重新创建 下同：
	 *
	 * @return
	 */
	public static String FIRE_SAVEPATH() {
		// 已进入app，在application中就把这个cfs119这个目录加载进去了。
		String strRoot = ComFILE + "CFS_Fire/";// "/com.cfs119/" +
		// 创建
		checkFile(strRoot);
		return strRoot;
	};
	/** 录制视频 */
	public static String Voide_SAVEPATHfirst = First_SAVEPATH("CFSVoide/");

	public static String Voide_SAVEPATH() {
		String strRoot = ComFILE + "CFSVoide/";
		checkFile(strRoot);

		return strRoot;
	};
	/**上传头像的位置 ，下载在News_SAVEPATH中*/
	public static String Weibao_SAVEPATHfirst = First_SAVEPATH("SouGu_WeiBao/");

	public static String Weibao_SAVEPATH() {
		String strRoot = ComFILE + "SouGu_WeiBao/";
		checkFile(strRoot);
		return strRoot;
	};

}
