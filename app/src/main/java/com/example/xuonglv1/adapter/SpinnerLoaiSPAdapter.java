package com.example.xuonglv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.xuonglv1.Model.LoaiSp;
import com.example.xuonglv1.R;

import java.util.ArrayList;

public class SpinnerLoaiSPAdapter extends ArrayAdapter<LoaiSp> {
    private Context context;
    private ArrayList<LoaiSp> list;
    TextView txtMa,txtTen;

    public SpinnerLoaiSPAdapter(@NonNull Context context, ArrayList<LoaiSp> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loai_sp_item_spiner,null);
        }
        LoaiSp item = list.get(position);
        if(item!=null){
            txtMa = v.findViewById(R.id.txtMaLoaisp_sp);
            txtTen=v.findViewById(R.id.txtTenLoaiSp_sp);
            txtMa.setText(item.getMaLoai()+".");
            txtTen.setText(item.getTenLoai());
        }
        return v;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loai_sp_item_spiner,null);
        }
        LoaiSp item = list.get(position);
        if(item!=null){
            txtMa = v.findViewById(R.id.txtMaLoaisp_sp);
            txtTen=v.findViewById(R.id.txtTenLoaiSp_sp);
            txtMa.setText(item.getMaLoai()+".");
            txtTen.setText(item.getTenLoai());
        }
        return v;
    }
}
