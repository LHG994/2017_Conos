package com.example.lhg.new_proto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.lhg.new_proto.MemoItem.KEY_ID;
import static com.example.lhg.new_proto.MemoItem.TABLE;

/**
 * Created by LHG on 2017-05-24.
 */

public class MemoRepo {
    private MemoDBHelper dbHelper;

    public MemoRepo(Context context) {
        dbHelper = new MemoDBHelper(context);
    }

    public int insert(MemoItem memoItem) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MemoItem.KEY_type, memoItem.type);
        values.put(MemoItem.KEY_memo, memoItem.memo);
        values.put(MemoItem.KEY_file, memoItem.file_link);
        values.put(MemoItem.KEY_owner, memoItem.owner);

        // Inserting Row
        long memo_Id = db.insert(TABLE, null, values);
        db.close(); // Closing database connection
        return (int) memo_Id;
    }

    public Cursor getMemotList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                MemoItem.KEY_ROWID + "," +
                KEY_ID + "," +
                MemoItem.KEY_type + "," +
                MemoItem.KEY_memo + "," +
                MemoItem.KEY_file + "," +
                MemoItem.KEY_owner +
                " FROM " + TABLE;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }

    public Cursor  getMemoListByKeyword(String search) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                MemoItem.KEY_ROWID + "," +
                KEY_ID + "," +
                MemoItem.KEY_type + "," +
                MemoItem.KEY_memo + "," +
                MemoItem.KEY_file + "," +
                MemoItem.KEY_owner +
                " FROM " + TABLE +
                " WHERE " +  MemoItem.KEY_memo + "  LIKE  '%" +search + "%' "
                ;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }

    public MemoItem getMemoById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT " +
                KEY_ID + "," +
                MemoItem.KEY_type + "," +
                MemoItem.KEY_memo + "," +
                MemoItem.KEY_file + "," +
                MemoItem.KEY_owner +
                " FROM " + MemoItem.TABLE
                + " WHERE " +
                MemoItem.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        MemoItem memoItem = new MemoItem();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                memoItem.memo_ID =cursor.getInt(cursor.getColumnIndex(MemoItem.KEY_ID));
                memoItem.type =cursor.getInt(cursor.getColumnIndex(MemoItem.KEY_type));
                memoItem.memo  =cursor.getString(cursor.getColumnIndex(MemoItem.KEY_memo));
                memoItem.file_link =cursor.getString(cursor.getColumnIndex(MemoItem.KEY_file));
                memoItem.owner = cursor.getString(cursor.getColumnIndex(MemoItem.KEY_owner));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return memoItem;
    }

}
