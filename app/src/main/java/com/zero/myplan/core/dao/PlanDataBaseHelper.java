package com.zero.myplan.core.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zero on 15-12-18.
 */
public class PlanDataBaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "MyPlan";

    private static final String CREATE_PLAN_TABLE = "create table t_plan("
            + "planID integer primary key autoincrement not null,"
            + "createdTime long,"
            + "lastUpdateTime long,"
            + "doneTime long,"
            + "type integer not null,"
            + "content varchar(100)"
            + "hasDone integer default 0)";


    private static  int version = 1;

    public PlanDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PlanDataBaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
