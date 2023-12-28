package com.example.xuonglv1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.xuonglv1.Dao.CtHoaDonDao;
import com.example.xuonglv1.Dao.SanPhamDao;
import com.example.xuonglv1.Model.CtHoaDon;
import com.example.xuonglv1.Model.SanPham;
import com.example.xuonglv1.R;

import java.util.ArrayList;

public class CtHoaDonAdapter extends ArrayAdapter<CtHoaDon> {
    private Context context;
    private ArrayList<CtHoaDon> list;
    TextView tvSp, tvSl, tvThanhTien;
    ImageView btnDelete;
   CtHoaDonDao ctHoaDonDao;
    SanPhamDao sanPhamDao;
    private OnDeleteSuccessListener onDeleteSuccessListener;
    public CtHoaDonAdapter(@NonNull Context context, ArrayList<CtHoaDon> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        ctHoaDonDao = new CtHoaDonDao(context);
    }
    public int tinhTongTien() {
        int tongTien = 0;
        for (int i = 0; i < getCount(); i++) {
            CtHoaDon item = getItem(i);
            tongTien += item.getDonGia() * item.getSoLuong();
        }
        return tongTien;
    }
    public interface OnDeleteSuccessListener {
        void onDeleteSuccess();
    }

    public void setOnDeleteSuccessListener(OnDeleteSuccessListener listener) {
        onDeleteSuccessListener = listener;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_hdct_sanpham, null);
        }
        final CtHoaDon item = list.get(position);
        if (item != null) {
            tvSp = view.findViewById(R.id.tvTenSp_itemHoaDonCt);
            tvSl = view.findViewById(R.id.tvSLuong_itemHoaDonCt);
            tvThanhTien = view.findViewById(R.id.tvGia_itemHoaDonCt);
            btnDelete = view.findViewById(R.id.btnDelete_hoaDonCt);
            //
            sanPhamDao = new SanPhamDao(context);
            SanPham sp = sanPhamDao.getID(String.valueOf(item.getMaSp()));
            tvSp.setText("Tên sản phẩn: "+sp.getTenSP() );
            tvSl.setText("Số lượng: "+item.getSoLuong());
            tvThanhTien.setText("Thành tiền: "+item.getDonGia() * item.getSoLuong());
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ctHoaDonDao.deleteHoaDonCt(item.getMaCthd())) {
                            list.clear();
                            list.addAll(ctHoaDonDao.getAll(item.getMaHoaDon()));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete Succ", Toast.LENGTH_SHORT).show();
                            if (onDeleteSuccessListener != null) {
                                onDeleteSuccessListener.onDeleteSuccess();
                            }
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Bạn đã thoát xoá", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }
}
