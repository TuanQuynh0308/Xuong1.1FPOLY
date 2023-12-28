package com.example.xuonglv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.xuonglv1.Model.HoaDon;
import com.example.xuonglv1.R;
import com.example.xuonglv1.fragment.Frg_hoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<HoaDon> list;
    Frg_hoaDon fragment;
    TextView txtMaHd, txtMaThuKho, txtloaiHd, txtNgay;
    ImageView btnDelete;
    SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonAdapter(@NonNull Context context, Frg_hoaDon fragment, ArrayList<HoaDon> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_hoadon, null);
        }
        final HoaDon item = list.get(position);
        if (item != null) {
            txtMaHd = v.findViewById(R.id.tvMaHd_itemHoaDon);
            txtMaThuKho = v.findViewById(R.id.tvTenThuKho_itemHoaDon);
            txtloaiHd = v.findViewById(R.id.tvLoaiHd_itemHoaDon);
            txtNgay = v.findViewById(R.id.tvNgay_itemHoaDon);
            btnDelete = v.findViewById(R.id.btnDelete_hoaDon);
//
            txtMaHd.setText("Mã hóa đơn"+item.getMaHd());
            txtMaThuKho.setText("Tên thủ kho: "+item.getMaUser());
            try {
                txtNgay.setText("Ngày: "+ sfd.format(item.getNgay()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (item.getLoaiHoaDon() == 0) {
                txtloaiHd.setText("Hóa đơn: Nhập");
            } else {
                txtloaiHd.setText("Hóa đon: Xuất");
            }
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.getMaHd()));
            }
        });
        return v;
    }
}
