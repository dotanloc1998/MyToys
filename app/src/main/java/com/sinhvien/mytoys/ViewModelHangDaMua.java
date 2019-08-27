package com.sinhvien.mytoys;

public class ViewModelHangDaMua {
    private String tenSP;
    private int soLuong , gia;
    private String hinhSP;
    private String maSP;

    public ViewModelHangDaMua(String tenSP, int soLuong, int gia, String hinhSP, String maSP) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.gia = gia;
        this.hinhSP = hinhSP;
        this.maSP = maSP;
    }

    public ViewModelHangDaMua() {
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }
}
