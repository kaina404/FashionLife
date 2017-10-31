package com.fashionlife.base.data;

/**
 * Created by lovexujh on 2017/10/15
 */

public abstract class BaseCache {
    public abstract long insert(String key, String value);

    public abstract long update(String key, String value);

    public abstract long delete(String key);

    public abstract long deleteAll();

    public abstract String getCache(String key, String defaultValue);
}
