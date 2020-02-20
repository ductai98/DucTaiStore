package com.example.dtstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

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
    }

    private void MappingView() {
        homeToolbar = findViewById(R.id.home_toolbar);
        homeViewFlipper = findViewById(R.id.home_viewflipper);
        homeRecyclerView = findViewById(R.id.home_recyclerView);
        homeNavigationView = findViewById(R.id.home_navigation);
        homeListView = findViewById(R.id.home_listview);
    }
}
