package com.yjbo.yjboandroidmodule.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "yjboyy.db";
	private static final int DATABASE_VERSION = 1;
	private static SQLiteDatabase db;
	public DBHelper(Context context) {
		//CursorFactory设置为null,使用默认值
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//数据库第一次被创建时onCreate会被调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		String createHttpDB="CREATE TABLE IF NOT EXISTS yjbo_https "+
				"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"//数字
				+ "time varchar,"//时间
				+ "content varchar,"//内容
				+ "title varchar,"//主标题
				+ "smail_title varchar,"//副标题
				+ "http_url varchar,"//http路径
				+ "http_url_hashcode varchar,"//http路径的哈希code值
				+ "love_num varchar,"//收藏次数，0次则没收藏，其他则说明你对这个知识点比较重视
				+ "is_cache varchar,"//是否保存缓存
				+ "kind varchar,"//属于哪一类目：cache，rxjava，retrofit等
				+ "share_num varchar,"//分享次数
				+ "other varchar)";//其他
		db.execSQL(createHttpDB); //登录用户表

	}
	/**
	 * 删除表
	 *
	 */
	public static void delete(Context context){
		DBHelper helper = new DBHelper(context);
		db = helper.getWritableDatabase();
		db.delete("yjbo_https", null, null);//2

	}

	//如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		String createnode;
//		try {
//			createnode = "CREATE TABLE IF NOT EXISTS cfs_node "+
//					"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//					+ "Node_Name varchar,"
//					+ "Node_Info varchar,"
//					+ "Mark_X varchar,"
//					+ "Mark_Y varchar,"
//					+ "PicFileName varchar,"
//					+ "PicName varchar,"
//					+ "Node_id varchar,"
//					+ "MonitorID varchar,"
//					+ "username varchar)";
//
//			db.execSQL(createnode);//分成图存储
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		/**
//		 * 消防常识
//		 */
//		try {
//			String createxfcs="CREATE TABLE IF NOT EXISTS cfs_xfcs "+
//					"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//					+ "idx varchar,"
//					+ "noticeID varchar,"
//					+ "simpleTitle varchar,"
//					+ "fullTitle varchar,"
//					+ "noticeUrl varchar,"
//					+ "noticeContent varchar,"
//					+ "ynTop varchar,"
//					+ "orderTop varchar,"
//					+ "picUrl varchar,"
//					+ "shortPicUrl varchar,"
//					+ "viewCount varchar,"
//					+ "OAccount varchar,"
//					+ "ODateTime varchar)";
//			db.execSQL(createxfcs);//消防常识
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		/**
//		 * “视频查岗”中，截图的描述，等数据保存的库
//		 */
//		try {
//
//			String sqldelete="drop table cfs_picinfo ";
//			db.execSQL(sqldelete);//截图保存
//
//			//截图保存
//			String createpicinfo="CREATE TABLE IF NOT EXISTS cfs_picinfo "+
//					"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//					+ "idx varchar,"
//					+ "userID varchar,"
//					+ "photoInfo varchar,"
//					+ "photoLongName varchar,"//相机内的全路径
//					+ "photoShortName varchar,"//相片的名字，就时间的那串字符
//					+ "companyCode varchar,"
//					+ "companySName varchar,"
//					+ "photourl varchar,"
//					+ "isUpload varchar,"
//					+ "pickind varchar,"
//					+ "photoDate varchar,"
//					+ "sn varchar,"//PicInfoClass类中有详细的说明
//					+ "userPwd varchar,"
//					+ "qztitle varchar,"
//					+ "qzkind varchar,"
//					+ "qzcontent varchar,"
//					+ "qzpickind varchar)";
//			db.execSQL(createpicinfo);//截图保存
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			String createpicinfo=" ALTER TABLE cfs_alarm ADD COLUMN Disposal_Info VARCHAR ";
//			db.execSQL(createpicinfo);//截图保存
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
