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

import com.example.dtstore.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout homeDrawerLayout;
    Toolbar homeToolbar;
    ViewFlipper homeViewFlipper;
    RecyclerView homeRecyclerView;
    NavigationView homeNavigationView;
    ListView homeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MappingView();
        InitActionBar();
        InitViewFlipper();
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
    }
}
