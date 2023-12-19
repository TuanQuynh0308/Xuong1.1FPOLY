package com.example.xuonglv1.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.xuonglv1.Dao.UserDao;
import com.example.xuonglv1.Model.User;
import com.example.xuonglv1.R;

public class Frg_changePass extends Fragment {
    EditText edOldPass, edNewPass, edRePass;
    Button btnSave, btnCancle;
    UserDao dao;

    public Frg_changePass() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass,container,false);
        edOldPass= view.findViewById(R.id.edOldPass);
        edNewPass= view.findViewById(R.id.edNewPass);
        edRePass= view.findViewById(R.id.edRePass);
        btnSave= view.findViewById(R.id.btnSave);
        btnCancle= view.findViewById(R.id.btnCancel);

        dao = new UserDao(getActivity());

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edOldPass.setText("");
                edNewPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //đọc user, pass trong SharePreferences
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");
                if (validate() > 0) {
                    User user1 = dao.getID(user);
                    user1.setPassword(edNewPass.getText().toString());

                    if (dao.updatePass(user1) > 0) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edOldPass.setText("");
                        edNewPass.setText("");
                        edRePass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    public int validate(){
        int check =1;
        if(edOldPass.getText().length()==0 || edNewPass.getText().length()==0 || edRePass.getText().length()==0){
            Toast.makeText(getActivity(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }else{
            //đọc user, pass trong SharePreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String oldPass = pref.getString("PASSWORD","");
            String newPass = edNewPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!oldPass.equals(edOldPass.getText().toString())){
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!newPass.equals(rePass)){
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}