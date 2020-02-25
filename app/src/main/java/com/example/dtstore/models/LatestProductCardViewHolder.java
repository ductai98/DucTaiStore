package com.example.dtstore.models;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtstore.R;

public class LatestProductCardViewHolder extends RecyclerView.ViewHolder {

    public ImageView productImage;
    public TextView productName;
    public TextView productPrice;
    public LatestProductCardViewHolder(@NonNull View itemView) {
        super(itemView);

        // Mapping view from lastest_product_card.xmlcard.xml
        productImage = itemView.findViewById(R.id.product_imgview);
        productName = itemView.findViewById(R.id.product_textview_name);
        productPrice = itemView.findViewById(R.id.product_price);
    }
}
