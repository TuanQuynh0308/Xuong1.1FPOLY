package com.example.xuonglv1.Model;

public class User {
    private String username;//khóa chính
    private String password;
    private String numberPhone;
    private Integer position;//chức v 1|0
    private String profile;//giới thiệu tóm tắt
    private String lastLogin;
    private String createdDate;//ngày tạo tk
    //create table USER (username text primary key, password text,numberphone text, position text


    public User() {
    }

    public User(String username, String password, String numberPhone, Integer position, String profile, String createdDate) {
        this.username = username;
        this.password = password;
        this.numberPhone = numberPhone;
        this.position = position;
        this.profile = profile;
        this.createdDate = createdDate;
    }

//    public User(String username, String password, String numberPhone, Integer position, String profile, String lastLogin, String createdDate) {
//        this.username = username;
//        this.password = password;
//        this.numberPhone = numberPhone;
//        this.position = position;
//        this.profile = profile;
//        this.lastLogin = lastLogin;
//        this.createdDate = createdDate;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

//    public String getLastLogin() {
//        return lastLogin;
//    }
//
//    public void setLastLogin(String lastLogin) {
//        this.lastLogin = lastLogin;
//    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
