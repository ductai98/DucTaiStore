package com.example.dtstore.models;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtstore.R;
import com.example.dtstore.activities.ProductDetailActivity;
import com.example.dtstore.utilities.CheckNetworkConnection;

import java.util.ArrayList;

public class LatestProductCardViewHolder extends RecyclerView.ViewHolder {

    public ImageView productImage;
    public TextView productName;
    public TextView productPrice;
    public LatestProductCardViewHolder(@NonNull final View itemView, final ArrayList<Product> productArrayList) {
        super(itemView);

        // Mapping view from lastest_product_card.xmlcard.xml
        productImage = itemView.findViewById(R.id.product_imgview);
        productName = itemView.findViewById(R.id.product_textview_name);
        productPrice = itemView.findViewById(R.id.product_price);

        // Set click event on item of recycler view
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), ProductDetailActivity.class);
                intent.putExtra("ProductData", productArrayList.get(getPosition()));
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
