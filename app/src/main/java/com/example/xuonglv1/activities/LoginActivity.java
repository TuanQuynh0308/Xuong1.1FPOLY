package com.example.xuonglv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xuonglv1.Dao.UserDao;
import com.example.xuonglv1.Model.User;
import com.example.xuonglv1.R;

public class LoginActivity extends AppCompatActivity {

    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);

        dao = new UserDao(this);

        //đọc user, pass trong SharedPreferences
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edUserName.setText(preferences.getString("userName", ""));
        edPassword.setText((preferences.getString("passWord", "")));
        chkRememberPass.setChecked(preferences.getBoolean("REMEMBER",false));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUserName.setText("");
                edPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    public void checkLogin(){
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if(strUser.isEmpty() || strPass.isEmpty()){
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        }else{
            if (dao.checkLogin(strUser,strPass)>0){
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                remembeUser(strUser,strPass,chkRememberPass.isChecked());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",strUser);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void remembeUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            //xoá tình trạng đã lưu trước đó
            edit.clear();
        }else{
            //lưu dữ liệu
            edit.putString("userName",u);
            edit.putString("passWord",p);
            edit.putBoolean("REMEMBER",status);
        }
        //lưu lại toàn bộ
        edit.commit();
    }
}