package com.example.xuonglv1;

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

import com.example.xuonglv1.fragment.frg_addUser;
import com.example.xuonglv1.fragment.frg_changePass;
import com.example.xuonglv1.fragment.frg_loaiSanPham;
import com.example.xuonglv1.fragment.frg_phieuXuatKho;
import com.example.xuonglv1.fragment.frg_sanPham;
import com.example.xuonglv1.fragment.frg_thanhVien;
import com.example.xuonglv1.fragment.frg_thongKeTonKho;
import com.example.xuonglv1.fragment.frg_thongKeXuatKho;
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
        frg_phieuXuatKho frgPX = new frg_phieuXuatKho();
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
                    frg_phieuXuatKho frgPhieuXuatKho = new frg_phieuXuatKho();
                    manager.beginTransaction()
                            .replace(R.id.flContent,frgPhieuXuatKho)
                            .commit();
                }
                if(item.getItemId()==R.id.nav_LoaiSanPham){
                    setTitle("Quản lý Loại sản phẩm");
                    frg_loaiSanPham frgLoaiSanPham = new frg_loaiSanPham();
                    manager.beginTransaction()
                            .replace(R.id.flContent,frgLoaiSanPham)
                            .commit();
                }
                if(item.getItemId()==R.id.nav_SanPham){
                    setTitle("Quản lý Sản phẩm");
                    frg_sanPham frgSanPham = new frg_sanPham();
                    manager.beginTransaction()
                            .replace(R.id.flContent,frgSanPham)
                            .commit();
                }
                if(item.getItemId()==R.id.nav_ThanhVien){
                    setTitle("Quản lý Thành viên");
                    frg_thanhVien frgThanhVien = new frg_thanhVien();
                    manager.beginTransaction()
                            .replace(R.id.flContent, frgThanhVien)
                            .commit();
                }
                if(item.getItemId()==R.id.sub_Export){
                    setTitle("Thống kê Xuất kho");
                    frg_thongKeXuatKho frgThongKeXuatKho = new frg_thongKeXuatKho();
                    manager.beginTransaction()
                            .replace(R.id.flContent, frgThongKeXuatKho)
                            .commit();
                }
                if(item.getItemId()==R.id.sub_Import){
                    setTitle("Thống kê Tồn kho");
                    frg_thongKeTonKho frgThongKeTonKho = new frg_thongKeTonKho();
                    manager.beginTransaction()
                            .replace(R.id.flContent, frgThongKeTonKho)
                            .commit();
                }
                if(item.getItemId()==R.id.sub_AddUser){
                    setTitle("Thêm người dùng");
                    frg_addUser frgAddUser = new frg_addUser();
                    manager.beginTransaction()
                            .replace(R.id.flContent, frgAddUser)
                            .commit();
                }
                if(item.getItemId()==R.id.sub_Pass){
                    setTitle("Thay đổi mật khẩu");
                    frg_changePass frgChangePass = new frg_changePass();
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
