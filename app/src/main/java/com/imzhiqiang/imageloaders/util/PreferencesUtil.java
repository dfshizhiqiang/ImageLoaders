package com.imzhiqiang.imageloaders.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Zech on 2016/1/1.
 */
public class PreferencesUtil {
    private SharedPreferences mSharedPreferences;
    private Context mContext;

    public PreferencesUtil(Context context) {
        this.mContext = context;
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 1);
    }

    public void saveInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
