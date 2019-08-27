package com.sinhvien.mytoys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ListView listViewGioHang;
    public static TextView textViewTongTien;
    Button buttonThanhToan, buttonMuaTiep;
    Cursor duLieuGioHang;
    public static ArrayList<ViewModelGioHang> gioHangs;
    MyDB db;
    public static int tongTien;
    ViewModelGioHang gioHangTam;
    GioHang hangDaXoa;
    View main_content;
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
        setContentView(R.layout.activity_cart);
        ChinhGiaoDien();
        AnhXa();
        HienThi();

        listViewGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle(getString(R.string.dialogTitle));
                builder.setMessage(getString(R.string.dialogContent));
                builder.setCancelable(true);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ViewModelGioHang gioHang = gioHangs.get(position);
                        GioHang sanPhamCanXoa = db.LaySanPhamTrongGioHang(gioHang.getMaSP(), HomeActivity.nguoiDungDangDangNhap);
                        db.XoaGioHang(sanPhamCanXoa);
                        tongTien = 0;
                        HienThi();
                        hangDaXoa = sanPhamCanXoa;
                        Snackbar snackbar = Snackbar
                                .make(main_content, "Xóa sản phẩm thành công", Snackbar.LENGTH_LONG)
                                .setAction("Khôi phục", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        db.ThemGioHang(hangDaXoa);
                                        tongTien = 0;
                                        HienThi();
                                    }
                                });
                        snackbar.show();
                    }
                });
                builder.setNegativeButton("Giữ hàng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });

        buttonMuaTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });

        buttonThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gioHangs == null || gioHangs.size() ==0){
                    Toast.makeText(getApplicationContext(),getString(R.string.gioHangTrong),Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent xacNhanThanhToan = new Intent(getApplicationContext(), XacNhanGiaoHangActivity.class);
                    startActivity(xacNhanThanhToan);
                }
            }
        });
    }

    private void AnhXa() {
        db = new MyDB(getApplicationContext());
        listViewGioHang = findViewById(R.id.listViewGioHang);
        textViewTongTien = findViewById(R.id.textViewTongTien);
        buttonThanhToan = findViewById(R.id.buttonThanhToan);
        buttonMuaTiep = findViewById(R.id.buttonMuaTiep);
        tongTien = 0;
        hangDaXoa = new GioHang();
        gioHangTam = new ViewModelGioHang();
        main_content = findViewById(R.id.main_content);
    }

    private void ChinhGiaoDien() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Đổi màu Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.cartColor)));

        //Đổi màu Status bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.cartColorDark));

        //Focus vào item được chọn
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
    }

    private void HienThi() {
        duLieuGioHang = db.LayGioHangTheoTaiKhoan(HomeActivity.nguoiDungDangDangNhap);
        if (gioHangs == null) {
            gioHangs = new ArrayList<>();
        } else {
            gioHangs.removeAll(gioHangs);
        }
        if (duLieuGioHang.getCount() > 0) {
            do {
                ViewModelGioHang tam = new ViewModelGioHang();
                SanPham sanPhamTam = db.LaySanPhamBangMaSanPham(duLieuGioHang.getString(duLieuGioHang.getColumnIndex("masanpham")));
                if (tam != null) {
                    tam.setMaSP(sanPhamTam.getMaSP());
                    tam.setGia(sanPhamTam.getGia());
                    tam.setHinhSP(sanPhamTam.getHinhAnhSP());
                    tam.setSoLuongSP(duLieuGioHang.getInt(duLieuGioHang.getColumnIndex("soluong")));
                    tam.setTenSP(sanPhamTam.getTenSP());
                    gioHangs.add(tam);
                    tongTien += tam.getGia() * tam.getSoLuongSP();
                }
            }
            while (duLieuGioHang.moveToNext());
        }
        if (gioHangs != null) {
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            textViewTongTien.setText(getText(R.string.tongTien) + " " + decimalFormat.format(tongTien));
            GioHangAdapter gioHangAdapter = new GioHangAdapter(getApplicationContext(), gioHangs);
            listViewGioHang.setAdapter(gioHangAdapter);
        }
    }
}
