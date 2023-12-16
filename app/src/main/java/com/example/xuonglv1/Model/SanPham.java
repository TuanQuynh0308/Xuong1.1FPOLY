package com.example.xuonglv1.Model;

public class SanPham {
    private int maSP;
    private String tenSP;
    private int giaSp,soLuong,maLoai;
    private byte[] anhSP;

    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, int giaSp, int soLuong, int maLoai, byte[] anhSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSp = giaSp;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
        this.anhSP = anhSP;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(int giaSp) {
        this.giaSp = giaSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public byte[] getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(byte[] anhSP) {
        this.anhSP = anhSP;
    }
}
