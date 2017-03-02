package com.mp.user.strikespeed.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mp.user.strikespeed.database.DbSchema;

public class StrikeBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "strike_database";

    public StrikeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DbSchema.StrikeTable.NAME + "(" +
                "_id integer primary key autoincrement, "+
                DbSchema.StrikeTable.Cols.ACCELERATION + ", "+
                DbSchema.StrikeTable.Cols.REACTION_SPEED + ", "+
                DbSchema.StrikeTable.Cols.STRIKE_SPEED + ", "+
                DbSchema.StrikeTable.Cols.IS_RIGHT + ", "+
                DbSchema.StrikeTable.Cols.HAS_GLOVE + ", "+
                DbSchema.StrikeTable.Cols.HAS_STEP + ", "+
                DbSchema.StrikeTable.Cols.TYPE + ", "+
                DbSchema.StrikeTable.Cols.DATE + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
