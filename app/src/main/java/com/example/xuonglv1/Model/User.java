package com.example.xuonglv1.Model;

public class User {
    private String username;//khóa chính
    private String password;
    private String numberPhone;
    private String postion;//chức v 1|0
    private String avatar;
    private String profile;//giới thiệu tóm tắt
    private String lastLogin;
    private String createdDate;//ngày tạo tk
    private String lastAction;//hành động cuối trên hệ thống
    //create table USER (username text primary key, password text,numberphone text, position text


    public User() {
    }

    public User(String username, String password, String numberPhone, String postion, String avatar, String profile, String lastLogin, String createdDate, String lastAction) {
        this.username = username;
        this.password = password;
        this.numberPhone = numberPhone;
        this.postion = postion;
        this.avatar = avatar;
        this.profile = profile;
        this.lastLogin = lastLogin;
        this.createdDate = createdDate;
        this.lastAction = lastAction;
    }

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

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }
}
