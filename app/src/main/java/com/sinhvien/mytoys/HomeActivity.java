package com.sinhvien.mytoys;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ViewFlipper viewFlipperKhuyenMai;
    ListView listViewSanPhamHot;
    MyDB db;
    public static String nguoiDungDangDangNhap;
    Cursor duLieuSanPhamHot;
    ArrayList<ViewModelSanPhamHot> sanPhamHots;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_search:
                    Intent openSearch = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(openSearch);
                    return true;
                case R.id.navigation_category:
                    Intent openCat = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(openCat);
                    return true;
                case R.id.navigation_cart:
                    Intent openCart = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(openCart);
                    return true;
                case R.id.navigation_menu:
                    Intent openMenu = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(openMenu);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new MyDB(getApplicationContext());

        nguoiDungDangDangNhap = db.LayTaiKhoanDangDangNhap();
        listViewSanPhamHot = findViewById(R.id.listViewSanPhamHot);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Đổi màu Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.homeColor)));

        //Đổi màu Status bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.homeColorDark));

        //Focus vào item được chọn
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        viewFlipperKhuyenMai = findViewById(R.id.viewFlipperKhuyenMai);
        //Đặt 2s sẽ chuyển hình
        viewFlipperKhuyenMai.setFlipInterval(2000);
        //Bắt đầu chuyển
        viewFlipperKhuyenMai.startFlipping();

        HienThiSanPham();

        listViewSanPhamHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViewModelSanPhamHot tam = (ViewModelSanPhamHot) listViewSanPhamHot.getItemAtPosition(i);
                SanPham duocChon = db.LaySanPhamBangMaSanPham(tam.getMaSP());
                startActivity(HienThiChiTietSanPham(duocChon, getApplicationContext()));
            }
        });
    }

    private void HienThiSanPham() {
        duLieuSanPhamHot = db.LayTatCaSanPhamHot();
        if (sanPhamHots == null) {
            sanPhamHots = new ArrayList<>();
        } else {
            sanPhamHots.removeAll(sanPhamHots);
        }
        if (duLieuSanPhamHot.getCount() > 0) {
            do {
                SanPham tam = new SanPham();
                tam = db.LaySanPhamBangMaSanPham(duLieuSanPhamHot.getString(duLieuSanPhamHot.getColumnIndex("masanpham")));
                ViewModelSanPhamHot sanPhamHot = new ViewModelSanPhamHot();
                if (tam != null) {
                    sanPhamHot.setMaSP(tam.getMaSP());
                    sanPhamHot.setTenSP(tam.getTenSP());
                    sanPhamHot.setGiaSP(tam.getGia());
                    sanPhamHot.setHinhAnhSP(tam.getHinhAnhSP());
                    sanPhamHot.setMoTaSP(tam.getMoTa());
                    sanPhamHots.add(sanPhamHot);
                }
            }
            while (duLieuSanPhamHot.moveToNext());
        }
        if (sanPhamHots != null) {
            listViewSanPhamHot.setAdapter(new SanPhamHotAdapter(getApplicationContext(), sanPhamHots));
        }
    }

    public static Intent HienThiChiTietSanPham(SanPham canXem, Context context) {
        Intent intentChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
        Bundle bundleThongTinSanPham = new Bundle();
        bundleThongTinSanPham.putString("masanpham", canXem.getMaSP());
        bundleThongTinSanPham.putString("tensanpham", canXem.getTenSP());
        bundleThongTinSanPham.putInt("giasanpham", canXem.getGia());
        bundleThongTinSanPham.putString("motasanpham", canXem.getMoTa());
        bundleThongTinSanPham.putString("hinhanhsanpham", canXem.getHinhAnhSP());
        bundleThongTinSanPham.putString("youtube", canXem.getLinkYT());
        intentChiTietSanPham.putExtras(bundleThongTinSanPham);
        return intentChiTietSanPham;
    }
}
