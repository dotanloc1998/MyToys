package com.sinhvien.mytoys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<ViewModelGioHang> gioHangs;
    MyDB db;


    public GioHangAdapter(Context context, ArrayList<ViewModelGioHang> gioHangs) {
        this.context = context;
        this.gioHangs = gioHangs;
    }

    @Override
    public int getCount() {
        return gioHangs.size();
    }

    @Override
    public Object getItem(int i) {
        return gioHangs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        ImageView imageViewHinhSanPham;
        Button buttonDauTru, buttonDauCong;
        TextView textViewTenSanPham, textViewGiaSanPham, textViewSoLuongSanPham;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dong_gio_hang, null);
            viewHolder = new ViewHolder();
            viewHolder.imageViewHinhSanPham = view.findViewById(R.id.imageViewHinhSanPham);
            viewHolder.buttonDauTru = view.findViewById(R.id.buttonDauTru);
            viewHolder.buttonDauCong = view.findViewById(R.id.buttonDauCong);
            viewHolder.textViewSoLuongSanPham = view.findViewById(R.id.textViewSoLuongSanPham);
            viewHolder.textViewTenSanPham = view.findViewById(R.id.textViewTenSanPham);
            viewHolder.textViewGiaSanPham = view.findViewById(R.id.textViewGiaSanPham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final ViewModelGioHang gioHang = gioHangs.get(i);
        db = new MyDB(context);

        Picasso.get().load(gioHang.getHinhSP())
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.errorimg)
                .into(viewHolder.imageViewHinhSanPham);

        viewHolder.textViewSoLuongSanPham.setText("" + gioHang.getSoLuongSP());
        viewHolder.textViewTenSanPham.setText(gioHang.getTenSP());
        //Định dạng chuỗi giá sản phẩm
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int giaSanPham = gioHang.getGia() * gioHang.getSoLuongSP();
        viewHolder.textViewGiaSanPham.setText(decimalFormat.format(giaSanPham));

        viewHolder.buttonDauTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gioHang.getSoLuongSP() == 1) {
                    Toast.makeText(context, "SL không được nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                } else {
                    gioHang.setSoLuongSP(gioHang.getSoLuongSP() - 1);
                    viewHolder.textViewSoLuongSanPham.setText("" + gioHang.getSoLuongSP());
                    db.CapNhatGioHang(new GioHang(gioHang.getMaSP(), gioHang.getSoLuongSP(), HomeActivity.nguoiDungDangDangNhap));
                    int giaSanPham = gioHang.getGia() * gioHang.getSoLuongSP();
                    viewHolder.textViewGiaSanPham.setText(decimalFormat.format(giaSanPham));

                    CartActivity.tongTien -= gioHang.getGia();
                    CartActivity.textViewTongTien.setText("Tổng tiền: " + decimalFormat.format(CartActivity.tongTien));
                }
            }
        });

        viewHolder.buttonDauCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gioHang.getSoLuongSP() == 10) {
                    Toast.makeText(context, "SL không được lớn hơn 10", Toast.LENGTH_SHORT).show();
                } else {
                    gioHang.setSoLuongSP(gioHang.getSoLuongSP() + 1);
                    viewHolder.textViewSoLuongSanPham.setText("" + gioHang.getSoLuongSP());
                    db.CapNhatGioHang(new GioHang(gioHang.getMaSP(), gioHang.getSoLuongSP(), HomeActivity.nguoiDungDangDangNhap));
                    int giaSanPham = gioHang.getGia() * gioHang.getSoLuongSP();
                    viewHolder.textViewGiaSanPham.setText(decimalFormat.format(giaSanPham));

                    CartActivity.tongTien += gioHang.getGia();
                    CartActivity.textViewTongTien.setText("Tổng tiền: " + decimalFormat.format(CartActivity.tongTien));
                }
            }
        });
        return view;
    }
}
