package com.example.xuonglv1.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xuonglv1.Model.User;
import com.example.xuonglv1.activity.database.DbHelper;

import java.util.ArrayList;

public class userDao {
    private  final DbHelper dbHelper;

    public userDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<User> selectAll(){
        ArrayList<User>list= new ArrayList<>();
        SQLiteDatabase database =dbHelper.getReadableDatabase();
        try{
            Cursor cursor =database.rawQuery("select * from User", null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
            }while (!cursor.isAfterLast()){
                User user = new User();
                user.setUsername(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                user.setNumberPhone(cursor.getString(2));
                user.setPostion(cursor.getString(3));
                user.setAvatar(cursor.getString(4));
                user.setProfile(cursor.getString(5));
                user.setLastLogin(cursor.getString(6));
                user.setCreatedDate(cursor.getString(7));
                user.setLastAction(cursor.getString(8));
                list.add(user);
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
