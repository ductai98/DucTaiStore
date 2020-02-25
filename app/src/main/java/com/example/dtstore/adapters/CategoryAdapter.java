package com.example.dtstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dtstore.R;
import com.example.dtstore.models.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    ArrayList<Category> listCategory;
    Context context;

    public CategoryAdapter(ArrayList<Category> listCategory, Context context) {
        this.listCategory = listCategory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return listCategory.get(position);
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
            convertView = inflater.inflate(R.layout.row_listview_category, null);
            viewHolderLoaiSP.textView_TenLoaiSanPham = convertView.findViewById(R.id.loaisanpham_textview);
            viewHolderLoaiSP.imageView_LoaiSanPham = convertView.findViewById(R.id.loaisanpham_imageview);
            convertView.setTag(viewHolderLoaiSP);
        }
        else{
            viewHolderLoaiSP = (ViewHolder) convertView.getTag();
        }

        Category category = (Category) getItem(position);
        viewHolderLoaiSP.textView_TenLoaiSanPham.setText(category.getTenLoaiSanPham());
        Picasso.get().load(category.getHinhLoaiSanPham()).placeholder(R.drawable.placeholder).error(R.drawable.error)
                .into(viewHolderLoaiSP.imageView_LoaiSanPham);

        return convertView;
    }
}
