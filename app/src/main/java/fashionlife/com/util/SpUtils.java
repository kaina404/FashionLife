package fashionlife.com.util;

import android.content.Context;
import android.content.SharedPreferences;

import fashionlife.com.base.component.BaseApplication;

/**
 * Created by lovexujh on 2017/10/15
 */

public class SpUtils {
    private SharedPreferences sharedPreferences;

    public SpUtils(String fileName) {
        sharedPreferences = BaseApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public String getString(String key, String defaultValue) {
        String value = sharedPreferences.getString(key, defaultValue);
        return value;
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public void remove(String key) {
        if (sharedPreferences.contains(key)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.commit();
        }
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
