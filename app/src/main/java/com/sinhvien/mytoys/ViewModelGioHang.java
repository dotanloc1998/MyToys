package com.sinhvien.mytoys;

public class ViewModelGioHang {
    private String maSP;
    private String tenSP;
    private int soLuongSP;
    private String hinhSP;
    private int gia;

    public ViewModelGioHang(String maSP, String tenSP, int soLuongSP, String hinhSP, int gia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuongSP = soLuongSP;
        this.hinhSP = hinhSP;
        this.gia = gia;
    }

    public ViewModelGioHang() {
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
