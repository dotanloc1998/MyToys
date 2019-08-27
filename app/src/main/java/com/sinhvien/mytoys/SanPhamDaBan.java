package com.sinhvien.mytoys;

public class SanPhamDaBan {
    private int id;
    private String maSP;
    private int soLuong;
    private String taiKhoan;
    private int soTien;

    public SanPhamDaBan(int id, String maSP, int soLuong, String taiKhoan, int soTien) {
        this.id = id;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.taiKhoan = taiKhoan;
        this.soTien = soTien;
    }

    public SanPhamDaBan(String maSP, int soLuong, String taiKhoan, int soTien) {
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.taiKhoan = taiKhoan;
        this.soTien = soTien;
    }

    public SanPhamDaBan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}
