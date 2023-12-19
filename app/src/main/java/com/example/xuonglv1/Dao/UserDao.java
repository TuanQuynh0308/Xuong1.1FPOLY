package com.example.xuonglv1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.xuonglv1.Model.User;
import com.example.xuonglv1.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private SQLiteDatabase db;

    public UserDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert (User obj){
        ContentValues values = new ContentValues();
        values.put("username",obj.getUsername());
        values.put("password",obj.getPassword());
        values.put("numberPhone",obj.getNumberPhone());
        values.put("position",obj.getPosition());
        values.put("profile",obj.getProfile());
        values.put("createdDate",obj.getCreatedDate());

        return db.insert("User", null, values);
    }

    public int updatePass(User obj){
        ContentValues values = new ContentValues();

        values.put("username",obj.getUsername());
        values.put("password",obj.getPassword());

        return db.update("User", values, "username=?", new String[]{obj.getUsername()});
    }

    public int update(User obj){
        ContentValues values = new ContentValues();
        values.put("username",obj.getUsername());
        values.put("password",obj.getPassword());
        values.put("numberPhone",obj.getNumberPhone());
        values.put("position",obj.getPosition());
        values.put("profile",obj.getProfile());
        values.put("createdDate",obj.getCreatedDate());
        return db.update("User", values, "username=?", new String[]{obj.getUsername()});
    }

    public int delete(String username){
        return db.delete("User", "username=?", new String[]{username});
    }

    //get All data
    public List<User> getAll(){
        String sql = "SELECT * FROM User";
        return getData(sql);
    }

    //get data theo id
    public User getID(String username){
        String sql ="SELECT * FROM User WHERE username=?";
        List<User> list = getData(sql,username);
        return list.get(0);
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    private List<User> getData(String sql, String...selectionArgs){
        ArrayList<User> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            User obj = new User();
            obj.setUsername(c.getString(c.getColumnIndex("username")));
            obj.setPassword(c.getString(c.getColumnIndex("password")));
            obj.setNumberPhone(c.getString(c.getColumnIndex("numberPhone")));
            obj.setPosition(Integer.parseInt(c.getString(c.getColumnIndex("position"))));
            obj.setProfile(c.getString(c.getColumnIndex("profile")));
            obj.setCreatedDate(c.getString(c.getColumnIndex("createdDate")));
            list.add(obj);
        }
        return list;
    }

    //check login
    public int checkLogin(String username, String password){
        String sql = "SELECT * FROM User WHERE username=? AND password=?";
        List<User> list = getData(sql,username,password);
        if (list.size() == 0)
            return -1;
        return 1;

    }
}
