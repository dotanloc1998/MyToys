package com.sinhvien.mytoys;

public class SanPhamHot {
private int id;
private String maSP;

    public SanPhamHot(int id, String maSP) {
        this.id = id;
        this.maSP = maSP;
    }

    public SanPhamHot() {
    }

    public SanPhamHot(String maSP) {
        this.maSP = maSP;
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
}
