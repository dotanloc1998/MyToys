package com.sinhvien.mytoys;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class LoaiSPApdapter extends CursorAdapter {
    public LoaiSPApdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.dong_loai_san_pham, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewTenLoaiSP = view.findViewById(R.id.textViewTenLoaiSP);
        ImageView imageViewHinhLoaiSanPham = view.findViewById(R.id.imageViewHinhLoaiSanPham);

        textViewTenLoaiSP.setText(cursor.getString(cursor.getColumnIndex("tenloaisanpham")));
        Picasso.get()
                .load("https://images.pexels.com/photos/1020324/pexels-photo-1020324.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.errorimg)
                .into(imageViewHinhLoaiSanPham);
    }
}
