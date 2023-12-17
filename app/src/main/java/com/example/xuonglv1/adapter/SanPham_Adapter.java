package com.example.xuonglv1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.xuonglv1.Dao.SanPhamDao;
import com.example.xuonglv1.Model.SanPham;
import com.example.xuonglv1.R;
import com.example.xuonglv1.fragment.Frg_sanPham;

import java.util.ArrayList;

public class SanPham_Adapter extends ArrayAdapter<SanPham> {
    private Context context;
    Frg_sanPham frgSanPham;
    private ArrayList<SanPham> list;
    SanPhamDao sanPhamDao;
    TextView txtMasp,txtTenSp,txtGiaSp,txtLoaisp,txtSoLuong;
    ImageView imgAnhSp,imgDelete;
    byte[] hinhAnh;

    public SanPham_Adapter(@NonNull Context context, Frg_sanPham frgSanPham, ArrayList<SanPham> list) {
        super(context, 0,list);
        this.context = context;
        this.frgSanPham = frgSanPham;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.item_sp,null);
        }

        final SanPham item = list.get(position);
        if (item!=null){
            txtMasp=v.findViewById(R.id.txtmasp);
            txtTenSp=v.findViewById(R.id.txttenSp);
            txtGiaSp=v.findViewById(R.id.txtgiasp);
            txtSoLuong=v.findViewById(R.id.txtsoLuong);
            txtLoaisp=v.findViewById(R.id.txtloaisp);
            imgAnhSp = v.findViewById(R.id.imgAnhsp);
            imgDelete = v.findViewById(R.id.btndelete);
            txtMasp.setVisibility(View.GONE);

            txtTenSp.setText("Tên sản phẩm: "+item.getTenSP());
            txtGiaSp.setText("Giá Sản Phẩm: "+item.getGiaSp());
            txtSoLuong.setText("Số lượng: "+item.getSoLuong());

            hinhAnh = item.getAnhSP();
            Bitmap bitmap= BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            imgAnhSp.setImageBitmap(bitmap);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    frgSanPham.xoa(String.valueOf(item.getMaSP()));
                }
            });

        }


        return v;
    }
}
