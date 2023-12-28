package com.example.xuonglv1.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.xuonglv1.Dao.LoaiSpDao;
import com.example.xuonglv1.Dao.SanPhamDao;
import com.example.xuonglv1.Dao.ThongKeDao;
import com.example.xuonglv1.Model.LoaiSp;
import com.example.xuonglv1.Model.SanPham;
import com.example.xuonglv1.Model.TonKho;
import com.example.xuonglv1.R;
import com.example.xuonglv1.adapter.SanPham_Adapter;
import com.example.xuonglv1.adapter.SpinnerLoaiSPAdapter;
import com.example.xuonglv1.adapter.TonKhoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frg_thongKeTonKho extends Fragment {
    ListView lstTonKho;
    ArrayList<TonKho> list;
    TonKhoAdapter adapter;
    TextView txttong;
    ThongKeDao thongKeDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_thong_ke_ton_kho, container, false);
        lstTonKho=v.findViewById(R.id.lstTonKho);
        txttong=v.findViewById(R.id.txttong);
        ThongKeDao thongKeDao=new ThongKeDao(getActivity());
        list = (ArrayList<TonKho>) thongKeDao.getTop();
        thongKeDao=new ThongKeDao(getActivity());
        txttong.setText("Số lượng tồn kho: "+thongKeDao.tinhTongSoLuong()+" sản phẩm");
        adapter = new TonKhoAdapter(getActivity(),list,this);
        lstTonKho.setAdapter(adapter);
        return v;
    }

}