package com.example.dtstore.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dtstore.R;
import com.example.dtstore.models.Product;
import com.example.dtstore.models.ProductViewHolder;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PhoneListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> productArrayList;

    public PhoneListViewAdapter(Context context, ArrayList<Product> productArrayList) {
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
        ProductViewHolder productViewHolder = null;
        if(convertView == null){
            //Inflate layout to view
            productViewHolder = new ProductViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview_phone, null);

            //Mapping view to product view holder
            productViewHolder.phone_text_product_name = convertView.findViewById(R.id.phone_text_name);
            productViewHolder.phone_text_product_price = convertView.findViewById(R.id.phone_text_price);
            productViewHolder.phone_text_product_descript = convertView.findViewById(R.id.phone_text_descript);
            productViewHolder.phone_image_product = convertView.findViewById(R.id.phone_imageview);

            convertView.setTag(productViewHolder);
        }
        else{
            productViewHolder = (ProductViewHolder) convertView.getTag();
        }

        //Set data from product to view holder
        Product product = (Product) getItem(position);
        productViewHolder.phone_text_product_name.setText(product.getProductName());
        productViewHolder.phone_text_product_descript.setMaxLines(2);//Set max line of product description
        productViewHolder.phone_text_product_descript.setEllipsize(TextUtils.TruncateAt.END);//Set truncate position
        productViewHolder.phone_text_product_descript.setText(product.getProductDescript());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        productViewHolder.phone_text_product_price.setText("Giá: " + decimalFormat.format(product.getProductPrice()) + "đ");
        Picasso.get().load(product.getProductImage())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.error)
                        .into(productViewHolder.phone_image_product);

        return convertView;
    }

}
