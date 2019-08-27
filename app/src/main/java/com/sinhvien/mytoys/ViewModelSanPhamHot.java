package com.sinhvien.mytoys;

public class ViewModelSanPhamHot {
private String maSP ;
private String tenSP;
private int giaSP;
private String moTaSP;
private String hinhAnhSP;

    public ViewModelSanPhamHot(String maSP, String tenSP, int giaSP, String moTaSP, String hinhAnhSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.moTaSP = moTaSP;
        this.hinhAnhSP = hinhAnhSP;
    }

    public ViewModelSanPhamHot() {
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

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public String getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
    }
}
