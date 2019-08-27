package com.sinhvien.mytoys;

public class ViewModelSanPhamDaBan {
    private String tenSP;
    private int soLuong;
    private int soTien;

    public ViewModelSanPhamDaBan() {
    }

    public ViewModelSanPhamDaBan(String tenSP, int soLuong, int soTien) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.soTien = soTien;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}
