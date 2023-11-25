package com.example.xuonglv1.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xuonglv1.Model.Product;
import com.example.xuonglv1.activity.database.DbHelper;

import java.util.ArrayList;

public class productDao {
    private  final DbHelper dbHelper;

    public productDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Product> selectAll(){
        ArrayList<Product>list= new ArrayList<>();
        SQLiteDatabase database =dbHelper.getReadableDatabase();
        try{
            Cursor cursor =database.rawQuery("select * from Product", null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
            }while (!cursor.isAfterLast()){
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setQuantity(cursor.getInt(2));
                product.setPrice(cursor.getString(3));
                product.setPhoto(cursor.getString(4));
                product.setUserID(cursor.getString(5));
                list.add(product);
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
