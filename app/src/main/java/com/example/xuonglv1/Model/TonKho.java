package com.example.xuonglv1.Model;

public class TonKho {
    private String tenSp;
    private int soLuong;

    public TonKho(String tenSp, int soLuong) {
        this.tenSp = tenSp;
        this.soLuong = soLuong;
    }

    public TonKho() {
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
