package com.sinhvien.mytoys;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ThongKeActivity extends AppCompatActivity {
    ListView listViewThongKe;
    TextView textViewTongDoanhThu;
    int tongTien = 0;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        listViewThongKe = findViewById(R.id.listViewThongKe);
        textViewTongDoanhThu = findViewById(R.id.textViewTongDoanhThu);

        db = new MyDB(getApplicationContext());

        Cursor hangDaBan = db.LayTatCaSanPhamDaBan();

        String[] from = new String[]{"_id", "masanpham", "soluong", "taikhoan", "sotien"};
        int[] to = new int[]{R.id.textViewMaThongKe, R.id.textViewMaSanPham, R.id.textViewSoLuongSanPham, R.id.textViewTaiKhoan, R.id.textViewSoTien};


        if (hangDaBan.getCount() > 0) {
            do {
                tongTien += hangDaBan.getInt(hangDaBan.getColumnIndex("sotien"));
            }
            while (hangDaBan.moveToNext());
            SimpleCursorAdapter simpleAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.dong_thong_ke, hangDaBan, from, to, 0);
            listViewThongKe.setAdapter(simpleAdapter);
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewTongDoanhThu.setText(decimalFormat.format(tongTien));

    }
}
