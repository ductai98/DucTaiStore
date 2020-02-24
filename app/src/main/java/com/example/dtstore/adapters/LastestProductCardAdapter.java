package com.example.dtstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtstore.R;
import com.example.dtstore.models.Product;
import com.example.dtstore.models.LastestProductCardViewHolder;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LastestProductCardAdapter extends RecyclerView.Adapter<LastestProductCardViewHolder> {

    ArrayList<Product> productArrayList;

    public LastestProductCardAdapter(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public LastestProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lastest_product_card, null);
        LastestProductCardViewHolder lastestProductCardViewHolder = new LastestProductCardViewHolder(view);
        return lastestProductCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LastestProductCardViewHolder holder, int position) {
        // Binding product data to layout
        if(productArrayList != null && position < productArrayList.size()){
            Product product = productArrayList.get(position);
            holder.productName.setText(product.getProductName());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            holder.productPrice.setText("Giá: " + decimalFormat.format(product.getProductPrice()) + "đ");
            Picasso.get().load(product.getProductImage()).placeholder(R.drawable.placeholder)
                                                            .error(R.drawable.error)
                                                            .into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }
}
