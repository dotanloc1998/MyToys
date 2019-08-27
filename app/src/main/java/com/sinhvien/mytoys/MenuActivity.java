package com.sinhvien.mytoys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {
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
                    Intent openCat = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(openCat);
                    return true;
                case R.id.navigation_cart:
                    Intent openCart = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(openCart);
                    return true;
                case R.id.navigation_menu:
                    return true;
            }
            return false;
        }
    };
    Button buttonThongTin, buttonVeChungToi, buttonThemCSDL, buttonXemThongKe;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        AnhXa();

        buttonThemCSDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemLoaiSanPhamVaoCSDL();
                ThemSanPhamVaoCSDL();
                ThemSanPhamHotVaoCSDL();
                Toast.makeText(getApplicationContext(), "Thêm vào CSDL thành công", Toast.LENGTH_SHORT).show();
            }
        });

        buttonThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (HomeActivity.nguoiDungDangDangNhap == "") {
                    Intent giaoDienDangNhap = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(giaoDienDangNhap);
                } else {
                    Intent giaoDienThongTin = new Intent(getApplicationContext(), UserActivity.class);
                    startActivity(giaoDienThongTin);
                }
            }
        });

        buttonXemThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent xemThongKe = new Intent(getApplicationContext(),ThongKeActivity.class);
                startActivity(xemThongKe);
            }
        });

        buttonVeChungToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenFacebookIntent(getApplicationContext()));
            }
        });
    }

    private void ThemSanPhamVaoCSDL() {
        SanPham sanPham1 = new SanPham("sp1", "Optimus Prime Hot Toys", 560000, "-q5J2Lj9F0s", "E", "https://i.pinimg.com/originals/91/85/3e/91853ed1e2b399a95d69d098fdec5eb1.jpg", getString(R.string.E));
        db.ThemSanPham(sanPham1);

        SanPham sanPham2 = new SanPham("sp2", "Kamen Rider Neo Decade Belt", 250000, "HWDJXPpJdSQ", "E", "https://i2.wp.com/hero-club.com/wp-content/uploads/2018/12/Neo-Decade-Driver.jpg?ssl=1", getString(R.string.E));
        db.ThemSanPham(sanPham2);

        SanPham sanPham3 = new SanPham("sp3", "Kamen Rider Build Belt", 380000, "BWHIt98Xthw", "E10", "https://images-na.ssl-images-amazon.com/images/I/716yNwDQ1kL._SL1500_.jpg", getString(R.string.E10));
        db.ThemSanPham(sanPham3);

        SanPham sanPham4 = new SanPham("sp4", "Kamen Rider Zi-O Belt", 390000, "npWk6yuxN9M", "E10", "https://i.ytimg.com/vi/npWk6yuxN9M/maxresdefault.jpg", getString(R.string.E10));
        db.ThemSanPham(sanPham4);

        SanPham sanPham5 = new SanPham("sp5", "Kamen Rider Woz Belt", 250000, "qcPz1A3NONw", "T", "https://s3.amazonaws.com/thmb.inkfrog.com/pix/cwk2002/beyondriver0.jpg/600/0", getString(R.string.T));
        db.ThemSanPham(sanPham5);

        SanPham sanPham6 = new SanPham("sp6", "Kamen Rider Ex-Aid Belt", 930000, "NgoopTF9eFE", "T", "https://i.ytimg.com/vi/wdMJLijL-Fk/maxresdefault.jpg", getString(R.string.T));
        db.ThemSanPham(sanPham6);

        SanPham sanPham7 = new SanPham("sp7", "LEGO Kamen Rider OOO combos", 1500000, "cTkDxux9xDk", "M", "https://i.ytimg.com/vi/cTkDxux9xDk/maxresdefault.jpg", getString(R.string.M));
        db.ThemSanPham(sanPham7);

        SanPham sanPham8 = new SanPham("sp8", "One Piece Mihawk Figure", 2500000, "Lp43OzY31h4", "M", "https://cdn.shopify.com/s/files/1/1097/6170/products/BPMasterD_Mihawk_01_1024x1024.png?v=1535976122", getString(R.string.M));
        db.ThemSanPham(sanPham8);

        SanPham sanPham9 = new SanPham("sp9", "One Piece Nami Figure", 2000000, "QbmK4VBIyOQ", "A", "https://ae01.alicdn.com/kf/HTB1c.AiNFXXXXa1XVXXq6xXFXXX7/One-Piece-Action-Figure-Nami-BB-Ver-PVC-Figure-One-Piece-Nami-Sexy-Yellow-Bikini-Collectible.jpg_640x640.jpg", getString(R.string.A));
        db.ThemSanPham(sanPham9);

        SanPham sanPham10 = new SanPham("sp10", "One Piece Chopper Figure", 3500000, "VaTNuNj1WaI", "A", "https://i.redd.it/2hap868bp3a21.jpg", getString(R.string.A));
        db.ThemSanPham(sanPham10);

    }

    private void ThemSanPhamHotVaoCSDL() {
        db.ThemSanPhamHot("sp6");
        db.ThemSanPhamHot("sp10");
        db.ThemSanPhamHot("sp1");
    }

    private void ThemLoaiSanPhamVaoCSDL() {
        LoaiSP loaiSP1 = new LoaiSP("E", "Mọi người", "Thích hợp cho mọi lứa tuổi");
        db.ThemLoaiSP(loaiSP1);
        LoaiSP loaiSP2 = new LoaiSP("E10", "Trên 10 tuổi", "Thích hợp cho người trên 10 tuổi");
        db.ThemLoaiSP(loaiSP2);
        LoaiSP loaiSP3 = new LoaiSP("T", "Thanh thiếu niên", "Thích hợp cho lứa tuổi trên 13");
        db.ThemLoaiSP(loaiSP3);
        LoaiSP loaiSP4 = new LoaiSP("M", "Người trưởng thành", "Thích hợp cho người trên 17 tuổi");
        db.ThemLoaiSP(loaiSP4);
        LoaiSP loaiSP5 = new LoaiSP("A", "Người lớn", "Thích hợp cho người 18 tuổi trở lên");
        db.ThemLoaiSP(loaiSP5);
    }

    private void AnhXa() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Đổi màu Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.menuColor)));

        //Đổi màu Status bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.menuColorDark));

        //Focus vào item được chọn
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        db = new MyDB(getApplicationContext());

        buttonThongTin = findViewById(R.id.buttonThongTin);
        buttonVeChungToi = findViewById(R.id.buttonVeChungToi);
        buttonThemCSDL = findViewById(R.id.buttonThemCSDL);
        buttonXemThongKe = findViewById(R.id.buttonXemThongKe);
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/khimacao"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/khimacao"));
        }
    }
}
