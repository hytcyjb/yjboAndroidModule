package com.yjbo.yjboandroidmodule.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yjbo.yjboandroidmodule.entity.PersonClass;
import com.yjbo.yjboandroidmodule.entity.PersonClass;
import com.yjbo.yjboandroidmodule.util.L;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户表
 * @author yjbo
 * @deprecated 2016年8月11日14:57:37
 */
public class PersonDBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public PersonDBManager(Context context) {
        helper = new DBHelper(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();

    }

    /**
     * add persons
     */
//    + "pname varchar,"//人的姓名
//            + "pid varchar,"//人的id，编号
//            + "pregister_time varchar,"//人的注册app时间
//            + "pregister_hx_time varchar,"//人的注册环信时间
//            + "psign varchar,"//人的个性签名时间
//            + "other varchar)";//其他
    public void add(List<PersonClass> cxzbs) {
        db.beginTransaction(); // 开始事务
        try {
            for (PersonClass cxzb : cxzbs) {
                if (queryTheCursorByPid(cxzb.getPid()).getCount() == 0) {
                    db.execSQL(
                            "INSERT INTO yjbo_person VALUES(null,?,?,?,?,?,?)",
                            new Object[]{ cxzb.getPname(),
                                    cxzb.getPid(), cxzb.getPregister_time(),
                                    cxzb.getPregister_hx_time(), cxzb.getPsign(), cxzb.getOther()});
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
    public void add(PersonClass cxzb, String pid) {
        db.beginTransaction(); // 开始事务
        try {
            L.i("添加一条数据了--0-");
            if (queryTheCursorByPid(pid).getCount() == 0) {
                L.i("添加一条数据了--1-");
                db.execSQL(
                        "INSERT INTO yjbo_person VALUES(null,?,?,?,?,?,?)",
                        new Object[]{cxzb.getPname(),
                                cxzb.getPid(), cxzb.getPregister_time(),
                                cxzb.getPregister_hx_time(), cxzb.getPsign(), cxzb.getOther()});
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
        db.delete("yjbo_person", null, null);
    }

    /**
     * update person's age
     */
    public void update(PersonClass cxzb) {

    }

    /**
     * update person's age
     */
    public void delall(String username) {
        db.delete("yjbo_person", "username=? ", new String[]{username});

    }

    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public List<PersonClass> query() {
        ArrayList<PersonClass> persons = new ArrayList<PersonClass>();
        Cursor c = null;
        try {
            c = queryTheCursor();
            while (c.moveToNext()) {
                PersonClass cxzbinfo = new PersonClass();
                cxzbinfo.set_id(c.getInt(c.getColumnIndex("_id")));
                cxzbinfo.setPid(c.getString(c.getColumnIndex("pname")));
                cxzbinfo.setPid(c.getString(c.getColumnIndex("pid")));
                cxzbinfo.setPregister_time(c.getString(c.getColumnIndex("pregister_time")));
                cxzbinfo.setPregister_hx_time(c.getString(c.getColumnIndex("pregister_hx_time")));
                cxzbinfo.setPsign(c.getString(c.getColumnIndex("psign")));
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
    public List<PersonClass> query(String username, String curPage,
                                    String pageSize) {
        ArrayList<PersonClass> persons = new ArrayList<PersonClass>();
        Cursor c = null;
        try {
            c = queryTheCursor1(username, curPage, pageSize);
            // Cursor c = queryTheCursor();
            while (c.moveToNext()) {

                PersonClass cxzbinfo = new PersonClass();
                cxzbinfo.set_id(c.getInt(c.getColumnIndex("_id")));
                cxzbinfo.setPid(c.getString(c.getColumnIndex("pname")));
                cxzbinfo.setPid(c.getString(c.getColumnIndex("pid")));
                cxzbinfo.setPregister_time(c.getString(c.getColumnIndex("pregister_time")));
                cxzbinfo.setPregister_hx_time(c.getString(c.getColumnIndex("pregister_hx_time")));
                cxzbinfo.setPsign(c.getString(c.getColumnIndex("psign")));
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
        Cursor c = db.rawQuery("SELECT * FROM yjbo_person", null);
        return c;
    }

    /**
     * query all persons, return cursor
     * 查询该数据库的表里面，有没有已经存在该条httpurl了
     * @return Cursor
     */
    public Cursor queryTheCursorByPid(String pid) {
        Cursor c = db.rawQuery("SELECT * FROM yjbo_person where pid=?",
                new String[]{pid});
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
                "select * from yjbo_person where username=?  order by idx desc limit "
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
