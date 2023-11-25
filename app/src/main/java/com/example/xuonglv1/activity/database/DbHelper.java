package com.example.xuonglv1.activity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "file.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Bill(id int primary key autoincrement ,name text,quantity integer,createByUser text, createdDate text,note text)");
        db.execSQL("create table BillDetail(id int primary key autoincrement,billID integer,quantity integer)");
        db.execSQL("create table Product(id integer  primary key autoincrement,name text,quatity integer,price text,photo text,userName )");
        db.execSQL("create table User (userName text primary key,password text,numberPhone text,positon text,avatar text,profile text,lastLogin text,screatedDate text,lastAction text )");

        db.execSQL("INSERT INTO User (userName, password, numberPhone, positon, avatar, profile, lastLogin, createdDate, lastAction)\n" +
                "        VALUES ('quynhltph31990', '123456', '1234567890', 1, 'john_avatar.jpg', 'User profile information', '2023-10-14', '2023-10-14', '2023-10-14');");

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
