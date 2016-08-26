package com.yjbo.yjboandroidmodule.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yjbo.yjboandroidmodule.entity.HttpUrlClass;
import com.yjbo.yjboandroidmodule.util.L;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yjbo
 * @deprecated 2016年8月11日14:57:37
 */
public class HttpsDBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public HttpsDBManager(Context context) {
        helper = new DBHelper(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();

    }

    /**
     * add persons
     */
    public void add(List<HttpUrlClass> cxzbs, String username) {
        db.beginTransaction(); // 开始事务
        try {
            for (HttpUrlClass cxzb : cxzbs) {
                if (queryTheCursorByhashcode(cxzb.getHttp_url_hashcode()).getCount() == 0) {
                    db.execSQL(
                            "INSERT INTO yjbo_https VALUES(null,?,?,?,?,?,?,?,?,?,?,?)",
                            new Object[]{ cxzb.getTime(),
                                    cxzb.getContent(), cxzb.getTitle(),
                                    cxzb.getSmail_title(), cxzb.getHttp_url(), cxzb.getHttp_url_hashcode(),
                                    cxzb.getLove_num(), cxzb.getIs_cache(), cxzb.getKind(),
                                    cxzb.getShare_num(), cxzb.getOther()});
                }
            }
            db.setTransactionSuccessful(); // 设置事务成功完成
        } finally {
            db.endTransaction(); // 结束事务
        }
    }

    /**
     * 可以尝试这种方法：http://stackoverflow.com/questions/34240352/android-sqlite-table-book-has-4-columns-but-5-values-were-supplied
     *  ContentValues values = new ContentValues();
     *  values.put("title", intent.getStringExtra("TITLE"));
     *  values.put("author", intent.getStringExtra("AUTHOR"));
     *   values.put("rating", intent.getFloatExtra("RATING", (float) 0.0));
     *  values.put("quotes", intent.getStringExtra("QUOTES"));
     *  // Inserting Row
     *  db.insert("book", null, values);
     */
    public void add(HttpUrlClass cxzb, String urlHashCode) {
        db.beginTransaction(); // 开始事务
        try {
            L.i("添加一条数据了--0-");
            if (queryTheCursorByhashcode(urlHashCode).getCount() == 0) {
                L.i("添加一条数据了--1-");
                db.execSQL(//"null"不能加null   cxzb.get_id(),
                        "INSERT INTO yjbo_https VALUES(null,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{cxzb.getTime(),
                                cxzb.getContent(), cxzb.getTitle(),
                                cxzb.getSmail_title(), cxzb.getHttp_url(), urlHashCode,
                                cxzb.getLove_num(), cxzb.getIs_cache(), cxzb.getKind(),
                                cxzb.getShare_num(), cxzb.getOther()});
                db.setTransactionSuccessful(); // 设置事务成功完成
            }
            L.i("添加一条数据了--2-");
        } finally {
            db.endTransaction(); // 结束事务
        }
    }

    /**
     * delete old typecase
     */
    public void deletes() {
        db.delete("yjbo_https", null, null);
    }

    /**
     * update person's age
     */
    public void update(HttpUrlClass cxzb) {

    }

    /**
     * update person's age
     */
    public void delall(String username) {
        db.delete("yjbo_https", "username=? ", new String[]{username});

    }

    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public List<HttpUrlClass> query() {
        ArrayList<HttpUrlClass> persons = new ArrayList<HttpUrlClass>();
        Cursor c = null;
        try {
            c = queryTheCursor();
            while (c.moveToNext()) {
                HttpUrlClass cxzbinfo = new HttpUrlClass();
                cxzbinfo.set_id(c.getInt(c.getColumnIndex("_id")));
                cxzbinfo.setTime(c.getString(c.getColumnIndex("time")));
                cxzbinfo.setContent(c.getString(c.getColumnIndex("content")));
                cxzbinfo.setTitle(c.getString(c.getColumnIndex("title")));
                cxzbinfo.setSmail_title(c.getString(c.getColumnIndex("smail_title")));
                cxzbinfo.setHttp_url(c.getString(c.getColumnIndex("http_url")));
                cxzbinfo.setHttp_url_hashcode(c.getString(c.getColumnIndex("http_url_hashcode")));
                cxzbinfo.setLove_num(c.getString(c.getColumnIndex("love_num")));
                cxzbinfo.setIs_cache(c.getString(c.getColumnIndex("is_cache")));
                cxzbinfo.setKind(c.getString(c.getColumnIndex("kind")));
                cxzbinfo.setShare_num(c.getString(c.getColumnIndex("share_num")));
                cxzbinfo.setOther(c.getString(c.getColumnIndex("other")));
                persons.add(cxzbinfo);
            }
        } finally {
            if (c != null) {
                try {

                    c.close();

                } catch (Exception e) {
                }
            }
        }

        return persons;
    }

    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public List<HttpUrlClass> query(String username, String curPage,
                                    String pageSize) {
        ArrayList<HttpUrlClass> persons = new ArrayList<HttpUrlClass>();
        Cursor c = null;
        try {
            c = queryTheCursor1(username, curPage, pageSize);
            // Cursor c = queryTheCursor();
            while (c.moveToNext()) {

                HttpUrlClass cxzbinfo = new HttpUrlClass();
                cxzbinfo.set_id(c.getInt(c.getColumnIndex("_id")));
                cxzbinfo.setTime(c.getString(c.getColumnIndex("time")));
                cxzbinfo.setContent(c.getString(c.getColumnIndex("content")));
                cxzbinfo.setTitle(c.getString(c.getColumnIndex("title")));
                cxzbinfo.setSmail_title(c.getString(c.getColumnIndex("smail_title")));
                cxzbinfo.setHttp_url(c.getString(c.getColumnIndex("http_url")));
                cxzbinfo.setHttp_url_hashcode(c.getString(c.getColumnIndex("http_url_hashcode")));
                cxzbinfo.setLove_num(c.getString(c.getColumnIndex("love_num")));
                cxzbinfo.setIs_cache(c.getString(c.getColumnIndex("is_cache")));
                cxzbinfo.setKind(c.getString(c.getColumnIndex("kind")));
                cxzbinfo.setShare_num(c.getString(c.getColumnIndex("share_num")));
                cxzbinfo.setOther(c.getString(c.getColumnIndex("other")));
                persons.add(cxzbinfo);
            }
        } finally {
            if (c != null) {
                try {

                    c.close();

                } catch (Exception e) {
                }
            }
        }

        return persons;
    }

    /**
     * query all persons, return cursor
     *
     * @return Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM yjbo_https", null);
        return c;
    }

    /**
     * query all persons, return cursor
     * 查询该数据库的表里面，有没有已经存在该条httpurl了
     * @return Cursor
     */
    public Cursor queryTheCursorByhashcode(String http_url_hashcode) {
        Cursor c = db.rawQuery("SELECT * FROM yjbo_https where http_url_hashcode=?",
                new String[]{http_url_hashcode});
        L.i("查询该数据库的表里面，有没有已经存在该条httpurl了=" + c.getColumnCount()+"--"+c.getCount() + ";");
        return c;
    }

    /**
     * query all persons, return cursor
     *
     * @return Cursor
     */
    public Cursor queryTheCursor1(String username, String curPage,
                                  String pageSize) {
        int cur = Integer.parseInt(curPage);
        int size = Integer.parseInt(pageSize);
        int startidx = (cur - 1) * size;

        Cursor c = db.rawQuery(
                "select * from yjbo_https where username=?  order by idx desc limit "
                        + pageSize + " offset " + startidx,
                new String[]{username});
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
