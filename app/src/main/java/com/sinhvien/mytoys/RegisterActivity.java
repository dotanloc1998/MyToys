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
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextTaiKhoan, editTextMatKhau, editTextNhapLaiMatKhau, editTextHoTen, editTextDiaChi, editTextEmail, editTextSDT;
    Button buttonDangKy, buttonHuyBo;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        buttonDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextTaiKhoan.getText().toString().isEmpty() || editTextMatKhau.getText().toString().isEmpty() || editTextNhapLaiMatKhau.getText().toString().isEmpty() || editTextHoTen.getText().toString().isEmpty() || editTextDiaChi.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() || editTextSDT.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.deTrong), Toast.LENGTH_SHORT).show();
                } else {
                    NguoiDung kq = db.LayThongTinNguoiDungBangTaiKhoan(editTextTaiKhoan.getText().toString());
                    if (kq.getTaiKhoan() == null) {
                        if (editTextMatKhau.getText().toString().equals(editTextNhapLaiMatKhau.getText().toString())) {
                            NguoiDung nguoiDung = new NguoiDung(editTextTaiKhoan.getText().toString(), editTextMatKhau.getText().toString(), editTextHoTen.getText().toString(), editTextDiaChi.getText().toString(), editTextEmail.getText().toString(), editTextSDT.getText().toString(), 0);
                            db.ThemNguoiDung(nguoiDung);
                            Toast.makeText(getApplicationContext(), getString(R.string.dangKyThanhCong), Toast.LENGTH_SHORT).show();
                            Intent logIn = new Intent(getApplicationContext(), LogIn.class);
                            startActivity(logIn);
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.khongTrungMatKhau), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.taiKhoanDaTonTai), Toast.LENGTH_SHORT).show();
                    }
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
        editTextTaiKhoan = findViewById(R.id.editTextTaiKhoan);
        editTextMatKhau = findViewById(R.id.editTextMatKhau);
        editTextNhapLaiMatKhau = findViewById(R.id.editTextNhapLaiMatKhau);
        editTextHoTen = findViewById(R.id.editTextHoTen);
        editTextDiaChi = findViewById(R.id.editTextDiaChi);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSDT = findViewById(R.id.editTextSDT);
        buttonDangKy = findViewById(R.id.buttonDangKy);
        buttonHuyBo = findViewById(R.id.buttonHuyBo);
    }
}
