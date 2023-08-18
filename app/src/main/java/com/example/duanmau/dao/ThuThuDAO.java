package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.database.DbHelper;

public class ThuThuDAO {
    DbHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public boolean checkLogin(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from thuthu where matt = ? and matkhau = ? ", new String[]{matt, matkhau});
        if(cursor.getCount() != 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean updateMk(String username, String oldpass, String newpass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from thuthu where matt = ? and matkhau = ?", new String[]{username, oldpass});
        if(cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("matkhau", newpass);
            long check = sqLiteDatabase.update("thuthu", values, "matt = ? ", new String[]{username});
            if (check == -1)
                return false;
        }
            return true;
    }
}
