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

import com.example.xuonglv1.Model.LoaiSp;
import com.example.xuonglv1.R;
import com.example.xuonglv1.fragment.Frg_loaiSanPham;

import java.util.ArrayList;

public class LoaiSpAdapter extends ArrayAdapter<LoaiSp> {
    private Context context;;
    private ArrayList<LoaiSp> list;
    TextView txtMa,txtloai;
    ImageView btnXoa;
    Frg_loaiSanPham fragment;
    public LoaiSpAdapter(@NonNull Context context, ArrayList<LoaiSp> list, Frg_loaiSanPham fragment) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loaisanpham,null);
        }
        LoaiSp item = list.get(position);
        if(item!=null){
            txtMa = v.findViewById(R.id.txtmaLoai);
            txtloai = v.findViewById(R.id.txttenloaisp);
            btnXoa = v.findViewById(R.id.btnxoa_loaisach);
            txtMa.setText(item.getMaLoai()+"");
            txtloai.setText(item.getTenLoai());
        }
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;
    }
}
