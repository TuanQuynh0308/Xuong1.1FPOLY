package com.example.xuonglv1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;

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

        //tạo bảng ThanhVien

        db.execSQL("create table User(username TEXT PRIMARY KEY," +
                "password TEXT NOT NULL," +
                "numberPhone TEXT NOT NULL," +
                "position INTEGER NOT NULL," +
                "profile TEXT NOT NULL," +
                "createdDate TEXT NOT NULL)");

//        db.execSQL("insert into User values " +
//                "('admin','admin','0123456789','admin',1,'2023-12-17','2023-7-23')," +
//                "('nam','123456','0987654321','PNam',0,'2023-12-17','2023-7-23')," +
//                "('quynh','123456','0596830582','TQuynh',0,'2023-12-17','2023-7-23')");





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
