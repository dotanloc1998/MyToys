package com.sinhvien.mytoys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    TextView textViewTaiKhoan, textViewTongChi;
    EditText editTextHoTen, editTextDiaChi, editTextEmail, editTextSDT;
    Button buttonCapNhat, buttonQuayLai, buttonDangXuat;
    ListView listViewLichSuMua;
    MyDB db;
    Cursor sanPhamDaMua;
    ArrayList<ViewModelHangDaMua> hangDaMuas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        AnhXa();
        final NguoiDung thongTin = db.LayThongTinNguoiDungBangTaiKhoan(HomeActivity.nguoiDungDangDangNhap);
        if (thongTin != null) {
            textViewTaiKhoan.setText(thongTin.getTaiKhoan());
            textViewTongChi.setText("" + thongTin.getTongChi());
            editTextHoTen.setText(thongTin.getHoTen());
            editTextDiaChi.setText(thongTin.getDiaChi());
            editTextEmail.setText(thongTin.getEmail());
            editTextSDT.setText(thongTin.getSoDienThoai());

            sanPhamDaMua = db.LaySanPhamDaBanBangTaiKhoan(thongTin.getTaiKhoan());

            if (sanPhamDaMua.getCount()>0) {
                do {
                    ViewModelHangDaMua hangDaMua;
                    SanPham sanPham = db.LaySanPhamBangMaSanPham(sanPhamDaMua.getString(sanPhamDaMua.getColumnIndex("masanpham")));
                    int soLuong = sanPhamDaMua.getInt(sanPhamDaMua.getColumnIndex("soluong"));
                    int thanhTien = sanPhamDaMua.getInt(sanPhamDaMua.getColumnIndex("sotien"));
                    hangDaMua = new ViewModelHangDaMua(sanPham.getTenSP(), soLuong, thanhTien, sanPham.getHinhAnhSP(), sanPham.getMaSP());
                    hangDaMuas.add(hangDaMua);
                }
                while (sanPhamDaMua.moveToNext());
            }
            if (hangDaMuas != null) {
                HangDaMuaApdapter hangDaMuaApdapter = new HangDaMuaApdapter(getApplicationContext(), hangDaMuas);
                listViewLichSuMua.setAdapter(hangDaMuaApdapter);
            }


        }

        buttonCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thongTin != null) {
                    if (editTextHoTen.getText().toString().isEmpty() || editTextDiaChi.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() || editTextSDT.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), getString(R.string.deTrong), Toast.LENGTH_SHORT).show();
                    } else {
                        NguoiDung nguoiDungMoi = new NguoiDung(thongTin.getTaiKhoan(), thongTin.getMatKhau(), editTextHoTen.getText().toString(), editTextDiaChi.getText().toString(), editTextEmail.getText().toString(), editTextSDT.getText().toString(), thongTin.getTongChi());
                        db.CapNhatNguoiDung(nguoiDungMoi);
                        Toast.makeText(getApplicationContext(), getString(R.string.capNhatThongTin), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(menu);
            }
        });

        buttonDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thongTin != null) {
                    db.XoaDangNhap(thongTin.getTaiKhoan());
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
        textViewTaiKhoan = findViewById(R.id.textViewTaiKhoan);
        textViewTongChi = findViewById(R.id.textViewTongChi);
        editTextHoTen = findViewById(R.id.editTextHoTen);
        editTextDiaChi = findViewById(R.id.editTextDiaChi);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSDT = findViewById(R.id.editTextSDT);
        buttonCapNhat = findViewById(R.id.buttonCapNhat);
        buttonQuayLai = findViewById(R.id.buttonQuayLai);
        buttonDangXuat = findViewById(R.id.buttonDangXuat);
        listViewLichSuMua = findViewById(R.id.listViewLichSuMua);
        hangDaMuas = new ArrayList<>();

    }
}
