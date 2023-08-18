package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {
    DbHelper dbHelper;
    public LoaiSachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LoaiSach> getDSLoaisach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from loaisach",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoaisach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",tenLoai);
        long check = sqLiteDatabase.insert("loaisach",null,values);
        if(check == -1){
            return false;
        }
        return true;
    }

    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from sach where maloai = ?", new String []{String.valueOf(id)});
        if(cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("loaisach","maloai =?",new String[]{String.valueOf(id)});
        if(check == -1){
            return 0;
        }
        return 1;
    }

}
