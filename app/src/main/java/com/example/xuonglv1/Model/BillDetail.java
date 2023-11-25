package com.example.xuonglv1.Model;

public class BillDetail {
    private int id;
    private int billID;//Khóa ngoại ở bản bill
    private int quantity;//só lượng sp
    private String createdDate;

    public BillDetail() {
    }

    public BillDetail(int id, int billID, int quantity, String createdDate) {
        this.id = id;
        this.billID = billID;
        this.quantity = quantity;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
