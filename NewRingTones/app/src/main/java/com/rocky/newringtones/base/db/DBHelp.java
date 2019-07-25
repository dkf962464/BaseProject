package com.rocky.newringtones.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rocky.newringtones.R;

/**
 * Created by Administrator on 2017/3/20.
 */

public class DBHelp extends SQLiteOpenHelper {
    private static int version = 1;
    private static  String TAG="sqlLite";
    private Context mContext;
    private SQLiteDatabase mDb;
    public DBHelp(Context context) {
        super(context, context.getResources().getString(R.string.db_name), null, version);
        mContext=context;
        Log.e(TAG, "DBHelp: "+DBHelp.class);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "DBHelp: onCreate");
        //default table
        String defaultTable=mContext.getResources().getString(R.string.db_name);
        String sql = "create table "+defaultTable + "(_id Integer primary key,name text not null,address text not null,birthday, age Integer not null)";
        mDb=db;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         * 这个方法是更改版本号的时候执行用的   如果数据库改变则要执行这个方法
         */
    }


}
