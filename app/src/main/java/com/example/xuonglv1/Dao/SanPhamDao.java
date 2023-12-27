package com.example.xuonglv1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xuonglv1.Model.SanPham;
import com.example.xuonglv1.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {
    private SQLiteDatabase db;

    public SanPhamDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public  long insert(SanPham obj){
        ContentValues values=new ContentValues();
        values.put("tenSp", obj.getTenSP());
        values.put("giaSp", obj.getGiaSp());
        values.put("soLuong", obj.getSoLuong());
        values.put("anhSp", obj.getAnhSP());
        values.put("maLoai", obj.getMaLoai());
        return db.insert("SanPham",null,values);
    }
    public int update(SanPham obj){
        ContentValues values=new ContentValues();
        values.put("tenSp", obj.getTenSP());
        values.put("giaSp", obj.getGiaSp());
        values.put("soLuong", obj.getSoLuong());
        values.put("anhSp", obj.getAnhSP());
        values.put("maLoai", obj.getMaLoai());
        return db.update("SanPham",values,"maSp=?",new String[]{String.valueOf(obj.getMaSP())});
    }
    public int delete(String id){
        return db.delete("SanPham","maSp=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<SanPham> getDaTa(String sql, String...selectionArgs){
        List<SanPham> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            SanPham obj = new SanPham();
            obj.setMaSP(Integer.parseInt(c.getString(c.getColumnIndex("maSp"))));
            obj.setTenSP(c.getString(c.getColumnIndex("tenSp")));
            obj.setGiaSp(Integer.parseInt(c.getString(c.getColumnIndex("giaSp"))));
            obj.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            obj.setAnhSP(c.getBlob(c.getColumnIndex("anhSp")));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));

            list.add(obj);
        }
        return list;
    }
    //get all
    public List<SanPham> getAll(){
        String sql = "SELECT * FROM SanPham";
        return getDaTa(sql);
    }

    //get id
    public SanPham getID(String id){
        String sql = "SELECT *FROM SanPham WHERE maSp=?";
        List<SanPham> list = getDaTa(sql,id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
