package com.example.dtstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dtstore.R;
import com.example.dtstore.models.LoaiSanPham;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoaiSanPhamAdapter extends BaseAdapter {
    ArrayList<LoaiSanPham> listLoaiSanPham;
    Context context;

    public LoaiSanPhamAdapter(ArrayList<LoaiSanPham> listLoaiSanPham, Context context) {
        this.listLoaiSanPham = listLoaiSanPham;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listLoaiSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return listLoaiSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView textView_TenLoaiSanPham;
        ImageView imageView_LoaiSanPham;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolderLoaiSP = null;
        if(convertView == null){
            viewHolderLoaiSP = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview_loaisanpham, null);
            viewHolderLoaiSP.textView_TenLoaiSanPham = convertView.findViewById(R.id.loaisanpham_textview);
            viewHolderLoaiSP.imageView_LoaiSanPham = convertView.findViewById(R.id.loaisanpham_imageview);
            convertView.setTag(viewHolderLoaiSP);
        }
        else{
            viewHolderLoaiSP = (ViewHolder) convertView.getTag();
        }

        LoaiSanPham loaiSanPham = (LoaiSanPham) getItem(position);
        viewHolderLoaiSP.textView_TenLoaiSanPham.setText(loaiSanPham.getTenLoaiSanPham());
        Picasso.get().load(loaiSanPham.getHinhLoaiSanPham()).placeholder(R.drawable.placeholder).error(R.drawable.error)
                .into(viewHolderLoaiSP.imageView_LoaiSanPham);

        return convertView;
    }
}
