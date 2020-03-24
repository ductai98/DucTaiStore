package com.example.dtstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dtstore.R;
import com.example.dtstore.activities.CartActivity;
import com.example.dtstore.activities.MainActivity;
import com.example.dtstore.models.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> cartArrayList;

    public CartListViewAdapter(Context context, ArrayList<Cart> cartArrayList) {
        this.context = context;
        this.cartArrayList = cartArrayList;
    }

    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CartViewHolder cartViewHolder = null;
        if (convertView == null) {
            cartViewHolder = new CartViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_cart_list_view, null);
            cartViewHolder.imgProductImage = convertView.findViewById(R.id.row_cart_image);
            cartViewHolder.txtProductName = convertView.findViewById(R.id.row_cart_product_name);
            cartViewHolder.txtProductPrice = convertView.findViewById(R.id.row_cart_product_price);
            cartViewHolder.btnMinus = convertView.findViewById(R.id.row_cart_minus);
            cartViewHolder.btnPlus = convertView.findViewById(R.id.row_cart_plus);
            cartViewHolder.txtQuantity = convertView.findViewById(R.id.row_cart_value);
            convertView.setTag(cartViewHolder);
        } else {
            cartViewHolder = (CartViewHolder) convertView.getTag();
        }

        Cart cart = (Cart) getItem(position);
        cartViewHolder.txtProductName.setText(cart.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        cartViewHolder.txtProductPrice.setText(decimalFormat.format(cart.getTotalPrice()) + "đ");
        Picasso.get().load(cart.getProductImage()).placeholder(R.drawable.placeholder).error(R.drawable.error).into(cartViewHolder.imgProductImage);
        cartViewHolder.txtQuantity.setText(String.valueOf(cart.getCount()));
        int quantity = Integer.parseInt(cartViewHolder.txtQuantity.getText().toString());

        //Don't allowed customer buy more than 10 each product and less than 1 each product
        checkQuantity(quantity, cartViewHolder);

        //Handle button plus quantity on click event
        final CartViewHolder finalCartViewHolder = cartViewHolder;
        cartViewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = Integer.parseInt(finalCartViewHolder.txtQuantity.getText().toString()) + 1;
                finalCartViewHolder.txtQuantity.setText(String.valueOf(newQuantity));
                int currentQuantity = MainActivity.cartArrayList.get(position).getCount();
                long currentPrice = MainActivity.cartArrayList.get(position).getTotalPrice();

                //Set new quantity to cartArrayList
                MainActivity.cartArrayList.get(position).setCount(newQuantity);
                if(currentQuantity == 0){
                    currentQuantity = 1;
                }
                long newPrice = (currentPrice * newQuantity) / currentQuantity;
                MainActivity.cartArrayList.get(position).setTotalPrice(newPrice);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalCartViewHolder.txtProductPrice.setText(decimalFormat.format(newPrice) + "đ");
                CartActivity.GetCartDataFromMainActivity();
                checkQuantity(newQuantity, finalCartViewHolder);
            }
        });

        //Handle button minus quantity on click event
        cartViewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = Integer.parseInt(finalCartViewHolder.txtQuantity.getText().toString()) - 1;
                finalCartViewHolder.txtQuantity.setText(String.valueOf(newQuantity));
                int currentQuantity = MainActivity.cartArrayList.get(position).getCount();
                long currentPrice = MainActivity.cartArrayList.get(position).getTotalPrice();

                //Set new quantity to cartArrayList
                MainActivity.cartArrayList.get(position).setCount(newQuantity);
                long newPrice = 1;
                if(currentQuantity == 0){
                    newPrice = 0;
                }else{
                    newPrice = (currentPrice * newQuantity) / currentQuantity;
                }
                MainActivity.cartArrayList.get(position).setTotalPrice(newPrice);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalCartViewHolder.txtProductPrice.setText(decimalFormat.format(newPrice) + "đ");
                CartActivity.GetCartDataFromMainActivity();
                checkQuantity(newQuantity, finalCartViewHolder);
            }
        });

        return convertView;
    }

    //Don't allowed customer buy more than 10 each product
    private void checkQuantity(int quantity, CartViewHolder viewHolder){
        if(quantity == 1){
            viewHolder.btnMinus.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.btnMinus.setVisibility(View.VISIBLE);
        }
        if (quantity >= 10) {
            viewHolder.btnPlus.setVisibility(View.INVISIBLE);
        } else{
            viewHolder.btnPlus.setVisibility(View.VISIBLE);
        }
    }

    public class CartViewHolder {
        public TextView txtProductName, txtProductPrice, txtQuantity;
        public ImageView imgProductImage;
        public Button btnMinus, btnPlus;
    }
}
