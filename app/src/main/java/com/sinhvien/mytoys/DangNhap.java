package com.sinhvien.mytoys;

public class DangNhap {
    private int id;
    private String taiKhoan;

    public DangNhap(int id, String taiKhoan) {
        this.id = id;
        this.taiKhoan = taiKhoan;
    }

    public DangNhap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
