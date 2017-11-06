package com.myfashionlife.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lovexujh on 2017/10/15
 */
public class DBUtils {

    private static DBUtils instance;
    private SQLiteDatabase mDB;
    private DbSQLiteHelper mSqLiteHelper;

    private DBUtils() {
        mSqLiteHelper = new DbSQLiteHelper();
        mDB = mSqLiteHelper.getWritableDatabase();
    }

    public static DBUtils getInstance() {
        if (null == instance) {
            synchronized (DBUtils.class) {
                if (null == instance) {
                    instance = new DBUtils();
                }
            }
        }
        return instance;
    }

    public SQLiteDatabase getDB() {
        return mDB;
    }



}
