package com.example.dtstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dtstore.R;
import com.example.dtstore.models.Cart;
import com.example.dtstore.models.Product;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetailActivity extends AppCompatActivity {
    Toolbar productDetailToolbar;
    ImageView productDetailProductImg;
    TextView productDetailName, productDetailPrice, productDetailDescription;
    Spinner productDetailSpinner;
    MaterialButton productDetailAddCart;
    int id = 0;
    String productName = "";
    int productPrice = 0;
    String productImage = "";
    String productDescription = "";
    int idCategory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        MappingView();
        InitialActionBar();
        GetProductData();
        SetUpSpinner();
        OnClickEvent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart:
                Intent cartIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(cartIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void OnClickEvent() {
        productDetailAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.cartArrayList.size() > 0){
                    //Increase the quantity if customer continue buy same product
                    int quantity = Integer.parseInt(productDetailSpinner.getSelectedItem().toString());
                    boolean detectedSameProduct = false;
                    for(int i=0;i<MainActivity.cartArrayList.size();i++){
                        if(MainActivity.cartArrayList.get(i).getId() == id){
                            MainActivity.cartArrayList.get(i).setCount(MainActivity.cartArrayList.get(i).getCount() + quantity);
                            //Do not allowed customer buy more than 10 products
                            if(MainActivity.cartArrayList.get(i).getCount() > 10){
                                MainActivity.cartArrayList.get(i).setCount(10);
                            }
                            //Set cart total price = product price * quantity
                            MainActivity.cartArrayList.get(i).setTotalPrice(productPrice * MainActivity.cartArrayList.get(i).getCount());
                            detectedSameProduct = true;
                            break;
                        }
                    }
                    if(detectedSameProduct == false){
                        int productQuantity = Integer.parseInt(productDetailSpinner.getSelectedItem().toString());
                        long totalPrice = productQuantity * productPrice;
                        MainActivity.cartArrayList.add(new Cart(id, productName, totalPrice, productImage, productQuantity));
                    }
                }else{
                    int productQuantity = Integer.parseInt(productDetailSpinner.getSelectedItem().toString());
                    int totalPrice = productQuantity * productPrice;
                    MainActivity.cartArrayList.add(new Cart(id, productName, totalPrice, productImage, productQuantity));
                }
                //Change activity
                Intent cartIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });
    }

    private void SetUpSpinner() {
        Integer[] count = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<>(ProductDetailActivity.this, android.R.layout.simple_spinner_dropdown_item, count);
        productDetailSpinner.setAdapter(spinnerArrayAdapter);
    }

    private void GetProductData() {
        //Create a Product object to get data from Product Activity
        Product product = (Product) getIntent().getSerializableExtra("ProductData");
        id = product.getId();
        productName = product.getProductName();
        productPrice = product.getProductPrice();
        productImage = product.getProductImage();
        productDescription = product.getProductDescript();
        idCategory = product.getIdCategory();

        //Set data to view
        productDetailName.setText(productName);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        productDetailPrice.setText("Giá: " + decimalFormat.format(productPrice) + "đ");
        productDetailDescription.setText(productDescription);
        Picasso.get().load(productImage).placeholder(R.drawable.placeholder).error(R.drawable.error).into(productDetailProductImg);
    }

    private void InitialActionBar() {
        setSupportActionBar(productDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        productDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void MappingView() {
        productDetailToolbar = findViewById(R.id.detail_toolbar);
        productDetailProductImg = findViewById(R.id.detail_productImg);
        productDetailName = findViewById(R.id.detail_productName);
        productDetailPrice = findViewById(R.id.detail_productPrice);
        productDetailSpinner = findViewById(R.id.detail_spinner);
        productDetailDescription = findViewById(R.id.detail_productDescription);
        productDetailAddCart = findViewById(R.id.detail_buttonAddtoCart);
    }
}
