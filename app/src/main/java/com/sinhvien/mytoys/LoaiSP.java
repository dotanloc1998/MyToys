package com.sinhvien.mytoys;

public class LoaiSP {
    private int id;
    private String maLoaiSP;
    private String tenLoaiSP;
    private String moTa;

    public LoaiSP(int id, String maLoaiSP, String tenLoaiSP, String moTa) {
        this.id = id;
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
        this.moTa = moTa;
    }

    public LoaiSP(String maLoaiSP, String tenLoaiSP, String moTa) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
        this.moTa = moTa;
    }

    public LoaiSP() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
