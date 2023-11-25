package com.example.xuonglv1.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public ProgressDialog loading;


    public void showLoading(String message){
        if(loading == null){
            loading = new ProgressDialog(BaseActivity.this);
            loading.setMessage(message);
        }
    }
    public void hideLoading(){
        if(loading != null) loading.hide();
    }
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void navigateScreen(Class target){
        Intent intent = new Intent(this, target);
    }
    public abstract int setLayout();

    public abstract void initViews();

    public abstract void initActions();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        //init view
        initViews();
        //init action
        initActions();

    }
}
