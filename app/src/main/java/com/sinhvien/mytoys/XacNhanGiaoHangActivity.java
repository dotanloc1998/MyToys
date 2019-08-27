package com.sinhvien.mytoys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class XacNhanGiaoHangActivity extends AppCompatActivity {
    EditText editTextHoTen, editTextDiaChi, editTextEmail, editTextSDT;
    TextView textViewTongTien;
    Button buttonXacNhan, buttonQuayLai;
    MyDB db;
    int tongTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_giao_hang);
        AnhXa();

        NguoiDung nguoiDung = db.LayThongTinNguoiDungBangTaiKhoan(HomeActivity.nguoiDungDangDangNhap);
        if (nguoiDung.getTaiKhoan() != null) {
            editTextHoTen.setText(nguoiDung.getHoTen());
            editTextDiaChi.setText(nguoiDung.getDiaChi());
            editTextEmail.setText(nguoiDung.getEmail());
            editTextSDT.setText(nguoiDung.getSoDienThoai());
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewTongTien.setText(getText(R.string.tongTien) + " " + decimalFormat.format(CartActivity.tongTien));

        buttonQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gioHang = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(gioHang);
            }
        });

        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextHoTen.getText().toString().isEmpty() || editTextDiaChi.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() || editTextSDT.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.deTrong), Toast.LENGTH_SHORT).show();
                } else {
                    for (ViewModelGioHang hangTrongGio : CartActivity.gioHangs) {
                        SanPhamDaBan sanPhamDaBan = new SanPhamDaBan(hangTrongGio.getMaSP(), hangTrongGio.getSoLuongSP(), HomeActivity.nguoiDungDangDangNhap, hangTrongGio.getGia());
                        db.ThemSanPhamDaBan(sanPhamDaBan);
                        tongTien += hangTrongGio.getGia();
                    }
                    db.XoaGioHangKhiThanhToan(HomeActivity.nguoiDungDangDangNhap);
                    NguoiDung suaLaiTongChi = db.LayThongTinNguoiDungBangTaiKhoan(HomeActivity.nguoiDungDangDangNhap);
                    suaLaiTongChi.setTongChi(suaLaiTongChi.getTongChi() + tongTien);
                    db.CapNhatNguoiDung(suaLaiTongChi);
                    Toast.makeText(getApplicationContext(), getString(R.string.thanhToanThanhCong), Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(home);
                }
            }
        });
    }

    private void AnhXa() {
        //Đổi màu Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.registerColor)));

        //Đổi màu Status bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.registerColorDark));

        db = new MyDB(getApplicationContext());
        editTextHoTen = findViewById(R.id.editTextHoTen);
        editTextDiaChi = findViewById(R.id.editTextDiaChi);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSDT = findViewById(R.id.editTextSDT);
        textViewTongTien = findViewById(R.id.textViewTongTien);
        buttonXacNhan = findViewById(R.id.buttonXacNhan);
        buttonQuayLai = findViewById(R.id.buttonQuayLai);

    }
}
