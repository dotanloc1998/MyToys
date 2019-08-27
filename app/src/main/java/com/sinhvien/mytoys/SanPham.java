package com.sinhvien.mytoys;

public class SanPham {
    private int id;
    private String maSP;
    private String tenSP;
    private int gia;
    private String linkYT;
    private String loaiSP;
    private String hinhAnhSP;
    private String moTa;

    public SanPham(String maSP, String tenSP, int gia, String linkYT, String loaiSP, String hinhAnhSP, String moTa) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.linkYT = linkYT;
        this.loaiSP = loaiSP;
        this.hinhAnhSP = hinhAnhSP;
        this.moTa = moTa;
    }

    public SanPham(int id, String maSP, String tenSP, int gia, String linkYT, String loaiSP, String hinhAnhSP, String moTa) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.linkYT = linkYT;
        this.loaiSP = loaiSP;
        this.hinhAnhSP = hinhAnhSP;
        this.moTa = moTa;
    }

    public SanPham() {
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

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getLinkYT() {
        return linkYT;
    }

    public void setLinkYT(String linkYT) {
        this.linkYT = linkYT;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
