package com.example.dtstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dtstore.R;
import com.example.dtstore.adapters.LaptopListViewAdapter;
import com.example.dtstore.models.Product;
import com.example.dtstore.utilities.CheckNetworkConnection;
import com.example.dtstore.utilities.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaptopActivity extends AppCompatActivity {
    Toolbar laptopToolbar;
    ListView laptopListView;
    ArrayList<Product> laptopArrayList;
    LaptopListViewAdapter laptopAdapter;
    int page = 1;
    int categoryID = 0;
    View footerView;
    MyHandler myHandler;
    boolean isLoading = false;
    boolean isOutOfData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        MappingViewLaptopAcvivity();
        GetCategoryID();
        InitListViewItem();
        InitActionBar();
        InitHandler();
        if (CheckNetworkConnection.haveNetworkConnection(getApplicationContext())) {
            GetProductsDataByCategoryID(page);
            LoadMoreData();
        } else {
            CheckNetworkConnection.ShowMessage_Short(this, "No internet connection");
        }
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
                Intent cartIntent = new Intent(LaptopActivity.this, CartActivity.class);
                startActivity(cartIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void InitHandler() {
        myHandler = new MyHandler();
    }

    private void LoadMoreData() {
        laptopListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0
                        && isLoading == false && isOutOfData == false) {
                    isLoading = true;
                    LoadingDataThread loadingDataThread = new LoadingDataThread();
                    loadingDataThread.start();
                }
            }
        });

        laptopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LaptopActivity.this, ProductDetailActivity.class);
                intent.putExtra("ProductData", laptopArrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void InitActionBar() {
        setSupportActionBar(laptopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        laptopToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetCategoryID() {
        categoryID = getIntent().getIntExtra("CategoryID", -1);
        Log.d("CategoryID", categoryID + "");
    }

    private void GetProductsDataByCategoryID(int pageNum) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String productURL = Server.ProductURL + String.valueOf(pageNum);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, productURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String productName = "";
                int productPrice = 0;
                String productImage = "";
                String productDescript = "";
                int categoryID = 0;
                if (response != null && response.length() != 2) {
                    laptopListView.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        //Iterate through all JsonObjects of JsonArray
                        for (int i = 0; i < jsonArray.length(); i++) {

                            //Get attribute from JsonObject
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            productName = jsonObject.getString("productName");
                            productImage = jsonObject.getString("productImage");
                            productPrice = jsonObject.getInt("productPrice");
                            productDescript = jsonObject.getString("productDescript");
                            categoryID = jsonObject.getInt("idCategory");

                            //Push data to the product array list
                            laptopArrayList.add(new Product(id, productName, productPrice, productImage, productDescript, categoryID));
                            laptopAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    laptopListView.removeFooterView(footerView);
                    isOutOfData = true;
                    Toast.makeText(LaptopActivity.this, "Out of data", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LaptopActivity.this, error.getMessage(), Toast.LENGTH_SHORT);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("categoryid", String.valueOf(categoryID));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void InitListViewItem() {
        laptopArrayList = new ArrayList<>();
        laptopAdapter = new LaptopListViewAdapter(LaptopActivity.this, laptopArrayList);
        laptopListView.setAdapter(laptopAdapter);
    }

    private void MappingViewLaptopAcvivity() {
        laptopToolbar = findViewById(R.id.laptop_toolbar);
        laptopListView = findViewById(R.id.laptop_listview);
        footerView = LayoutInflater.from(LaptopActivity.this).inflate(R.layout.progress_bar, null);
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0: { // Add footer to layout
                    laptopListView.addFooterView(footerView);
                    break;
                }
                case 1: { // Add data to list view
                    page++;
                    GetProductsDataByCategoryID(page);
                    isLoading = false;
                    break;
                }
            }
            super.handleMessage(msg);
        }
    }

    //Create new Thread for loading data from database
    public class LoadingDataThread extends Thread {
        @Override
        public void run() {
            Message message = myHandler.obtainMessage(0);
            myHandler.sendMessage(message);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            message = myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }


}
