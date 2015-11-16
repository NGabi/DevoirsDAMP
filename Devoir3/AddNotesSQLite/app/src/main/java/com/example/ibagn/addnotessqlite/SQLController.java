package com.example.ibagn.addnotessqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class SQLController {

    private DBhelper dbhelper;
    private Context ctx;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ctx = c;
    }

    public SQLController open() throws SQLException {
        dbhelper = new DBhelper(ctx);
        database = dbhelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbhelper.close();
    }

    //Inserting Data into table
    public void insertData(String name,String description,String date ) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.NOTE_TITLE, name);
        cv.put(DBhelper.NOTE_DESCRITION, description);
        cv.put(DBhelper.NOTE_DATE, date);
        database.insert(DBhelper.TABLE_NOTE, null, cv);
    }

    //Getting Cursor to read data from table
    public Cursor readData() {
        String[] allColumns = new String[] { DBhelper.NOTE_ID,
                DBhelper.NOTE_TITLE, DBhelper.NOTE_DESCRITION, DBhelper.NOTE_DATE};
        Cursor c = database.query(DBhelper.TABLE_NOTE, allColumns, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    //Updating record data into table by id
    public int updateData(long memberID, String title,String description,String date) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(DBhelper.NOTE_TITLE, title);
        int i = database.update(DBhelper.TABLE_NOTE, cvUpdate,
                DBhelper.NOTE_ID + " = " + memberID, null);
        return i;
    }

    // Deleting record data from table by id
    public void deleteData(long memberID) {
        database.delete(DBhelper.TABLE_NOTE, DBhelper.NOTE_ID + "="
                + memberID, null);
    }

}