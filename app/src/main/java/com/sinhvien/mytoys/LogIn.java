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

public class LogIn extends AppCompatActivity {
    EditText editTextTaiKhoan, editTextMatKhau;
    Button buttonDangNhap, buttonDangKy;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        AnhXa();
        buttonDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giaoDienDangKy = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(giaoDienDangKy);
            }
        });
        buttonDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextTaiKhoan.getText().toString().isEmpty() || editTextMatKhau.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.deTrong), Toast.LENGTH_SHORT).show();
                } else {
                    NguoiDung kq = db.LayThongTinNguoiDungBangTaiKhoan(editTextTaiKhoan.getText().toString());
                    if (kq.getTaiKhoan() != null) {
                        if (kq.getMatKhau().equals(editTextMatKhau.getText().toString())) {
                            db.ThemDangNhap(editTextTaiKhoan.getText().toString());
                            Toast.makeText(getApplicationContext(), getString(R.string.dangNhapThanhCong), Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(home);
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.dangNhapThatBai), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.khongTonTai), Toast.LENGTH_SHORT).show();
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

        editTextTaiKhoan = findViewById(R.id.editTextTaiKhoan);
        editTextMatKhau = findViewById(R.id.editTextMatKhau);
        buttonDangNhap = findViewById(R.id.buttonDangNhap);
        buttonDangKy = findViewById(R.id.buttonDangKy);
        db = new MyDB(getApplicationContext());
    }
}
