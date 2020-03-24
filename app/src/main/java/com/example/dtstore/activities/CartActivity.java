package com.example.dtstore.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dtstore.R;
import com.example.dtstore.adapters.CartListViewAdapter;
import com.example.dtstore.models.Cart;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    ListView cartListView;
    TextView cartInform;
    static TextView cartTotalPrice;
    Button cartCheckOut, cartContinue;
    Toolbar cartToolBar;
    CartListViewAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        MappingView();
        SetUpListViewAdapter();
        SetUpToolBar();
        CheckCartData();
        GetCartDataFromMainActivity();
        HandlerItemLongClick(); //Delete product in cart
        OnClickButtonCheckOutAndContinue();
    }

    private void OnClickButtonCheckOutAndContinue() {
        cartContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        cartCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.cartArrayList.size() > 0){
                    Intent intent = new Intent(CartActivity.this, CustomerActivity.class);
                    startActivity(intent);
                }else{
                    final AlertDialog.Builder alert = new AlertDialog.Builder(CartActivity.this);
                    alert.setTitle("Thông báo");
                    alert.setMessage("Giỏ hàng đang trống");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                }
            }
        });
    }

    private void HandlerItemLongClick() {
        cartListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Xóa sản phẩm");
                builder.setMessage("Bạn có muốn xóa sản phẩm?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.cartArrayList.size() <= 0){
                            cartInform.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.cartArrayList.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            GetCartDataFromMainActivity();
                            if(MainActivity.cartArrayList.size() <= 0){
                                cartInform.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartAdapter.notifyDataSetChanged();
                        GetCartDataFromMainActivity();
                    }
                });
                builder.show();

                return true;
            }
        });
    }

    public static void GetCartDataFromMainActivity() {
        long checkoutPrice = 0;
        int size = MainActivity.cartArrayList.size();
        for(int i=0;i<size;i++){
            checkoutPrice = checkoutPrice + MainActivity.cartArrayList.get(i).getTotalPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        cartTotalPrice.setText(decimalFormat.format(checkoutPrice) + "đ");
    }

    private void CheckCartData() {
        if(MainActivity.cartArrayList.size() <= 0){
            cartAdapter.notifyDataSetChanged();
            cartInform.setVisibility(View.VISIBLE);
            cartListView.setVisibility(View.INVISIBLE);
        }else{
            cartAdapter.notifyDataSetChanged();
            cartInform.setVisibility(View.INVISIBLE);
            cartListView.setVisibility(View.VISIBLE);
        }
    }

    private void SetUpToolBar() {
        setSupportActionBar(cartToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cartToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void SetUpListViewAdapter() {
        cartAdapter = new CartListViewAdapter(CartActivity.this, MainActivity.cartArrayList);
        cartListView.setAdapter(cartAdapter);
    }

    private void MappingView() {
        cartListView = findViewById(R.id.cart_listView);
        cartInform = findViewById(R.id.cart_textviewInform);
        cartTotalPrice = findViewById(R.id.cart_totalPrice);
        cartCheckOut = findViewById(R.id.cart_checkout);
        cartContinue = findViewById(R.id.cart_continue);
        cartToolBar = findViewById(R.id.cart_toolbar);
    }
}
