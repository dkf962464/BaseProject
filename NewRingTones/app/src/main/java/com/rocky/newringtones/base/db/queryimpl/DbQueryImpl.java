package com.rocky.newringtones.base.db.queryimpl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rocky.newringtones.base.db.DBHelp;
import com.rocky.newringtones.base.db.query.DbInterface;
import com.rocky.newringtones.base.db.query.DbQuery;
import com.rocky.newringtones.home.model.TestDbModel;

import java.util.ArrayList;
import java.util.List;

/**
 * create by 2019/7/24
 * <p>
 * author: wgl
 * <p>
 * Believe in yourself, you can do it.
 */
public class DbQueryImpl<T> implements DbQuery<T> {

    private SQLiteDatabase db;
    String sql;
    @SuppressLint("Recycle")
    private Cursor mCursor;
    private String mString;
    private String mCursorString;

    public DbQueryImpl(Context context) {
        db = new DBHelp(context).getWritableDatabase();
    }

    @Override
    public boolean save(String tableName, T t, DbInterface dbInterface) {
        StringBuilder values = new StringBuilder();
        List<String> columName = getColumName(tableName);
        for (int i = 0; i < columName.size(); i++) {
            values.append("?,");
        }
        values.deleteCharAt(values.length() - 1);
        sql = "insert into " + tableName + " values" + "(" + values + ")";
        dbInterface.save(t, db, sql);
        return true;
    }

    @Override
    public String queryById(String tableName, String where, int id) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> columName = getColumName(tableName);
        sql = "select * from " + tableName + " where " + where + "=" + id;
        Log.e("querySql", "" + sql);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        for (int i = 0; i < columName.size(); i++) {
            int columnIndex = cursor.getColumnIndex(columName.get(i));
            mString = cursor.getString(columnIndex);
            stringBuilder.append(mString);
        }
//        Log.e("result", ""+mString );
//        Log.e("result", stringBuilder.toString());
        return  mString;
    }
    //如需查询全部数据且能转换成Bean对象，请调用queryById，此方法废弃且不维护
    @Deprecated
    @Override
    public List<T> queryAll(String tableName) {
        StringBuilder values = new StringBuilder();
        List<String> columName = getColumName(tableName);
        sql = "select * from " + tableName + "";
        try {
            mCursor = db.rawQuery(sql, null);
            while (mCursor.moveToNext()) {
                for (int i = 0; i < columName.size(); i++) {
                    int columnIndex = mCursor.getColumnIndex(columName.get(i));
                    mCursorString = mCursor.getString(columnIndex);
                    mCursorString+=mCursorString;
//                    mList.add(mCursorString);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        values.delete(0,1);
        Log.e("jsonResult", ""+mCursorString);
        return null;
    }

    @Override
    public boolean deleteById(String tableName, int deleteId) {
        List<String> columName = getColumName(tableName);
        sql = "delete from " + tableName + " where " + columName.get(0) + "=" + deleteId;
        try {
            db.execSQL(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAll(String tableName, String sequenceName) {
        sql = "delete from " + tableName;
        try {
            db.execSQL(sql);
            if (null != sequenceName) {
                String sols = "update sqlite_sequence SET " + sequenceName + " = 0  where name =" + tableName + "";
                db.execSQL(sols);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void merge(String tabName, int mergeId, T t, DbInterface dbInterface) {
        StringBuilder values = new StringBuilder();
        List<String> columName = getColumName(tabName);
        for (int i = 0; i < columName.size(); i++) {
            if (i == 0) {
                values.append(" " + coloumName.get(i)).append("=").append("?");
            } else {
                values.append("," + coloumName.get(i)).append("=").append("?");
            }
        }
        sql = "update " + tabName + " set" + values.toString();
        try {
            dbInterface.merge(t, sql, db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> coloumName = new ArrayList<>();

    private List<String> getColumName(String tablename) {
        sql = "PRAGMA table_info(" + tablename + ")";
        coloumName.clear();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            coloumName.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        Log.e("sqlSize", "" + coloumName.size());
        return coloumName;
    }

    public SQLiteDatabase getDb() {
        if (null != db) {
            return db;
        }
        return null;
    }
}
