package com.example.dtstore.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        MappingViewPhoneActivity();
        InitListViewItem();
        InitActionBar();
        GetCategoryID();
        if(CheckNetworkConnection.haveNetworkConnection(getApplicationContext())){
            GetProductsDataByCategoryID(page);
        }else{
            CheckNetworkConnection.ShowMessage_Short(this, "No internet connection");
        }
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
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        //Iterate through all JsonObjects of JsonArray
                        for(int i =0;i<jsonArray.length();i++){

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
    }
}
