package com.example.lhg.new_proto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LHG on 2017-05-24.
 */

public class MemoDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "memo.db";

    public MemoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + MemoItem.TABLE  + "("
                + MemoItem.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + MemoItem.KEY_type + " INT, "
                + MemoItem.KEY_memo + " TEXT, "
                + MemoItem.KEY_file + " TEXT, "
                + MemoItem.KEY_owner + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + MemoItem.TABLE);

        // Create tables again
        onCreate(db);

    }
}
