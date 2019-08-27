package com.sinhvien.mytoys;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quanlycuahang";
    private static final String KEY_ID = "_id";

    //Các bảng:

    //Bảng đăng nhập
    private static final String TABLE_DANGNHAP = "TDangNhap";
    private static final String KEY_TAIKHOAN = "taikhoan";

    //Bảng giỏ hàng
    private static final String TABLE_GIOHANG = "TGioHang";
    private static final String KEY_MASANPHAM = "masanpham";
    private static final String KEY_SOLUONG = "soluong";

    //Bảng loại sản phẩm
    private static final String TABLE_LOAISANPHAM = "TLoaiSanPham";
    private static final String KEY_MALOAISANPHAM = "maloaisanpham";
    private static final String KEY_TENLOAISANPHAM = "tenloaisanpham";
    private static final String KEY_MOTA = "mota";

    //Bảng người dùng
    private static final String TABLE_NGUOIDUNG = "TNguoiDung";
    private static final String KEY_MATKHAU = "matkhau";
    private static final String KEY_HOTEN = "hoten";
    private static final String KEY_DIACHI = "diachi";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SODIENTHOAI = "sodienthoai";
    private static final String KEY_TONGCHI = "tongchi";

    //Bảng sản phẩm
    private static final String TABLE_SANPHAM = "TSanPham";
    private static final String KEY_TENSANPHAM = "tensanpham";
    private static final String KEY_GIASANPHAM = "giasanpham";
    private static final String KEY_LINKYOUTUBE = "linkyoutube";
    private static final String KEY_HINHSANPHAM = "hinhsanpham";
    //Bảng sản phẩm đã bán
    private static final String TABLE_SANPHAMDABAN = "TSanPhamDaBan";
    private static final String KEY_SOTIEN = "sotien";

    //Bảng sản phẩm hot
    //Có id và mã sản phẩm đã được khởi tạo ở trên
    private static final String TABLE_SANPHAMHOT = "TSanPhamHot";

    Context context;

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String taoBangDangNhap = "CREATE TABLE " + TABLE_DANGNHAP
                + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAIKHOAN + " TEXT" + ")";

        String taoBangGioHang = "CREATE TABLE " + TABLE_GIOHANG
                + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MASANPHAM + " TEXT," + KEY_SOLUONG + " INTEGER," + KEY_TAIKHOAN + " TEXT" + ")";

        String taoBangLoaiSanPham = "CREATE TABLE " + TABLE_LOAISANPHAM
                + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MALOAISANPHAM + " TEXT," + KEY_TENLOAISANPHAM + " TEXT," + KEY_MOTA + " TEXT" + ")";

        String taoBangNguoiDung = "CREATE TABLE " + TABLE_NGUOIDUNG
                + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAIKHOAN + " TEXT," + KEY_MATKHAU + " TEXT,"
                + KEY_HOTEN + " TEXT," + KEY_DIACHI + " TEXT," + KEY_EMAIL + " TEXT," + KEY_SODIENTHOAI + " TEXT," + KEY_TONGCHI + " INTEGER" + ")";

        String taoBangSanPham = "CREATE TABLE " + TABLE_SANPHAM
                + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MASANPHAM + " TEXT," + KEY_TENSANPHAM + " TEXT,"
                + KEY_GIASANPHAM + " INTEGER," + KEY_LINKYOUTUBE + " TEXT," + KEY_MALOAISANPHAM + " TEXT," + KEY_HINHSANPHAM + " TEXT," + KEY_MOTA + " TEXT" + ")";

        String taoBangSanPhamDaBan = "CREATE TABLE " + TABLE_SANPHAMDABAN
                + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MASANPHAM + " TEXT,"
                + KEY_SOLUONG + " INTEGER," + KEY_TAIKHOAN + " TEXT," + KEY_SOTIEN + " INTEGER" + ")";

        String taoBangSanPhamHot = "CREATE TABLE " + TABLE_SANPHAMHOT
                + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MASANPHAM + " TEXT" + ")";

        sqLiteDatabase.execSQL(taoBangDangNhap);
        sqLiteDatabase.execSQL(taoBangGioHang);
        sqLiteDatabase.execSQL(taoBangLoaiSanPham);
        sqLiteDatabase.execSQL(taoBangNguoiDung);
        sqLiteDatabase.execSQL(taoBangSanPham);
        sqLiteDatabase.execSQL(taoBangSanPhamDaBan);
        sqLiteDatabase.execSQL(taoBangSanPhamHot);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Các hàm sẽ cần sử dụng
    public Cursor LayTatCaSanPhamHot() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor sanPhamHot = db.query
                (TABLE_SANPHAMHOT, null, null, null, null, null, null);
        if (sanPhamHot != null) {
            sanPhamHot.moveToFirst();
        }
        return sanPhamHot;
    }

    public Cursor LayTatCaLoaiSP() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor loaiSP = db.query(TABLE_LOAISANPHAM, null, null, null, null, null, null);
        if (loaiSP != null) {
            loaiSP.moveToFirst();
        }
        return loaiSP;
    }

    public Cursor LayTatCaSanPhamDaBan() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor sanPhamDaBan = db.query
                (TABLE_SANPHAMDABAN, null,
                        null, null, null, null, null);
        if (sanPhamDaBan != null) {
            sanPhamDaBan.moveToFirst();
        }
        return sanPhamDaBan;
    }

    public String LayTaiKhoanDangDangNhap() {
        SQLiteDatabase db = getWritableDatabase();
        String taiKhoan = "";
        Cursor taiKhoanDangDangNhap = db.query
                (TABLE_DANGNHAP, null, null, null, null, null, null);
        if (taiKhoanDangDangNhap.getCount() > 0) {
            taiKhoanDangDangNhap.moveToFirst();
            taiKhoan = taiKhoanDangDangNhap.getString(taiKhoanDangDangNhap.getColumnIndex(KEY_TAIKHOAN));
        }
        return taiKhoan;
    }

    public NguoiDung LayThongTinNguoiDungBangTaiKhoan(String maTaiKhoan) {
        SQLiteDatabase db = getWritableDatabase();
        NguoiDung nguoiDungTimDuoc = new NguoiDung();
        Cursor nguoiDungCanTim = db.query
                (TABLE_NGUOIDUNG, null, KEY_TAIKHOAN + " = ?", new String[]{maTaiKhoan}, null, null, null);
        if (nguoiDungCanTim.getCount() > 0) {
            nguoiDungCanTim.moveToFirst();

            nguoiDungTimDuoc.setId(nguoiDungCanTim.getInt(nguoiDungCanTim.getColumnIndex(KEY_ID)));
            nguoiDungTimDuoc.setTongChi(nguoiDungCanTim.getInt(nguoiDungCanTim.getColumnIndex(KEY_TONGCHI)));
            nguoiDungTimDuoc.setTaiKhoan(nguoiDungCanTim.getString(nguoiDungCanTim.getColumnIndex(KEY_TAIKHOAN)));
            nguoiDungTimDuoc.setMatKhau(nguoiDungCanTim.getString(nguoiDungCanTim.getColumnIndex(KEY_MATKHAU)));
            nguoiDungTimDuoc.setHoTen(nguoiDungCanTim.getString(nguoiDungCanTim.getColumnIndex(KEY_HOTEN)));
            nguoiDungTimDuoc.setDiaChi(nguoiDungCanTim.getString(nguoiDungCanTim.getColumnIndex(KEY_DIACHI)));
            nguoiDungTimDuoc.setEmail(nguoiDungCanTim.getString(nguoiDungCanTim.getColumnIndex(KEY_EMAIL)));
            nguoiDungTimDuoc.setSoDienThoai(nguoiDungCanTim.getString(nguoiDungCanTim.getColumnIndex(KEY_SODIENTHOAI)));

        }
        return nguoiDungTimDuoc;
    }

    public Cursor LayTatCaSanPhamBangLoaiSP(String loaiSP) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor sanPhamTheoLoai = db.query
                (TABLE_SANPHAM, null, KEY_MALOAISANPHAM + " = ?", new String[]{loaiSP}, null, null, null);
        if (sanPhamTheoLoai != null) {
            sanPhamTheoLoai.moveToFirst();
        }
        return sanPhamTheoLoai;
    }

    public Cursor LayGioHangTheoTaiKhoan(String maTaiKhoan) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor gioHangTheoNguoiDung = db.query
                (TABLE_GIOHANG, null, KEY_TAIKHOAN + " = ?", new String[]{maTaiKhoan}, null, null, null);
        if (gioHangTheoNguoiDung != null) {
            gioHangTheoNguoiDung.moveToFirst();
        }
        return gioHangTheoNguoiDung;
    }

    public Cursor LaySanPhamDaBanBangTaiKhoan(String maTaiKhoan) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor lichSuMuaHang = db.query
                (TABLE_SANPHAMDABAN, null, KEY_TAIKHOAN + " = ?", new String[]{maTaiKhoan}, null, null, null);
        if (lichSuMuaHang != null) {
            lichSuMuaHang.moveToFirst();
        }
        return lichSuMuaHang;
    }

    public SanPham LaySanPhamBangMaSanPham(String maSanPham) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor sanPhamCanTim = db.query
                (TABLE_SANPHAM, null, KEY_MASANPHAM + " = ?", new String[]{maSanPham}, null, null, null);
        SanPham sanPhamTimDuoc = new SanPham();
        if (sanPhamCanTim.getCount() > 0) {
            sanPhamCanTim.moveToFirst();

            sanPhamTimDuoc.setId(sanPhamCanTim.getInt(sanPhamCanTim.getColumnIndex(KEY_ID)));
            sanPhamTimDuoc.setGia(sanPhamCanTim.getInt(sanPhamCanTim.getColumnIndex(KEY_GIASANPHAM)));
            sanPhamTimDuoc.setMaSP(sanPhamCanTim.getString(sanPhamCanTim.getColumnIndex(KEY_MASANPHAM)));
            sanPhamTimDuoc.setTenSP(sanPhamCanTim.getString(sanPhamCanTim.getColumnIndex(KEY_TENSANPHAM)));
            sanPhamTimDuoc.setLinkYT(sanPhamCanTim.getString(sanPhamCanTim.getColumnIndex(KEY_LINKYOUTUBE)));
            sanPhamTimDuoc.setLoaiSP(sanPhamCanTim.getString(sanPhamCanTim.getColumnIndex(KEY_MALOAISANPHAM)));
            sanPhamTimDuoc.setHinhAnhSP(sanPhamCanTim.getString(sanPhamCanTim.getColumnIndex(KEY_HINHSANPHAM)));
            sanPhamTimDuoc.setMoTa(sanPhamCanTim.getString(sanPhamCanTim.getColumnIndex(KEY_MOTA)));
        } else {
            return null;
        }
        return sanPhamTimDuoc;
    }

    public void ThemGioHang(GioHang gioHangMoi) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor gioHangCuaTaiKhoan = db.query(TABLE_GIOHANG, null, KEY_MASANPHAM + " = ? AND " + KEY_TAIKHOAN + " = ?", new String[]{gioHangMoi.getMaSP(), gioHangMoi.getTaiKhoan()}, null, null, null);
        if (gioHangCuaTaiKhoan.getCount() == 0) {
            contentValues.put(KEY_MASANPHAM, gioHangMoi.getMaSP());
            contentValues.put(KEY_SOLUONG, gioHangMoi.getSoLuong());
            contentValues.put(KEY_TAIKHOAN, gioHangMoi.getTaiKhoan());
            db.insert(TABLE_GIOHANG, null, contentValues);
            Toast.makeText(context, R.string.themVaoGioThanhCong, Toast.LENGTH_LONG).show();
        } else {
            gioHangCuaTaiKhoan.moveToFirst();
            int soLuongSanPham = gioHangCuaTaiKhoan.getInt(gioHangCuaTaiKhoan.getColumnIndex(KEY_SOLUONG));
            if ((soLuongSanPham + gioHangMoi.getSoLuong()) > 10) {
                gioHangMoi.setSoLuong(10);
                CapNhatGioHang(gioHangMoi);
                Toast.makeText(context, R.string.soLuongQuyDinh, Toast.LENGTH_LONG).show();
            } else {
                gioHangMoi.setSoLuong(soLuongSanPham + gioHangMoi.getSoLuong());
                CapNhatGioHang(gioHangMoi);
                Toast.makeText(context, R.string.themVaoGioThanhCong, Toast.LENGTH_LONG).show();
            }
        }
        db.close();
    }

    public void CapNhatGioHang(GioHang gioHangCapNhat) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MASANPHAM, gioHangCapNhat.getMaSP());
        contentValues.put(KEY_SOLUONG, gioHangCapNhat.getSoLuong());
        contentValues.put(KEY_TAIKHOAN, gioHangCapNhat.getTaiKhoan());
        db.update(TABLE_GIOHANG, contentValues, KEY_MASANPHAM + " = " + "'" + gioHangCapNhat.getMaSP() + "'", null);
        db.close();
    }

    public void XoaGioHang(GioHang gioHangCanXoa) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_GIOHANG, KEY_ID + " = " + "'" + gioHangCanXoa.getId() + "'", null);
        db.close();
    }

    public void XoaGioHangKhiThanhToan(String maTaiKhoan) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_GIOHANG, KEY_TAIKHOAN + " = " + "'" + maTaiKhoan + "'", null);
        db.close();
    }

    public GioHang LaySanPhamTrongGioHang(String maSP, String tenDangNhap) {
        SQLiteDatabase db = getWritableDatabase();
        GioHang hangCanTim = new GioHang();
        Cursor gioHangCuaTaiKhoan = db.query(TABLE_GIOHANG, null, KEY_MASANPHAM + " = ? AND " + KEY_TAIKHOAN + " = ?", new String[]{maSP, tenDangNhap}, null, null, null);
        if (gioHangCuaTaiKhoan.getCount() > 0) {
            gioHangCuaTaiKhoan.moveToFirst();
            hangCanTim.setMaSP(gioHangCuaTaiKhoan.getString(gioHangCuaTaiKhoan.getColumnIndex(KEY_MASANPHAM)));
            hangCanTim.setSoLuong(gioHangCuaTaiKhoan.getInt(gioHangCuaTaiKhoan.getColumnIndex(KEY_SOLUONG)));
            hangCanTim.setId(gioHangCuaTaiKhoan.getInt(gioHangCuaTaiKhoan.getColumnIndex(KEY_ID)));
            hangCanTim.setTaiKhoan(gioHangCuaTaiKhoan.getString(gioHangCuaTaiKhoan.getColumnIndex(KEY_TAIKHOAN)));
        }
        return hangCanTim;
    }

    public void ThemNguoiDung(NguoiDung nguoiDungMoi) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TAIKHOAN, nguoiDungMoi.getTaiKhoan());
        contentValues.put(KEY_MATKHAU, nguoiDungMoi.getMatKhau());
        contentValues.put(KEY_HOTEN, nguoiDungMoi.getHoTen());
        contentValues.put(KEY_DIACHI, nguoiDungMoi.getDiaChi());
        contentValues.put(KEY_EMAIL, nguoiDungMoi.getEmail());
        contentValues.put(KEY_SODIENTHOAI, nguoiDungMoi.getSoDienThoai());
        contentValues.put(KEY_TONGCHI, nguoiDungMoi.getTongChi());
        db.insert(TABLE_NGUOIDUNG, null, contentValues);
        db.close();
    }

    public void CapNhatNguoiDung(NguoiDung nguoiDungCanSua) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TAIKHOAN, nguoiDungCanSua.getTaiKhoan());
        contentValues.put(KEY_MATKHAU, nguoiDungCanSua.getMatKhau());
        contentValues.put(KEY_HOTEN, nguoiDungCanSua.getHoTen());
        contentValues.put(KEY_DIACHI, nguoiDungCanSua.getDiaChi());
        contentValues.put(KEY_EMAIL, nguoiDungCanSua.getEmail());
        contentValues.put(KEY_SODIENTHOAI, nguoiDungCanSua.getSoDienThoai());
        contentValues.put(KEY_TONGCHI, nguoiDungCanSua.getTongChi());
        db.update(TABLE_NGUOIDUNG, contentValues, KEY_TAIKHOAN + " = " + "'" + nguoiDungCanSua.getTaiKhoan() + "'", null);
        db.close();
    }

    public void ThemSanPham(SanPham sanPhamMoi) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MASANPHAM, sanPhamMoi.getMaSP());
        values.put(KEY_TENSANPHAM, sanPhamMoi.getTenSP());
        values.put(KEY_GIASANPHAM, sanPhamMoi.getGia());
        values.put(KEY_LINKYOUTUBE, sanPhamMoi.getLinkYT());
        values.put(KEY_MALOAISANPHAM, sanPhamMoi.getLoaiSP());
        values.put(KEY_HINHSANPHAM, sanPhamMoi.getHinhAnhSP());
        values.put(KEY_MOTA, sanPhamMoi.getMoTa());
        db.insert(TABLE_SANPHAM, null, values);
        db.close();
    }

    public void ThemSanPhamHot(String maSPSeHot) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MASANPHAM, maSPSeHot);
        db.insert(TABLE_SANPHAMHOT, null, values);
        db.close();
    }

    public void ThemLoaiSP(LoaiSP loaiSPMoi) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MALOAISANPHAM, loaiSPMoi.getMaLoaiSP());
        values.put(KEY_TENLOAISANPHAM, loaiSPMoi.getTenLoaiSP());
        values.put(KEY_MOTA, loaiSPMoi.getMoTa());
        db.insert(TABLE_LOAISANPHAM, null, values);
        db.close();
    }

    public Cursor LaySanPhamTheoTen(String tenSP) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor sanPhamTest = db.query(TABLE_SANPHAM, null, KEY_TENSANPHAM + " = ?", new String[]{tenSP}, null, null, null);
        if (sanPhamTest != null) {
            sanPhamTest.moveToFirst();
        }
        return sanPhamTest;
    }

    public void ThemDangNhap(String tenDangNhap) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TAIKHOAN, tenDangNhap);
        db.insert(TABLE_DANGNHAP, null, values);
        db.close();
    }

    public void XoaDangNhap(String tenDangNhap) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_DANGNHAP, KEY_TAIKHOAN + " = " + "'" + tenDangNhap + "'", null);
        db.close();
    }

    public void ThemSanPhamDaBan(SanPhamDaBan sanPhamDaBan) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MASANPHAM, sanPhamDaBan.getMaSP());
        values.put(KEY_SOLUONG, sanPhamDaBan.getSoLuong());
        values.put(KEY_TAIKHOAN, sanPhamDaBan.getTaiKhoan());
        values.put(KEY_SOTIEN, sanPhamDaBan.getSoTien());
        db.insert(TABLE_SANPHAMDABAN, null, values);
        db.close();
    }
}
