package com.example.android.capstone.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.AsyncResponse;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.WallpService;
import com.example.android.capstone.R;
import com.example.android.capstone.ui.adapter.CatAdapter;
import com.example.android.capstone.ui.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;
import static com.example.android.capstone.R.layout.fragment_no_internet;

/**
 * Created by DELL on 1/2/2017.
 */

public class SelectCategory extends AppCompatActivity implements AsyncResponse {

    public static final String EXTRA_CAT = "category";
    public CatAdapter catAdapter;
    public RecyclerView recyclerView;
    public NetworkUtilities networkUtilities;
    private EndlessRecyclerViewScrollListener scrollListener;


       @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        networkUtilities = new NetworkUtilities(this);
           if(networkUtilities.isInternetConnectionPresent()) {
               setContentView(R.layout.activity_select_category);
               Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_category);
               setSupportActionBar(toolbar);
               loadNextDataFromApi(1);
               recyclerView = (RecyclerView) findViewById(R.id.SelCatRecView);
               recyclerView.setHasFixedSize(true);
               StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
               recyclerView.setLayoutManager(staggeredGridLayoutManager);
               // Retain an instance so that you can call `resetState()` for fresh searches

               scrollListener = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
                   @Override
                   public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                       // Triggered only when new data needs to be appended to the list
                       // Add whatever code is needed to append new items to the bottom of the list
                       loadNextDataFromApi(page);
                   }
               };
               // Adds the scroll listener to RecyclerView
               recyclerView.addOnScrollListener(scrollListener);
               catAdapter = new CatAdapter(this);
               recyclerView.setAdapter(catAdapter);
           }
           else
               setContentView(R.layout.fragment_no_internet);
    }

    @Override
    public void processFinish(Pic output){


        if(output.getHits()!=null)
        {
            catAdapter.setPicList(output);
        }

    }
    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }


    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        String type=getIntent().getStringExtra(EXTRA_CAT);
        final WallpService wallpService = new WallpService(networkUtilities, this, this,offset,type);
        wallpService.loadWallp();
    }

}

