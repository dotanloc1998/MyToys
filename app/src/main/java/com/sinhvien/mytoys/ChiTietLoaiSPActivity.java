package com.sinhvien.mytoys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

public class ChiTietLoaiSPActivity extends AppCompatActivity {
    ListView listViewSanPham;
    Cursor sanPham;
    MyDB db;
    SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_loai_sp);
        AnhXa();

        listViewSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor duocChon = (Cursor) listViewSanPham.getItemAtPosition(i);
                SanPham sanPhamDuocChon = db.LaySanPhamBangMaSanPham(duocChon.getString(duocChon.getColumnIndex("masanpham")));
                startActivity(HomeActivity.HienThiChiTietSanPham(sanPhamDuocChon, getApplicationContext()));
            }
        });
    }

    private void AnhXa() {
        //Đổi màu Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.cateColor)));

        //Đổi màu Status bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.cateColorDark));
        listViewSanPham = findViewById(R.id.listViewSanPham);
        db = new MyDB(getApplicationContext());
        Bundle thongTin = getIntent().getExtras();
        String maLoaiSP = thongTin.getString("loaisp");
        sanPham = db.LayTatCaSanPhamBangLoaiSP(maLoaiSP);
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(), sanPham, 0);
        listViewSanPham.setAdapter(sanPhamAdapter);
    }
}
