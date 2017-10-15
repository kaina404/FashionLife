package fashionlife.com.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fashionlife.com.base.component.BaseApplication;

/**
 * Created by lovexujh on 2017/10/15
 */

public class DbSQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "fashionlife.db";
    public static final int VERSION = 1;

    /**
     * this(context, name, factory, version, null);
     */
    public DbSQLiteHelper() {
        super(BaseApplication.getInstance(), DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建缓存数据库
        db.execSQL(DBConstants.CACHE.CREATE_CACHE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
