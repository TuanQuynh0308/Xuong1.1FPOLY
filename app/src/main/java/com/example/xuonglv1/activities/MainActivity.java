package com.example.xuonglv1.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

//import com.example.xuonglv1.fragment.Frg_addUser;
import com.example.xuonglv1.R;
import com.example.xuonglv1.fragment.Frg_changePass;
import com.example.xuonglv1.fragment.Frg_loaiSanPham;
import com.example.xuonglv1.fragment.Frg_phieuXuatKho;
import com.example.xuonglv1.fragment.Frg_sanPham;
import com.example.xuonglv1.fragment.Frg_user;
import com.example.xuonglv1.fragment.Frg_thongKeTonKho;
import com.example.xuonglv1.fragment.Frg_thongKeXuatKho;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        //set toolbar thay thế cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //dùng fragment_PhieuMuon làm home
        FragmentManager manager = getSupportFragmentManager();
        Frg_phieuXuatKho frgPX = new Frg_phieuXuatKho();
        manager.beginTransaction()
                .replace(R.id.flContent,frgPX)
                .commit();
        NavigationView nv = findViewById(R.id.nvView);

        //Điều hướng navigation
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                if(item.getItemId()==R.id.nav_PhieuXuat){
                    setTitle("Quản lý Phiếu xuất kho");
                    Frg_phieuXuatKho frgPhieuXuatKho = new Frg_phieuXuatKho();
                    manager.beginTransaction()
                            .replace(R.id.flContent,frgPhieuXuatKho)
                            .commit();
                }
                if(item.getItemId()==R.id.nav_LoaiSanPham){
                    setTitle("Quản lý Loại sản phẩm");
                    Frg_loaiSanPham frgLoaiSanPham = new Frg_loaiSanPham();
                    manager.beginTransaction()
                            .replace(R.id.flContent,frgLoaiSanPham)
                            .commit();
                }
                if(item.getItemId()==R.id.nav_SanPham){
                    setTitle("Quản lý Sản phẩm");
                    Frg_sanPham frgSanPham = new Frg_sanPham();
                    manager.beginTransaction()
                            .replace(R.id.flContent,frgSanPham)
                            .commit();
                }
                if(item.getItemId()==R.id.nav_ThanhVien){
                    setTitle("Quản lý Thành viên");
                    Frg_user frgThanhVien = new Frg_user();
                    manager.beginTransaction()
                            .replace(R.id.flContent, frgThanhVien)
                            .commit();
                }
                if(item.getItemId()==R.id.sub_Export){
                    setTitle("Thống kê Xuất kho");
                    Frg_thongKeXuatKho frgThongKeXuatKho = new Frg_thongKeXuatKho();
                    manager.beginTransaction()
                            .replace(R.id.flContent, frgThongKeXuatKho)
                            .commit();
                }
                if(item.getItemId()==R.id.sub_Import){
                    setTitle("Thống kê Tồn kho");
                    Frg_thongKeTonKho frgThongKeTonKho = new Frg_thongKeTonKho();
                    manager.beginTransaction()
                            .replace(R.id.flContent, frgThongKeTonKho)
                            .commit();
                }
//                if(item.getItemId()==R.id.sub_AddUser){
//                    setTitle("Thêm người dùng");
//                    Frg_addUser frgAddUser = new Frg_addUser();
//                    manager.beginTransaction()
//                            .replace(R.id.flContent, frgAddUser)
//                            .commit();
//                }
                if(item.getItemId()==R.id.sub_Pass){
                    setTitle("Thay đổi mật khẩu");
                    Frg_changePass frgChangePass = new Frg_changePass();
                    manager.beginTransaction()
                            .replace(R.id.flContent, frgChangePass)
                            .commit();
                }
                if(item.getItemId()==R.id.sub_Logout){
//                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }
                drawer.closeDrawers();
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}
