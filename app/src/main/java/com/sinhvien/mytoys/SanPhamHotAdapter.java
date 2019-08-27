package com.sinhvien.mytoys;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamHotAdapter extends BaseAdapter {
    Context context;
    ArrayList<ViewModelSanPhamHot> sanPhamHots;

    public SanPhamHotAdapter(Context context, ArrayList<ViewModelSanPhamHot> sanPhamHots) {
        this.context = context;
        this.sanPhamHots = sanPhamHots;
    }

    @Override
    public int getCount() {
        return sanPhamHots.size();
    }

    @Override
    public Object getItem(int i) {
        return sanPhamHots.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        ImageView imageViewHinhSanPham;
        TextView textViewTenSanPham, textViewGiaSanPham, textViewMoTaSanPham;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dong_san_pham, null);
            viewHolder = new ViewHolder();
            viewHolder.imageViewHinhSanPham = view.findViewById(R.id.imageViewHinhSanPham);
            viewHolder.textViewTenSanPham = view.findViewById(R.id.textViewTenSanPham);
            viewHolder.textViewGiaSanPham = view.findViewById(R.id.textViewGiaSanPham);
            viewHolder.textViewMoTaSanPham = view.findViewById(R.id.textViewMoTaSanPham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ViewModelSanPhamHot sanPhamHot = sanPhamHots.get(i);

        //Định dạng chuỗi giá sản phẩm
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        Picasso.get().load(sanPhamHot.getHinhAnhSP())
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.errorimg)
                .into(viewHolder.imageViewHinhSanPham);
        viewHolder.textViewTenSanPham.setText(sanPhamHot.getTenSP());
        viewHolder.textViewGiaSanPham.setText(decimalFormat.format(sanPhamHot.getGiaSP()));
        viewHolder.textViewMoTaSanPham.setText(sanPhamHot.getMoTaSP());
        //Cho hiển thị tối đã 2 dòng ở ngoài
        viewHolder.textViewMoTaSanPham.setMaxLines(2);
        //Dấu 3 chấm ở cuối
        viewHolder.textViewMoTaSanPham.setEllipsize(TextUtils.TruncateAt.END);

        return view;
    }
}
