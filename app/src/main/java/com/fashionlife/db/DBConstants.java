package com.fashionlife.db;

/**
 * Created by lovexujh on 2017/10/15
 */

public class DBConstants {

    public interface CACHE {

        String CACHE_DB_NAME = "cache";
        String KEY = "key";
        String VALUE = "value";

        String CREATE_CACHE = "CREATE TABLE IF NOT EXISTS " + CACHE_DB_NAME
                + " ( _id integer primary key autoincrement, "
                + KEY + " TEXT NOT NULL, "
                + VALUE + " TEXT )";
    }


}
