package com.sinhvien.mytoys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    Spinner spinnerSL;
    ImageView imageViewHinhSanPham;
    Button buttonMua;
    TextView textViewTenSanPham, textViewGiaSanPham, textViewMoTa;
    YouTubePlayerView videoViewYoutube;
    MyDB db;
    String API_KEY = "AIzaSyAM6XQm7AGCArs7SD88U9UYReJJdQkAoQs";
    int REQUEST_CODE = 10051998;
    String youtubeLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        db = new MyDB(getApplicationContext());
        imageViewHinhSanPham = findViewById(R.id.imageViewHinhSanPham);
        buttonMua = findViewById(R.id.buttonMua);
        textViewTenSanPham = findViewById(R.id.textViewTenSanPham);
        textViewGiaSanPham = findViewById(R.id.textViewGiaSanPham);
        textViewMoTa = findViewById(R.id.textViewMoTa);
        videoViewYoutube = findViewById(R.id.videoViewYoutube);

        final Bundle duLieuSanPham = getIntent().getExtras();

        Picasso.get().load(duLieuSanPham.getString("hinhanhsanpham"))
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.errorimg)
                .into(imageViewHinhSanPham);

        textViewTenSanPham.setText(duLieuSanPham.getString("tensanpham"));
        textViewMoTa.setText(duLieuSanPham.getString("motasanpham"));

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewGiaSanPham.setText(decimalFormat.format(duLieuSanPham.getInt("giasanpham")));


        youtubeLink = duLieuSanPham.getString("youtube");

        videoViewYoutube.initialize(API_KEY, this);


        buttonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GioHang gioHangMoi = new GioHang(duLieuSanPham.getString("masanpham"), Integer.parseInt(spinnerSL.getSelectedItem().toString()), HomeActivity.nguoiDungDangDangNhap);
                db.ThemGioHang(gioHangMoi);
            }
        });

        //Đổi màu Status bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.cateColorDark));

        spinnerSL = findViewById(R.id.spinnerSL);
        List<String> soLuong = new ArrayList<>();
        soLuong.add("1");
        soLuong.add("2");
        soLuong.add("3");
        soLuong.add("4");
        soLuong.add("5");
        soLuong.add("6");
        soLuong.add("7");
        soLuong.add("8");
        soLuong.add("9");
        soLuong.add("10");

        ArrayAdapter adapterSL = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, soLuong);
        adapterSL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSL.setAdapter(adapterSL);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(youtubeLink);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(ChiTietSanPhamActivity.this, REQUEST_CODE);
        } else {
            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            videoViewYoutube.initialize(youtubeLink, this);
        }
    }
}
