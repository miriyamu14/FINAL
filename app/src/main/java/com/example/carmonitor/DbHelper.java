package com.example.carmonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context ) {
        super(context,"user_data",null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table user_info(name TEXT ,carname TEXT,carsim TEXT primary key,pass PASSWORD)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists user_info");
    }
    public Boolean insetUserData(String name,String number,String caname,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("carname",caname);
        contentValues.put("carsim",number);
        contentValues.put("pass",password);
        long result= DB.insert("user_info",null,contentValues);
        if (result != 0){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from user_info ",null);
        return cursor;
    }
}
