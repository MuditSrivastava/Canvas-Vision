package com.example.android.capstone.ui;

import android.content.res.Configuration;
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
    public WallpAdapter navAdapter;
    public RecyclerView recyclerView_nav;
    public NetworkUtilities networkUtilities;
    private EndlessRecyclerViewScrollListener scrollListener_nav;
    private String type;
    public int column_no;

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
            recyclerView_nav = (RecyclerView) findViewById(R.id.SelCatRecView);
            recyclerView_nav.setHasFixedSize(true);
            checkScreenSize();
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(column_no, StaggeredGridLayoutManager.VERTICAL);
            recyclerView_nav.setLayoutManager(staggeredGridLayoutManager);
            scrollListener_nav = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    loadNextDataFromApi(page);
                }
            };
            recyclerView_nav.addOnScrollListener(scrollListener_nav);
            navAdapter = new WallpAdapter(this);
            recyclerView_nav.setAdapter(navAdapter);
        }
        else
            setContentView(R.layout.fragment_no_internet);
    }

    @Override
    public void processFinish(Pic output){

        if(output.getHits()!=null)
        {
            navAdapter.setPicList(output);
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

    public void checkScreenSize() {

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:

                column_no = 4;
                break;
            case Configuration.SCREENLAYOUT_SIZE_UNDEFINED:
                column_no = 3;
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                column_no = 3;
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                column_no = 2;
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                column_no = 2;
                break;
            default:
                column_no = 2;
        }
    }
}
