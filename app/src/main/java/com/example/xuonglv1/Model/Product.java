package com.example.xuonglv1.Model;

public class Product {
    private int id;//khóa chính
    private String name;//tên sp
    private int quantity;//số lượng sp
    private String price;//giá sp
    private String photo;//ảnh của sp khi vào kho
    private String userID;//id người tạo sản phẩm

    public Product() {
    }

    public Product(int id, String name, int quantity, String price, String photo, String userID) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.photo = photo;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
