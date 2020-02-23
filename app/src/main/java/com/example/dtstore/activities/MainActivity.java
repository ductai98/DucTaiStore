package com.example.dtstore.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dtstore.R;
import com.example.dtstore.adapters.LoaiSanPhamAdapter;
import com.example.dtstore.models.LoaiSanPham;
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
    ArrayList<LoaiSanPham> loaiSanPhamArrayList;
    LoaiSanPhamAdapter loaiSanPhamAdapter;
    int id = 0;
    String tenLoaiSanPham = "";
    String hinhLoaiSanPham = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MappingView();
        if(CheckNetworkConnection.haveNetworkConnection(getApplicationContext())){
            InitActionBar();
            InitViewFlipper();
            GetLoaiSanPhamData();
        }
        else{
            CheckNetworkConnection.ShowMessage_Short(getApplicationContext(), "No Internet Connection");
            finish();
        }
    }

    private void GetLoaiSanPhamData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathLoaiSanPham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject objectLoaiSanPham = response.getJSONObject(i);
                            id = objectLoaiSanPham.getInt("id");
                            tenLoaiSanPham = objectLoaiSanPham.getString("tenloaisp");
                            hinhLoaiSanPham = objectLoaiSanPham.getString("hinhloaisp");
                            loaiSanPhamArrayList.add(new LoaiSanPham(id, tenLoaiSanPham, hinhLoaiSanPham));
                            loaiSanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                loaiSanPhamArrayList.add(3, new LoaiSanPham(0, "Liên hệ", "https://img.favpng.com/3/8/12/email-logo-icon-png-favpng-158EyDT9NQ1jfdXbwDdzD6ns6.jpg"));
                loaiSanPhamArrayList.add(4, new LoaiSanPham(0, "Thông tin", "https://image.flaticon.com/icons/svg/684/684843.svg"));
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
        loaiSanPhamArrayList = new ArrayList<>();
        loaiSanPhamArrayList.add(0, new LoaiSanPham(0, "Trang Chủ", "https://image.flaticon.com/icons/svg/684/684875.svg"));
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(loaiSanPhamArrayList, getApplicationContext());
        homeListView.setAdapter(loaiSanPhamAdapter);
    }
}
