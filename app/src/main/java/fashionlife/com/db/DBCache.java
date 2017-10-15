package fashionlife.com.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fashionlife.com.base.data.BaseCache;

/**
 * Created by lovexujh on 2017/10/15
 */

public class DBCache extends BaseCache {//CRUD


    private SQLiteDatabase mDB;

    public DBCache() {
        mDB = DBUtils.getInstance().getDB();
    }


    @Override
    public long insert(String key, String value) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.CACHE.KEY, key);
        values.put(DBConstants.CACHE.VALUE, value);
        return mDB.replace(DBConstants.CACHE.CACHE_DB_NAME, null, values);
    }

    @Override
    public long update(String key, String value) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.CACHE.VALUE, value);
        String whereClause = DBConstants.CACHE.KEY + "=?";
        String[] whereArgs = new String[]{key};
        return mDB.update(DBConstants.CACHE.CACHE_DB_NAME, values, whereClause, whereArgs);
    }

    @Override
    public long delete(String key) {
        String whereClause = DBConstants.CACHE.KEY + "=?";
        String[] whereArgs = new String[]{key};
        return mDB.delete(DBConstants.CACHE.CACHE_DB_NAME, whereClause, whereArgs);
    }

    @Override
    public long deleteAll() {
        return mDB.delete(DBConstants.CACHE.CACHE_DB_NAME, null, null);
    }

    @Override
    public String getCache(String key, String defaultValue) {
        String[] columns = new String[]{DBConstants.CACHE.VALUE};
        String selection = DBConstants.CACHE.KEY + "=?";
        String[] selectionArgs = new String[]{key};
        String value = defaultValue;
        Cursor cursor = null;
//        String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy
        try {
            cursor = mDB.query(DBConstants.CACHE.CACHE_DB_NAME, columns, selection, selectionArgs, null, null, null);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                value = cursor.getString(cursor.getColumnIndex(DBConstants.CACHE.VALUE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return value;
    }
}
