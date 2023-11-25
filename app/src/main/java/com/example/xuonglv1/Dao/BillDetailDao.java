package com.example.xuonglv1.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xuonglv1.Model.Bill;
import com.example.xuonglv1.Model.BillDetail;
import com.example.xuonglv1.activity.database.DbHelper;

import java.util.ArrayList;

public class BillDetailDao {
    private final DbHelper dbHelper;

    public BillDetailDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<BillDetail> selectAll() {
        ArrayList<BillDetail> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("select * from BillDetail", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                BillDetail billDetail = new BillDetail();
                billDetail.setId(cursor.getInt(0));
                billDetail.setBillID(cursor.getInt(1));
                billDetail.setQuantity(cursor.getInt(2));
                billDetail.setCreatedDate(cursor.getString(3));
                list.add(billDetail);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
