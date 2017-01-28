package com.example.android.capstone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.example.android.capstone.R;
import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.AsyncResponse;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.WallpService;
import com.example.android.capstone.ui.adapter.WallpAdapter;
import com.example.android.capstone.ui.util.EndlessRecyclerViewScrollListener;

public class NavActivity extends AppCompatActivity implements AsyncResponse {

    public static final String Extra_id = "nav_id";
    public WallpAdapter catAdapter;
    public RecyclerView recyclerView_cat;
    public NetworkUtilities networkUtilities;
    private EndlessRecyclerViewScrollListener scrollListener_cat;
    private String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        networkUtilities = new NetworkUtilities(this);
        type=getIntent().getStringExtra(Extra_id);
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
            scrollListener_cat = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    loadNextDataFromApi(page);
                }
            };
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadNextDataFromApi(int offset) {
        final WallpService wallpService = new WallpService(networkUtilities, this, this,offset,type);
        wallpService.loadWallp();
    }
}
