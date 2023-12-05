package com.example.xuonglv1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xuonglv1.Model.LoaiSp;
import com.example.xuonglv1.activity.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiSpDao {
    private final DbHelper dbHelper;

    public LoaiSpDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean insert(LoaiSp obj) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        long row = db.insert("LoaiSanPham", null, values);
        return (row > 0);

    }

    public boolean update(LoaiSp obj) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        long row = db.update("LoaiSanPham", values, "maLoai=?", new String[]{String.valueOf(obj.getMaLoai())});
        return (row > 0);
    }

    //xóa
    public boolean delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("LoaiSanPham", "maLoai=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }

    // get All
    public List<LoaiSp> getAll() {
        String sql = "select * from LoaiSanPham";
        return getData(sql);
    }


    // get id
    public LoaiSp getID(String id) {
        String sql = "select * from LoaiSanPham where maLoai=?";
        List<LoaiSp> list = getData(sql, id);
        return list.get(0);
    }

    //getData
    private List<LoaiSp> getData(String sql, String... selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<LoaiSp> list = new ArrayList<LoaiSp>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            LoaiSp obj = new LoaiSp();
            obj.setMaLoai(Integer.parseInt(c.getString(0)));
            obj.setTenLoai(c.getString(1));

            list.add(obj);
        }
        return list;
    }
}
