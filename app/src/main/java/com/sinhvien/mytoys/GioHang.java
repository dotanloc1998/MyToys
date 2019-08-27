package com.sinhvien.mytoys;

public class GioHang {
    private int id;
    private String maSP;
    private int soLuong;
    private String taiKhoan;

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

    public GioHang() {
    }

    public GioHang(String maSP, int soLuong, String taiKhoan) {
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.taiKhoan = taiKhoan;
    }

    public GioHang(int id, String maSP, int soLuong, String taiKhoan) {
        this.id = id;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.taiKhoan = taiKhoan;
    }
}
