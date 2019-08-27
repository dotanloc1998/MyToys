package com.sinhvien.mytoys;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class SanPhamAdapter extends CursorAdapter {

    public SanPhamAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.dong_san_pham, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Ánh xạ
        ImageView imageViewHinhSanPham = view.findViewById(R.id.imageViewHinhSanPham);
        TextView textViewTenSanPham = view.findViewById(R.id.textViewTenSanPham);
        TextView textViewGiaSanPham = view.findViewById(R.id.textViewGiaSanPham);
        TextView textViewMoTaSanPham = view.findViewById(R.id.textViewMoTaSanPham);

        //Cho hiển thị tối đã 2 dòng ở ngoài
        textViewMoTaSanPham.setMaxLines(2);
        //Dấu 3 chấm ở cuối
        textViewMoTaSanPham.setEllipsize(TextUtils.TruncateAt.END);

        //Định dạng chuỗi giá sản phẩm
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");


        //Gán dữ liệu cho view
        textViewTenSanPham.setText(cursor.getString(cursor.getColumnIndex("tensanpham")));
        textViewGiaSanPham.setText(decimalFormat.format(cursor.getInt(cursor.getColumnIndex("giasanpham"))));
        textViewMoTaSanPham.setText(cursor.getString(cursor.getColumnIndex("mota")));
        //Gán hình ảnh cho ImageView
        Picasso.get().load(cursor.getString(cursor.getColumnIndex("hinhsanpham")))
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.errorimg)
                .into(imageViewHinhSanPham);

    }
}
