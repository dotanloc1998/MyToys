package com.sinhvien.mytoys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {
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

    SearchView khungTimKiem;
    ListView listViewSanPhamKiemDuoc;
    MyDB db;
    Cursor sanPhamTimDuoc;
    SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AnhXa();

        khungTimKiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                sanPhamTimDuoc = db.LaySanPhamTheoTen(s);
                sanPhamAdapter = new SanPhamAdapter(getApplicationContext(), sanPhamTimDuoc, 0);
                listViewSanPhamKiemDuoc.setAdapter(sanPhamAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                sanPhamTimDuoc = db.LaySanPhamTheoTen(s);
                sanPhamAdapter = new SanPhamAdapter(getApplicationContext(), sanPhamTimDuoc, 0);
                listViewSanPhamKiemDuoc.setAdapter(sanPhamAdapter);
                return false;
            }
        });
        listViewSanPhamKiemDuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor sanPhamChon  = (Cursor) listViewSanPhamKiemDuoc.getItemAtPosition(i);
                SanPham duocChon = db.LaySanPhamBangMaSanPham(sanPhamChon.getString(sanPhamChon.getColumnIndex("masanpham")));
                startActivity(HomeActivity.HienThiChiTietSanPham(duocChon,getApplicationContext()));
            }
        });

    }

    private void AnhXa() {
        khungTimKiem = findViewById(R.id.khungTimKiem);
        listViewSanPhamKiemDuoc = findViewById(R.id.listViewSanPhamKiemDuoc);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        db = new MyDB(getApplicationContext());

        //Đổi màu Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.searchColor)));

        //Đổi màu Status bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.searchColorDark));

        //Focus vào item được chọn
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }
}
