package com.example.android.capstone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.capstone.R;
import com.example.android.capstone.model.CanvasDownloadTable;
import com.example.android.capstone.model.Hit;
import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.AsyncResponse;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.WallpService;
import com.example.android.capstone.ui.adapter.CollectionAdapter;
import com.example.android.capstone.ui.adapter.WallpAdapter;
import com.example.android.capstone.ui.util.EndlessRecyclerViewScrollListener;

import java.util.List;

import static com.example.android.capstone.ui.NavActivity.Extra_id;

/**
 * Created by DELL on 1/11/2017.
 */

public class CollectionActivity extends AppCompatActivity  {


    public CollectionAdapter catAdapter;
    public RecyclerView recyclerView_cat;
    public NetworkUtilities networkUtilities;
    private EndlessRecyclerViewScrollListener scrollListener_cat;
    private String type;
    public List<Hit> result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        networkUtilities = new NetworkUtilities(this);
        type=getIntent().getStringExtra(Extra_id);

            setContentView(R.layout.activity_select_category);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_category);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(type);
            recyclerView_cat = (RecyclerView) findViewById(R.id.SelCatRecView);
            recyclerView_cat.setHasFixedSize(true);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView_cat.setLayoutManager(staggeredGridLayoutManager);
            // Retain an instance so that you can call `resetState()` for fresh searches

            catAdapter = new CollectionAdapter(this);

            result = CanvasDownloadTable.getRows(this.getContentResolver().query(CanvasDownloadTable.CONTENT_URI, null, null, null, null), true);
            catAdapter.setHitList(result);
            recyclerView_cat.setAdapter(catAdapter);


    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();

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
