package com.example.xuonglv1.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xuonglv1.Model.Bill;
import com.example.xuonglv1.activity.database.DbHelper;

import java.util.ArrayList;

public class billDao {
    private final DbHelper dbHelper;

    public billDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Bill> selectAll() {
        ArrayList<Bill> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("select * from Bill", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                Bill bill = new Bill();
                bill.setId(cursor.getInt(0));
                bill.setProductID(cursor.getInt(1));
                bill.setName(cursor.getString(2));
                bill.setQuantity(cursor.getInt(3));
                bill.setCreatedByUser(cursor.getString(4));
                bill.setCreatedDate(cursor.getString(5));
                list.add(bill);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
