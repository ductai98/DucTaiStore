package com.example.dtstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dtstore.R;
import com.example.dtstore.RecyclerViewItemDecoration;
import com.example.dtstore.adapters.LatestProductCardAdapter;
import com.example.dtstore.adapters.CategoryAdapter;
import com.example.dtstore.models.Cart;
import com.example.dtstore.models.Category;
import com.example.dtstore.models.Product;
import com.example.dtstore.utilities.CheckNetworkConnection;
import com.example.dtstore.utilities.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout homeDrawerLayout;
    Toolbar homeToolbar;
    ViewFlipper homeViewFlipper;
    RecyclerView homeRecyclerView;
    NavigationView homeNavigationView;
    ListView homeListView;
    ArrayList<Category> categoryArrayList;
    CategoryAdapter categoryAdapter;
    int id = 0;
    String categoryName = "";
    String categoryImage = "";
    ArrayList<Product> productArrayList;
    LatestProductCardAdapter latestProductCardAdapter;
    public static ArrayList<Cart> cartArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MappingView();
        if(CheckNetworkConnection.haveNetworkConnection(getApplicationContext())){
            InitActionBar();
            InitViewFlipper();
            InitRecyclerViewLatestProduct();
            GetCategoryData();
            GetLatestProductData();
            OnClickMenuItem();
        }
        else{
            CheckNetworkConnection.ShowMessage_Short(getApplicationContext(), "No Internet Connection");
            finish();
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
                Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(cartIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    //Create event item click on Navigation Menu
    private void OnClickMenuItem() {
        homeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Catch the position of menu item and launch the correspond Activity
                switch (position){
                    case 0:{
                        homeDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    //Phone Activity
                    case 1:{
                        if(CheckNetworkConnection.haveNetworkConnection(getApplicationContext())){
                            //Launch Phone activity
                            Intent intentPhone = new Intent(MainActivity.this, PhoneActivity.class);
                            //Send CategoryID to Phone Activity
                            intentPhone.putExtra("CategoryID", categoryArrayList.get(position).getId());
                            startActivity(intentPhone);
                        }
                        else{
                            CheckNetworkConnection.ShowMessage_Short(getApplicationContext(), "No internet connection");
                        }
                        homeDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    //Laptop Activity
                    case 2:{
                        if(CheckNetworkConnection.haveNetworkConnection(getApplicationContext())){
                            //Launch activity
                            Intent intentPhone = new Intent(MainActivity.this, LaptopActivity.class);
                            //Send CategoryID to Laptop Activity
                            intentPhone.putExtra("CategoryID", categoryArrayList.get(position).getId());
                            startActivity(intentPhone);
                        }
                        else{
                            CheckNetworkConnection.ShowMessage_Short(getApplicationContext(), "No internet connection");
                        }
                        homeDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    //Contact Activity
                    case 3:{
                        if(CheckNetworkConnection.haveNetworkConnection(getApplicationContext())){
                            //Launch activity
                            Intent intentPhone = new Intent(MainActivity.this, ContactActivity.class);
                            startActivity(intentPhone);
                        }
                        else{
                            CheckNetworkConnection.ShowMessage_Short(getApplicationContext(), "No internet connection");
                        }
                        homeDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    //Information Activity
                    case 4:{
                        if(CheckNetworkConnection.haveNetworkConnection(getApplicationContext())){
                            //Launch activity
                            Intent intentPhone = new Intent(MainActivity.this, InformationActivity.class);
                            intentPhone.putExtra("CategoryID", categoryArrayList.get(position).getId());
                            startActivity(intentPhone);
                        }
                        else{
                            CheckNetworkConnection.ShowMessage_Short(getApplicationContext(), "No internet connection");
                        }
                        homeDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                }
            }
        });
    }

    private void InitRecyclerViewLatestProduct() {
        //Init latest product list
        productArrayList = new ArrayList<>();

        //Init adapter for latest product
        latestProductCardAdapter = new LatestProductCardAdapter(productArrayList);
        homeRecyclerView.setHasFixedSize(true);

        //Set up layout for recycler view
        GridLayoutManager latestProductLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        homeRecyclerView.setLayoutManager(latestProductLayoutManager);

        //Init items decoration of recycler view
        RecyclerViewItemDecoration itemDecoration = new RecyclerViewItemDecoration(10, 10);
        homeRecyclerView.addItemDecoration(itemDecoration);

        //Attach adapter
        homeRecyclerView.setAdapter(latestProductCardAdapter);
    }

    private void GetLatestProductData() {
        //Use Volley to get data from json format;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.LatestProductUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int id = 0;
                    String productName = "";
                    int productPrice = 0;
                    String productImage = "";
                    String productDescript = "";
                    int idCategory = 0;
                    //Iterate through all JsonObjects of JsonArray
                    for(int i = 0; i<response.length();i++){
                        try {

                            //Get attribute from JsonObject
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            productName = jsonObject.getString("productName");
                            productImage = jsonObject.getString("productImage");
                            productPrice = jsonObject.getInt("productPrice");
                            productDescript = jsonObject.getString("productDescript");
                            idCategory = jsonObject.getInt("idCategory");
                            productArrayList.add(new Product(id, productName, productPrice, productImage, productDescript, idCategory));
                            latestProductCardAdapter.notifyDataSetChanged();
                            //end

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "onErrorResponse: " + error);
                CheckNetworkConnection.ShowMessage_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetCategoryData() {
        //Use Volley to get data from json format;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.CategoryUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    //Iterate through all JsonObjects of JsonArray
                    for(int i = 0; i < response.length(); i++){
                        try {
                            //Get attribute from JsonObject
                            JSONObject categoryObject = response.getJSONObject(i);
                            id = categoryObject.getInt("id");
                            categoryName = categoryObject.getString("categoryName");
                            categoryImage = categoryObject.getString("categoryImage");
                            categoryArrayList.add(new Category(id, categoryName, categoryImage));
                            categoryAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                categoryArrayList.add(3, new Category(0, "Liên hệ",
                        "https://download.seaicons.com/icons/iconsmind/outline/512/Mail-Read-icon.png"));
                categoryArrayList.add(4, new Category(0, "Thông tin",
                        "https://png.pngtree.com/svg/20151015/7d4d97ad8b.png"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckNetworkConnection.ShowMessage_Short(getApplicationContext(), error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void InitViewFlipper() {

        ArrayList<String> listAdImages = new ArrayList<>();
        listAdImages.add("https://i.ytimg.com/vi/1LfM0fgxio4/maxresdefault.jpg");
        listAdImages.add("https://trungtamquangcao.com/wp-content/uploads/2019/03/lam-pano-quang-cao-gia-re-1.jpg");
        listAdImages.add("https://genk.mediacdn.vn/2016/dien-may-xanh-1481020960407.png");

        int size = listAdImages.size();
        for(int i =0; i<size;i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(listAdImages.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            homeViewFlipper.addView(imageView);
        }

        homeViewFlipper.setFlipInterval(5000);
        homeViewFlipper.setAutoStart(true);
        Animation viewFlipperSlideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation viewFlipperSlideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        homeViewFlipper.setInAnimation(viewFlipperSlideIn);
        homeViewFlipper.setOutAnimation(viewFlipperSlideOut);
    }

    private void InitActionBar() {
        setSupportActionBar(homeToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeToolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }



    private void MappingView() {
        homeDrawerLayout = findViewById(R.id.home_drawerlayout);
        homeToolbar = findViewById(R.id.home_toolbar);
        homeViewFlipper = findViewById(R.id.home_viewflipper);
        homeRecyclerView = findViewById(R.id.home_recyclerView);
        homeNavigationView = findViewById(R.id.home_navigation);
        homeListView = findViewById(R.id.home_listview);
        categoryArrayList = new ArrayList<>();
        categoryArrayList.add(0, new Category(0, "Trang Chủ", "https://download.seaicons.com/icons/icons8/windows-8/512/Very-Basic-Home-icon.png"));
        categoryAdapter = new CategoryAdapter(categoryArrayList, getApplicationContext());
        homeListView.setAdapter(categoryAdapter);

        if(cartArrayList != null){
        }else{
            cartArrayList = new ArrayList<>();
        }
    }
}
