package com.sinhvien.mytoys;

public class NguoiDung {
    private int id;
    private String taiKhoan;
    private String matKhau;
    private String hoTen;
    private String diaChi;
    private String email;
    private String soDienThoai;
    private int tongChi;

    public NguoiDung(int id, String taiKhoan, String matKhau, String hoTen, String diaChi, String email, String soDienThoai, int tongChi) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.tongChi = tongChi;
    }

    public NguoiDung(String taiKhoan, String matKhau, String hoTen, String diaChi, String email, String soDienThoai, int tongChi) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.tongChi = tongChi;
    }

    public NguoiDung() {
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

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getTongChi() {
        return tongChi;
    }

    public void setTongChi(int tongChi) {
        this.tongChi = tongChi;
    }
}
