package com.zero.myplan.core.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zero.myplan.core.dao.model.PlanM;
import com.zero.myplan.core.dao.table.PlanT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero on 15-12-18.
 */
public class PlanDao {

    private PlanDataBaseHelper mDBHelper;

    public PlanDao(Context context){
        mDBHelper = new PlanDataBaseHelper(context);
    }

    public void insertPlan(String createdTime, String lastUpateTime, String doneTime, String type, String content){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String sql = "insert into t_plan(createdTime, lastUpdateTime, doneTime, type, content) values (? ,? ,? ,? ,?)";
        db.execSQL(sql, new String[]{createdTime, lastUpateTime, doneTime, type, content});
        db.close();
    }

    public void deletePlan(int id){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String sql = "delete from t_plan where planID = ?";
        db.execSQL(sql, new Object[]{id});
        db.close();
    }


    public void deleteAll(){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String sql = "delete from t_plan";
        db.execSQL(sql);
        db.close();

    }

    public List<PlanM> getAllNotes(){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        List<PlanM> list = new ArrayList<>();
        String sql = "select * from t_plan";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                PlanM plan = new PlanM();
                plan.setId(cursor.getLong(cursor.getColumnIndex(PlanT.ID)));
                plan.setCreatedTime(cursor.getLong(cursor.getColumnIndex(PlanT.CREATE_TIME)));
                plan.setLastUpateTime(cursor.getLong(cursor.getColumnIndex(PlanT.LAST_UPDATE_TIME)));
                plan.setDoneTime(cursor.getLong(cursor.getColumnIndex(PlanT.DONE_TIME)));
                plan.setContent(cursor.getString(cursor.getColumnIndex(PlanT.CONTENT)));
                plan.setType(cursor.getInt(cursor.getColumnIndex(PlanT.TYPE)));
                plan.setHasDone(cursor.getInt(cursor.getColumnIndex(PlanT.HAS_DONE)));
                list.add(plan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

}
