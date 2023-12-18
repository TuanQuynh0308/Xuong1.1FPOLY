package com.example.xuonglv1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.xuonglv1.Dao.UserDao;
import com.example.xuonglv1.Model.User;
import com.example.xuonglv1.R;
import com.example.xuonglv1.adapter.UserAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frg_user extends Fragment {

    ListView lstUser;
    ArrayList<User> list;
    static UserDao dao;
    UserAdapter adapter;
    User item;
    FloatingActionButton fltAdd;
    Dialog dialog;
    EditText edtUsername, edtPassword, edtNumberPhone, edtPosition, edtProfile, edtCreatedDate;
    Button btnSave,btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        lstUser = v.findViewById(R.id.lstUser);
        dao = new UserDao(getActivity());
        fltAdd = v.findViewById(R.id.fltAdd);
        capnhatLst();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });

        lstUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }

    void capnhatLst(){
        list = (ArrayList<User>) dao.getAll();
        adapter = new UserAdapter(getActivity(),this,list);
        lstUser.setAdapter(adapter);
    }

    public void xoa(final String Id){
        //sử dụng alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Chắc chắn xoá?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {
                dao.delete(Id);
                capnhatLst();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type){
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_user);
        edtUsername = dialog.findViewById(R.id.edtUsername);
        edtPassword = dialog.findViewById(R.id.edtPassword);
        edtNumberPhone = dialog.findViewById(R.id.edtNumberPhone);
        edtPosition = dialog.findViewById(R.id.edtPosition);
        edtProfile = dialog.findViewById(R.id.edtProfile);
        edtCreatedDate = dialog.findViewById(R.id.edtCreatedDate);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        //kiểm tra type insert 0 hay Update 1
        
        if(type !=0){
            edtUsername.setText(item.getUsername());
            edtPassword.setText(item.getPassword());
            edtNumberPhone.setText(item.getNumberPhone());
            edtPosition.setText(item.getPosition());
            edtProfile.setText(item.getProfile());
            edtCreatedDate.setText(item.getCreatedDate());
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new User();
                item.setUsername(edtUsername.getText().toString());
                item.setPassword(edtPassword.getText().toString());
                item.setNumberPhone(edtNumberPhone.getText().toString());
                item.setPosition(Integer.parseInt(edtPosition.getText().toString()));
                item.setProfile(edtProfile.getText().toString());
                item.setCreatedDate(edtCreatedDate.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        // type = 0 (insert)
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm lỗi", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //type = 1 (update)
                        item.setUsername(edtUsername.getText().toString());
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capnhatLst();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate(){
        int check = 1;
        if(edtUsername.getText().length() ==0 || edtPassword.getText().length()==0){
            Toast.makeText(getContext(), "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}