package com.example.xuonglv1.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.xuonglv1.Model.SanPham;
import com.example.xuonglv1.Model.TonKho;
import com.example.xuonglv1.database.DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {

    private SQLiteDatabase db;
    private Context context;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public double tinhTongTienNhap(int month) {
        double tongTienNhap = 0;

        // Truy vấn SQL sử dụng INNER JOIN để kết hợp thông tin từ CtHoaDon và HoaDon
        String query = "SELECT SUM(CtHoaDon.soLuong * CtHoaDon.donGia) AS tongTienNhap " +
                "FROM CtHoaDon " +
                "INNER JOIN HoaDon ON CtHoaDon.maHoaDon = HoaDon.maHoaDon " +
                "WHERE HoaDon.loaiHoaDon = 0 AND strftime('%m', HoaDon.ngayThang) = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.format("%02d", month)});

        if (cursor != null && cursor.moveToFirst()) {
            // Lấy giá trị tổng tiền nhập từ kết quả truy vấn
            tongTienNhap = cursor.getDouble(cursor.getColumnIndex("tongTienNhap"));
            cursor.close();
        }

        return tongTienNhap;
    }

    @SuppressLint("Range")
    public double tinhTongTienXuat(int month) {
        double tongTienNhap = 0;

        // Truy vấn SQL sử dụng INNER JOIN để kết hợp thông tin từ CtHoaDon và HoaDon
        String query = "SELECT SUM(CtHoaDon.soLuong * CtHoaDon.donGia) AS tongTienNhap " +
                "FROM CtHoaDon " +
                "INNER JOIN HoaDon ON CtHoaDon.maHoaDon = HoaDon.maHoaDon " +
                "WHERE HoaDon.loaiHoaDon = 1 AND strftime('%m', HoaDon.ngayThang) = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.format("%02d", month)});

        if (cursor != null && cursor.moveToFirst()) {
            // Lấy giá trị tổng tiền nhập từ kết quả truy vấn
            tongTienNhap = cursor.getDouble(cursor.getColumnIndex("tongTienNhap"));
            cursor.close();
        }

        return tongTienNhap;
    }

    @SuppressLint("Range")
    public List<TonKho> getTop(){
        String sqlTop = "select maSp from SanPham order by soLuong desc";
        List<TonKho> list = new ArrayList<TonKho>();
        SanPhamDao sanPhamDao = new SanPhamDao(context);
        Cursor c = db.rawQuery(sqlTop,null);

        while (c.moveToNext()){
            TonKho top = new TonKho();
            SanPham sanPham = sanPhamDao.getID(c.getString(c.getColumnIndex("maSp")));
            top.setTenSp(sanPham.getTenSP());
            top.setSoLuong(sanPham.getSoLuong());
            list.add(top);
        }

        return list;
    }

    @SuppressLint("Range")
    public int tinhTongSoLuong() {
        int tongSoLuong = 0;

        // Truy vấn SQL để lấy tổng số lượng từ bảng SanPham
        String query = "SELECT SUM(soLuong) AS tongSoLuong FROM SanPham";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Lấy giá trị tổng số lượng từ kết quả truy vấn
            tongSoLuong = cursor.getInt(cursor.getColumnIndex("tongSoLuong"));
            cursor.close();
        }

        return tongSoLuong;
    }
}
