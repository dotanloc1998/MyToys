package com.sinhvien.mytoys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HangDaMuaApdapter extends BaseAdapter {
    Context context;
    ArrayList<ViewModelHangDaMua> hangDaMuas;

    public HangDaMuaApdapter(Context context, ArrayList<ViewModelHangDaMua> hangDaMuas) {
        this.context = context;
        this.hangDaMuas = hangDaMuas;
    }

    @Override
    public int getCount() {
        return hangDaMuas.size();
    }

    @Override
    public Object getItem(int i) {
        return hangDaMuas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        ImageView imageViewHinhSanPham;
        TextView textViewTenSanPham, textViewSoLuongSanPham, textViewGiaSanPham;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dong_lich_su_mua_hang, null);
            viewHolder = new ViewHolder();
            viewHolder.imageViewHinhSanPham = view.findViewById(R.id.imageViewHinhSanPham);
            viewHolder.textViewTenSanPham = view.findViewById(R.id.textViewTenSanPham);
            viewHolder.textViewSoLuongSanPham = view.findViewById(R.id.textViewSoLuongSanPham);
            viewHolder.textViewGiaSanPham = view.findViewById(R.id.textViewGiaSanPham);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ViewModelHangDaMua hangDaMua = hangDaMuas.get(i);
        //Định dạng chuỗi giá sản phẩm
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        Picasso.get().load(hangDaMua.getHinhSP())
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.errorimg)
                .into(viewHolder.imageViewHinhSanPham);

        viewHolder.textViewGiaSanPham.setText(decimalFormat.format(hangDaMua.getGia()));
        viewHolder.textViewSoLuongSanPham.setText(""+hangDaMua.getSoLuong());
        viewHolder.textViewTenSanPham.setText(hangDaMua.getTenSP());

        return view;
    }
}
