package com.rocky.newringtones.base.db.query;

import android.database.sqlite.SQLiteDatabase;

/**
 * create by 2019/7/24
 * <p>
 * author: wgl
 * <p>
 * Believe in yourself, you can do it.
 */
public interface DbInterface<T> {
     void save(T t, SQLiteDatabase db, String sql);
     void merge(T t,String sql,SQLiteDatabase db);
}
