package com.example.xuonglv1.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.xuonglv1.R;
import com.example.xuonglv1.adapter.viewpage_adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Frg_thongKeXuat_Nhap extends Fragment {

    TabLayout tabLayout;
    viewpage_adapter adapter;
    ViewPager2 viewPager2;
    ImageView btnAdd;

    public Frg_thongKeXuat_Nhap() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_thong_ke_xuat_nhap, container, false);

        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.black));


        tabLayout=v.findViewById(R.id.tablayout);
        viewPager2=v.findViewById(R.id.viewpage2);
        adapter=new viewpage_adapter(getActivity());
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: tab.setText("Nhập");break;
                    case 1: tab.setText("Xuất");break;
                }
            }
        }).attach();
        return v;
    }
}