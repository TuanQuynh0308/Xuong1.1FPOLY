package com.example.xuonglv1.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.xuonglv1.Dao.CtHoaDonDao;
import com.example.xuonglv1.Dao.SanPhamDao;
import com.example.xuonglv1.Model.CtHoaDon;
import com.example.xuonglv1.Model.SanPham;
import com.example.xuonglv1.R;
import com.example.xuonglv1.adapter.CtHoaDonAdapter;
import com.example.xuonglv1.adapter.SanPhamSpinnerAdapter;

import java.util.ArrayList;

public class HoaDonCtActivity extends AppCompatActivity {
    ListView lvSp;
    EditText edtsoHd, edtSoLuong;
    Spinner spnSanPham;
    TextView tvTongTien;
    ImageView imgBack;
    Button btnSave;
    SanPhamSpinnerAdapter sanPhamSpinnerAdapter;
    ArrayList<SanPham> listSp;
    CtHoaDonDao ctHoaDonDao;
    ArrayList<CtHoaDon> list;
    CtHoaDonAdapter adapter;
    CtHoaDon hoaDonCt;
    SanPhamDao sanPhamDao;
    String tenSp;
    int maSp, giatien;
    int maHd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_ct);
        edtsoHd = findViewById(R.id.edtSoHd_itemHdCt);
        lvSp = findViewById(R.id.lvHdCt);
        tvTongTien = findViewById(R.id.tvTongTien);
        edtSoLuong = findViewById(R.id.edtSoLuong_hdct);
        btnSave = findViewById(R.id.btnSave_hdct);
        spnSanPham = findViewById(R.id.spSp_itemHdCt);
        imgBack = findViewById(R.id.imgBack);
        //
        ctHoaDonDao = new CtHoaDonDao(this);
        //
        maHd = getIntent().getIntExtra("maHd", 0);
        edtsoHd.setText(String.valueOf(maHd));
        capNhapLv();
        //Sp sanPham
        sanPhamDao = new SanPhamDao(this);
        listSp = new ArrayList<>();
        listSp = (ArrayList<SanPham>) sanPhamDao.getAll();
        sanPhamSpinnerAdapter = new SanPhamSpinnerAdapter(this, listSp);
        spnSanPham.setAdapter(sanPhamSpinnerAdapter);

        spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSp = listSp.get(i).getMaSP();
                tenSp = listSp.get(i).getTenSP();
                giatien = listSp.get(i).getGiaSp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soluong = edtSoLuong.getText().toString();
                if (soluong.isEmpty()) {
                    Toast.makeText(HoaDonCtActivity.this, "Nhập số lượng", Toast.LENGTH_SHORT).show();
                    edtSoLuong.requestFocus();
                    return;
                } else {
                    hoaDonCt = new CtHoaDon();
                    hoaDonCt.setSoLuong(Integer.parseInt(soluong));
                    hoaDonCt.setMaHoaDon(maHd);
                    hoaDonCt.setMaSp(maSp);
                    hoaDonCt.setDonGia(giatien);
                    if (ctHoaDonDao.insertHoaDonCt(hoaDonCt)) {
                        Toast.makeText(HoaDonCtActivity.this, "Thêm  succ", Toast.LENGTH_SHORT).show();
                        edtSoLuong.setText("");
                        list.clear();
                        list.addAll(ctHoaDonDao.getAll(maHd));
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(HoaDonCtActivity.this, "Thêm fail", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhapLv();
            }
        });
    }


    public void capNhapLv() {
        list = (ArrayList<CtHoaDon>) ctHoaDonDao.getAll(maHd);
        adapter = new CtHoaDonAdapter(this, list);
        adapter.setOnDeleteSuccessListener(new CtHoaDonAdapter.OnDeleteSuccessListener() {
            @Override
            public void onDeleteSuccess() {
                int tongTien = adapter.tinhTongTien();
                tvTongTien.setText(tongTien + " $");
            }
        });
        lvSp.setAdapter(adapter);
        int tongTien = adapter.tinhTongTien();
        tvTongTien.setText(tongTien + " $");
    }
}