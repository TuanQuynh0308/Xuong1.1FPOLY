package com.example.xuonglv1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="QLKH";
    static final int dbVersion=1;
    public DbHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table Bill(id int primary key autoincrement ,name text,quantity integer,createByUser text, createdDate text,note text)");
//        db.execSQL("create table BillDetail(id int primary key autoincrement,billID integer,quantity integer)");
//        db.execSQL("create table Product(id integer  primary key autoincrement,name text,quatity integer,price text,photo text,userName )");
//        db.execSQL("create table User (userName text primary key,password text,numberPhone text,positon text,avatar text,profile text,lastLogin text,screatedDate text,lastAction text )");
//
//        db.execSQL("INSERT INTO User (userName, password, numberPhone, positon, avatar, profile, lastLogin, createdDate, lastAction)\n" +
//                "        VALUES ('quynhltph31990', '123456', '1234567890', 1, 'john_avatar.jpg', 'User profile information', '2023-10-14', '2023-10-14', '2023-10-14');");

//        String createTableThuThu="create table ThuThu (" +
//                "maTT TEXT PRIMARY KEY," +
//                "hoTen TEXT NOT NULL," +
//                "matKhau TEXT NOT NULL)";
//        db.execSQL(createTableThuThu);


        //Tạo bảng ThanhVien
        String createTableThanhVien="create table ThanhVien (" +
                "maTV TEXT PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);

        //Tạo bảng LoaiSach
        String createTableLoaiSanPham="create table LoaiSanPham (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSanPham);


        //Tạo bảng Sach
        String createTableSanPham="create table SanPham (" +
                "maSp INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSp TEXT NOT NULL," +
                "giaSp INTEGER NOT NULL," +
                "soLuong INTEGER," +
                "anhSp BLOB," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSanPham);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion!= newVersion){
            db.execSQL("DROP TABLE IF EXISTS Bill");
            db.execSQL("DROP TABLE IF EXISTS BillDetail");
            db.execSQL("DROP TABLE IF EXISTS Product");
            db.execSQL("DROP TABLE IF EXISTS User");
            onCreate(db);
        }
    }
}
