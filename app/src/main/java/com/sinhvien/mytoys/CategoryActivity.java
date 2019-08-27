package com.sinhvien.mytoys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategoryActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent openHome = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(openHome);
                    return true;
                case R.id.navigation_search:
                    Intent openSearch = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(openSearch);
                    return true;
                case R.id.navigation_category:
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

    ListView listViewLoaiSanPham;
    Cursor loaiSP;
    LoaiSPApdapter loaiSPApdapter;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        AnhXa();

        listViewLoaiSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor loaiSanPhamChon = (Cursor) listViewLoaiSanPham.getItemAtPosition(i);
                Intent moChiTietLoaiSP = new Intent(getApplicationContext(), ChiTietLoaiSPActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("loaisp", loaiSanPhamChon.getString(loaiSanPhamChon.getColumnIndex("maloaisanpham")));
                moChiTietLoaiSP.putExtras(bundle);
                startActivity(moChiTietLoaiSP);
            }
        });
    }

    private void AnhXa() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Đổi màu Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.cateColor)));

        //Đổi màu Status bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.cateColorDark));

        //Focus vào item được chọn
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        db = new MyDB(getApplicationContext());
        listViewLoaiSanPham = findViewById(R.id.listViewLoaiSanPham);
        loaiSP = db.LayTatCaLoaiSP();
        loaiSPApdapter = new LoaiSPApdapter(getApplicationContext(), loaiSP, 0);
        listViewLoaiSanPham.setAdapter(loaiSPApdapter);
    }
}
