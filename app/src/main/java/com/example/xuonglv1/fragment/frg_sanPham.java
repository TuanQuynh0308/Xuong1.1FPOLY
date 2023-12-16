package com.example.xuonglv1.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.xuonglv1.Dao.LoaiSpDao;
import com.example.xuonglv1.Dao.SanPhamDao;
import com.example.xuonglv1.Model.LoaiSp;
import com.example.xuonglv1.Model.SanPham;
import com.example.xuonglv1.R;
import com.example.xuonglv1.adapter.SanPham_Adapter;
import com.example.xuonglv1.adapter.SpinnerLoaiSPAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class frg_sanPham extends Fragment {
    ListView lstSanPham;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edtMasp, edtTenSp,edtGiaSp,edtsoLuong;
    Spinner spinner;
    ImageView imgAnhSp;
    Button btnChonAnh,btnXacNhan,btnHuy;
    ArrayList<SanPham> list;
    ArrayList<LoaiSp> list_lsp;
    SanPham item;
    SanPhamDao sanPhamDao;
    SanPham_Adapter sanPhamAdapter;
    int position, maLoaiSp;
    LoaiSpDao dao_lsp;
    LoaiSp item_sp;
    SpinnerLoaiSPAdapter spinnerAdapter;
    byte[] hinhAnh;
    final int REQUEST_CODE_FOLDER = 456;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_san_pham, container, false);
        fab=v.findViewById(R.id.fltAdd);
        lstSanPham=v.findViewById(R.id.lstSanPham);
        sanPhamDao=new SanPhamDao(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });

        lstSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                item = list.get(i);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_sanpham);
        btnChonAnh=dialog.findViewById(R.id.btnChonAnh);
        btnXacNhan=dialog.findViewById(R.id.btnXacNhan);
        btnHuy=dialog.findViewById(R.id.btnHuy);
        imgAnhSp=dialog.findViewById(R.id.imgAnhSP_Ud);
        edtMasp=dialog.findViewById(R.id.edtmasp_ud);
        edtTenSp=dialog.findViewById(R.id.edtTenSp_ud);
        edtsoLuong=dialog.findViewById(R.id.edtSoLuong_ud);
        edtGiaSp=dialog.findViewById(R.id.edtGiasp_ud);
        spinner=dialog.findViewById(R.id.spLoaiSp_ud);

        edtMasp.setVisibility(View.GONE);
        dao_lsp=new LoaiSpDao(context);
        list_lsp = new ArrayList<LoaiSp>();
        list_lsp= (ArrayList<LoaiSp>) dao_lsp.getAll();

        spinnerAdapter = new SpinnerLoaiSPAdapter(getActivity(), list_lsp);
        //
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoaiSp = list_lsp.get(i).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        if (type!=0){
            edtMasp.setText(String.valueOf(item.getMaSP()));
            edtTenSp.setText(item.getTenSP()+"");
            edtGiaSp.setText(item.getGiaSp()+"");
            edtsoLuong.setText(item.getSoLuong()+"");

            for (int i = 0; i < list_lsp.size(); i++) {
                if (item.getMaLoai() == (list_lsp.get(i).getMaLoai())) {
                    position = i;
                }
                Log.i("zzzzzzzzzzzz", "posPhong: " + position);
                spinner.setSelection(position);
            }
        }

        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
//                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_FOLDER);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(edtTenSp.getText().toString()) || TextUtils.isEmpty(edtGiaSp.getText().toString()) || TextUtils.isEmpty(edtsoLuong.getText().toString())) {
                    Toast.makeText(context, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhSp.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                hinhAnh = byteArrayOutputStream.toByteArray();

                item=new SanPham();
                item.setTenSP(edtTenSp.getText().toString());
                item.setGiaSp(Integer.parseInt(edtGiaSp.getText().toString()));
                item.setSoLuong(Integer.parseInt(edtsoLuong.getText().toString()));
                item.setMaLoai(maLoaiSp);
                item.setAnhSP(hinhAnh);

                if (type==0){
                    if (sanPhamDao.insert(item)>0){
                        Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    item.setMaSP(Integer.parseInt(edtMasp.getText().toString()));
                    if (sanPhamDao.update(item)>0){
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();

            }
        });

        dialog.show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = requireActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhSp.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode){
//            case REQUEST_CODE_FOLDER:
//                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
//                    Intent intent = new Intent(Intent.ACTION_PICK);
//                    intent.setType("image/*");
//                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
//                }else {
//                    Toast.makeText(getContext(), "Bạn không cho phép truy cập thư viện ảnh", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//        }
//
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    public void capNhatLv() {
        list = (ArrayList<SanPham>) sanPhamDao.getAll();
        sanPhamAdapter = new SanPham_Adapter(getActivity(),this,list);
        lstSanPham.setAdapter(sanPhamAdapter);
    }

    public void xoa(String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xoá");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sanPhamDao.delete(Id);
                capNhatLv();
                dialogInterface.cancel();
                Toast.makeText(getContext(), "Xóa thành công ", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

}