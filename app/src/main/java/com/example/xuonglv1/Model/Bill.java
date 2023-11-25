package com.example.xuonglv1.Model;

public class Bill {
    private int id;
    private int productID; //khóa ngoại: mã sản phẩm
    private String name;//tên sp
    private int quantity;//>0, nhập kho, <0, xuất kho
    private String createdByUser;//tạo bởi ai
    private String createdDate;//ngày tạo hóa đơn
    private String note;//ghi chú trường hợp đặc biệt

    public Bill() {
    }

    public Bill(int id, int productID, String name, int quantity, String createdByUser, String createdDate, String note) {
        this.id = id;
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.createdByUser = createdByUser;
        this.createdDate = createdDate;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    //
}
