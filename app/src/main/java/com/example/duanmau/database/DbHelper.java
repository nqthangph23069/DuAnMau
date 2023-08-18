package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "library", null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table thuthu (matt text primary key, hoten text, matkhau text)");
        db.execSQL("create table thanhvien (matv integer primary key autoincrement, hoten text, namsinh text)");
        db.execSQL("create table loaisach (maloai integer primary key autoincrement, tenloai text)");
        db.execSQL("create table sach (masach integer primary key autoincrement, tensach text, giathue integer, maloai integer references loaisach (maloai))");
        db.execSQL("create table phieumuon (mapm integer primary key autoincrement, matv integer references thanhvien (matv), matt text references thuthu (matt), masach integer references sach (masach), ngay text, trasach integer, tienthue integer)");

        // data mau
        db.execSQL("INSERT INTO LOAISACH VALUES (1, 'Thiếu nhi'),(2,'Tình cảm'),(3, 'Giáo khoa')");
        db.execSQL("INSERT INTO SACH VALUES (1, 'Hãy đợi đấy', 2500, 1), (2, 'Thằng cuội', 1000, 1), (3, 'Lập trình Android', 2000, 3)");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu01','Nguyễn Văn Anh','abc123'),('thuthu02','Trần Văn Hùng','123abc')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'Cao Thu Trang','2000'),(2,'Trần Mỹ Kim','2000')");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01', 1, '19/03/2022', 1, 2500),(2,1,'thuthu01', 3, '19/03/2022', 0, 2000),(3,2,'thuthu02', 1, '19/03/2022', 1, 2000)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("drop table if exists thuthu");
            db.execSQL("drop table if exists thanhvien");
            db.execSQL("drop table if exists loaisach");
            db.execSQL("drop table if exists sach");
            db.execSQL("drop table if exists phieumuon");
            onCreate(db);
        }
    }
}
