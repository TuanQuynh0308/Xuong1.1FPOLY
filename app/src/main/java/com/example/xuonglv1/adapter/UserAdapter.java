package com.example.xuonglv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.xuonglv1.Model.User;
import com.example.xuonglv1.R;
import com.example.xuonglv1.fragment.Frg_user;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    Frg_user frg_user;
    private ArrayList<User> list;
    TextView txtUsername, txtNumberPhone, txtPosition, txtProfile, txtLastLogin, txtCreateDate;
    ImageView btnDelete;

    public UserAdapter(@NonNull Context context, Frg_user frg_user, ArrayList<User> list) {
        super(context, 0, list);
        this.context = context;
        this.frg_user = frg_user;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_user, null);
        }
        final User item = list.get(position);
        if (item != null) {
            txtUsername = v.findViewById(R.id.txtUsername);
            txtUsername.setText("Tên đăng nhập: " + item.getUsername());
            txtNumberPhone = v.findViewById(R.id.txtNumberPhone);
            txtNumberPhone.setText("Số điện thoại: " + item.getNumberPhone());
            txtPosition = v.findViewById(R.id.txtPosition);
            if (item.getPosition() == 0) {
                txtPosition.setText("Chức vụ: Admin");
            } else {
                txtPosition.setText("Chức vụ: Thủ kho");
            }
            txtProfile = v.findViewById(R.id.txtProfile);
            txtProfile.setText("Giới thiệu: " + item.getProfile());
            txtLastLogin = v.findViewById(R.id.txtLastLogin);
            txtProfile.setText("Đăng nhập lần cuối: " + item.getLastLogin());
            txtCreateDate = v.findViewById(R.id.txtCreatedDate);
            txtCreateDate.setText("Ngày tạo: " + item.getCreatedDate());
            btnDelete = v.findViewById(R.id.btnDelete);

        }
        return v;
    }
}
