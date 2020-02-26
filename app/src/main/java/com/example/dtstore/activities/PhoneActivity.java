package com.example.dtstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.dtstore.adapters.PhoneListViewAdapter;
import com.example.dtstore.models.Product;
import com.example.dtstore.utilities.CheckNetworkConnection;
import com.example.dtstore.utilities.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Destroyable;

public class PhoneActivity extends AppCompatActivity {
    Toolbar phoneToolbar;
    ListView phoneListview;
    PhoneListViewAdapter phoneAdapter;
    ArrayList<Product> productArrayList;
    int categoryID = 0;
    int page = 1;
    View footerView;
    boolean isLoading = false;
    MyHandler myHandler;
    boolean isOutOfData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        MappingViewPhoneActivity();
        InitHandler();
        InitListViewItem();
        InitActionBar();
        GetCategoryID();
        if (CheckNetworkConnection.haveNetworkConnection(getApplicationContext())) {
            GetProductsDataByCategoryID(page);
            LoadMoreData();
        } else {
            CheckNetworkConnection.ShowMessage_Short(this, "No internet connection");
        }
    }

    private void InitHandler() {
        myHandler = new MyHandler();
    }

    private void LoadMoreData() {
        phoneListview.setOnScrollListener(new AbsListView.OnScrollListener() {
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

        phoneListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PhoneActivity.this, ProductDetailActivity.class);
                intent.putExtra("ProductData", productArrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void GetProductsDataByCategoryID(int pageNum) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String productURL = Server.ProductURL + String.valueOf(pageNum);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, productURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Json Array", response);
                int id = 0;
                String productName = "";
                int productPrice = 0;
                String productImage = "";
                String productDescript = "";
                int categoryID = 0;
                if (response != null && response.length() != 2) {
                    phoneListview.removeFooterView(footerView);
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
                            categoryID = jsonObject.getInt("categoryID");

                            //Push data to the product array list
                            productArrayList.add(new Product(id, productName, productPrice, productImage, productDescript, categoryID));
                            phoneAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    isOutOfData = true;
                    phoneListview.removeFooterView(footerView);
                    Toast.makeText(PhoneActivity.this, "Out of data", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

    private void InitActionBar() {
        setSupportActionBar(phoneToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        phoneToolbar.setNavigationOnClickListener(new View.OnClickListener() {
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

    private void InitListViewItem() {
        productArrayList = new ArrayList<>();
        phoneAdapter = new PhoneListViewAdapter(PhoneActivity.this, productArrayList);
        phoneListview.setAdapter(phoneAdapter);
    }

    private void MappingViewPhoneActivity() {
        phoneToolbar = findViewById(R.id.phone_toolbar);
        phoneListview = findViewById(R.id.phone_listview);
        footerView = LayoutInflater.from(this).inflate(R.layout.progress_bar, null);
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:{ // Add footer to layout
                    phoneListview.addFooterView(footerView);
                    break;
                }
                case 1:{ // Add data to list view
                    page++;
                    GetProductsDataByCategoryID(page);
                    isLoading = false;
                    break;
                }
            }
            super.handleMessage(msg);
        }
    }

    public class LoadingDataThread extends Thread{
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
