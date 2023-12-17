package com.example.xuonglv1.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.xuonglv1.Dao.LoaiSpDao;
import com.example.xuonglv1.Model.LoaiSp;
import com.example.xuonglv1.R;
import com.example.xuonglv1.adapter.LoaiSpAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frg_loaiSanPham extends Fragment {
    ListView lv;
    FloatingActionButton fltAdd;
    LoaiSpAdapter adapter;
    private ArrayList<LoaiSp> list;
    ArrayList<LoaiSp> listtemp = new ArrayList<>();
    Dialog dialog;
    LoaiSpDao dao;
    LoaiSp item;
    Button btnSave, btnHuy;
    EditText txtMa, txtLoai, txtTim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        lv = v.findViewById(R.id.lstLoaiSP);
        fltAdd = v.findViewById(R.id.fltAdd);
        dao = new LoaiSpDao(getActivity());
        capNhatLv();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(getActivity(), 0);
            }
        });
        listtemp= (ArrayList<LoaiSp>) dao.getAll();
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    public void opendialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loaisp);
        txtMa = dialog.findViewById(R.id.txtmaloaiSp_ud);
        txtLoai = dialog.findViewById(R.id.txttenloaisp_ud);
        btnSave = dialog.findViewById(R.id.btnadd_ud);
        btnHuy = dialog.findViewById(R.id.btnhuy_ud);
        txtMa.setEnabled(false);
        if (type != 0) {
            txtMa.setText(item.getMaLoai() + "");
            txtLoai.setText(item.getTenLoai());
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSp();
                item.setTenLoai(txtLoai.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item)) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaLoai(Integer.parseInt(txtMa.getText().toString()));
                        if (dao.update(item)) {
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xóa");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    public void capNhatLv() {
        list = (ArrayList<LoaiSp>) dao.getAll();
        adapter = new LoaiSpAdapter(getActivity(), list, this);
        lv.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
        if (txtLoai.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}