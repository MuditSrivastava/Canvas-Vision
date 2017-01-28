package com.example.android.capstone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.AsyncResponse;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.WallpService;
import com.example.android.capstone.R;
import com.example.android.capstone.ui.adapter.WallpAdapter;
import com.example.android.capstone.ui.util.EndlessRecyclerViewScrollListener;

import static android.R.attr.colorAccent;
import static android.R.attr.offset;

/**
 * Created by DELL on 1/2/2017.
 */

public class SelectCategory extends AppCompatActivity implements AsyncResponse {

    public static final String EXTRA_CAT = "category";
    public WallpAdapter catAdapter;
    public RecyclerView recyclerView_cat;
    public NetworkUtilities networkUtilities;
    private EndlessRecyclerViewScrollListener scrollListener_cat;
    private String type;

       @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        networkUtilities = new NetworkUtilities(this);
            type=getIntent().getStringExtra(EXTRA_CAT);
           if(networkUtilities.isInternetConnectionPresent()) {
               setContentView(R.layout.activity_select_category);
               Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_category);
               setSupportActionBar(toolbar);
               getSupportActionBar().setDisplayHomeAsUpEnabled(true);
              getSupportActionBar().setTitle(type);
               loadNextDataFromApi(1);
               recyclerView_cat = (RecyclerView) findViewById(R.id.SelCatRecView);
               recyclerView_cat.setHasFixedSize(true);
               StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
               recyclerView_cat.setLayoutManager(staggeredGridLayoutManager);
               // Retain an instance so that you can call `resetState()` for fresh searches

               scrollListener_cat = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
                   @Override
                   public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                       // Triggered only when new data needs to be appended to the list
                       // Add whatever code is needed to append new items to the bottom of the list
                       loadNextDataFromApi(page);
                   }
               };
               // Adds the scroll listener to RecyclerView
               recyclerView_cat.addOnScrollListener(scrollListener_cat);
               catAdapter = new WallpAdapter(this);
               recyclerView_cat.setAdapter(catAdapter);
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


        final WallpService wallpService = new WallpService(networkUtilities, this, this,offset,type);
        wallpService.loadWallp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



}

