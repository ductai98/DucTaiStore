package com.example.dtstore.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtstore.R;
import com.example.dtstore.models.Product;
import com.example.dtstore.models.LatestProductCardViewHolder;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LatestProductCardAdapter extends RecyclerView.Adapter<LatestProductCardViewHolder> {

    ArrayList<Product> productArrayList;

    public LatestProductCardAdapter(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public LatestProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lastest_product_card, null);
        LatestProductCardViewHolder latestProductCardViewHolder = new LatestProductCardViewHolder(view, productArrayList);
        return latestProductCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LatestProductCardViewHolder holder, int position) {
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
