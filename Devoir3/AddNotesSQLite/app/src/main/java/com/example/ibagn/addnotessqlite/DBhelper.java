package com.example.ibagn.addnotessqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // TABLE INFORMATTION
    public static final String TABLE_NOTE = "member";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TITLE = "name";
    public static final String NOTE_DESCRITION = "description";
    public static final String NOTE_DATE = "date";

    // DATABASE INFORMATION
    static final String DB_NAME = "NOTE.DB";
    static final int DB_VERSION = 1;

    // TABLE CREATION STATEMENT
    private static final String CREATE_TABLE = "create table "
            + TABLE_NOTE + "(" + NOTE_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NOTE_TITLE + " TEXT NOT NULL,"+NOTE_DESCRITION+" TEXT NOT NULL,"+NOTE_DATE+");";

    public DBhelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }
}