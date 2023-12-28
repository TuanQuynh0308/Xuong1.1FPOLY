package com.example.xuonglv1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.xuonglv1.fragment.Frg_thongKeNhapKho;
import com.example.xuonglv1.fragment.Frg_thongKeXuatKho;

public class viewpage_adapter extends FragmentStateAdapter {
    public viewpage_adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new Frg_thongKeNhapKho();
            case 1: return new Frg_thongKeXuatKho();
        }
        return new Frg_thongKeNhapKho();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
