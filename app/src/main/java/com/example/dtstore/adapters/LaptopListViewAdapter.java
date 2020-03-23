package com.example.dtstore.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dtstore.R;
import com.example.dtstore.models.LaptopViewHolder;
import com.example.dtstore.models.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> productArrayList;

    public LaptopListViewAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @Override
    public int getCount() {
        return productArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return productArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LaptopViewHolder laptopViewHolder = null;
        if (convertView == null){
            //Inflate layout to convertView
            laptopViewHolder = new LaptopViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview_laptop, null);

            //Mapping view to view holder
            laptopViewHolder.laptopName = convertView.findViewById(R.id.laptop_name);
            laptopViewHolder.laptopPrice = convertView.findViewById(R.id.laptop_price);
            laptopViewHolder.laptopDescription = convertView.findViewById(R.id.laptop_description);
            laptopViewHolder.laptopImage = convertView.findViewById(R.id.laptop_image);

            convertView.setTag(laptopViewHolder);
        }
        else{
            laptopViewHolder = (LaptopViewHolder) convertView.getTag();
        }

        //Set data from product list to view
        Product laptop = (Product) getItem(position);
        laptopViewHolder.laptopName.setText(laptop.getProductName());
        laptopViewHolder.laptopDescription.setMaxLines(2);//Set max line of product description
        laptopViewHolder.laptopDescription.setEllipsize(TextUtils.TruncateAt.END);//Set truncate position
        laptopViewHolder.laptopDescription.setText(laptop.getProductDescript());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        laptopViewHolder.laptopPrice.setText("Giá: " + decimalFormat.format(laptop.getProductPrice()) + "đ");
        Picasso.get().load(laptop.getProductImage())
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.error)
                                .into(laptopViewHolder.laptopImage);

        return convertView;
    }
}
