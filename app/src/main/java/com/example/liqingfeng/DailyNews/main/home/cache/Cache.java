package com.example.liqingfeng.DailyNews.main.home.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.liqingfeng.DailyNews.common.AppApplication;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/3 10:29
 * @DESC: 新闻缓存工具类
 * @VERSION: V1.0
 */

public class Cache {
    private  SharedPreferences sp;

    public Cache(String dbName) {
        sp = AppApplication.getInstance().getSharedPreferences(dbName,
                Context.MODE_PRIVATE);
    }

    public boolean addCache(String date, String value) {
        return put(date, value);
    }

    public boolean deleteCache(String date) {
        return put(date, "");
    }

    public boolean updateCache(String date, String value) {
        return put(date, value);
    }

    public String findCache(String date, String defaultValue) {
        return get(date, defaultValue);
    }

    public  void clearCache() {
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }

    private boolean put(String key, String value) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        return edit.commit();
    }

    private String get(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

}
