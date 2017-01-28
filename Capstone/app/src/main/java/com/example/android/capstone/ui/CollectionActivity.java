package com.example.android.capstone.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v4.content.CursorLoader;
import android.view.MenuItem;
import com.example.android.capstone.R;
import com.example.android.capstone.model.CanvasDownloadTable;
import com.example.android.capstone.model.Hit;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.ui.adapter.CollectionAdapter;
import java.util.ArrayList;
import java.util.List;


public class CollectionActivity extends AppCompatActivity  {

    public CollectionAdapter catAdapter;
    public RecyclerView recyclerView_cat;
    public NetworkUtilities networkUtilities;
    public List<Hit> result;
    public static final int LOADER_ID = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkUtilities = new NetworkUtilities(this);
        setContentView(R.layout.activity_select_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_category);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("collections");
        recyclerView_cat = (RecyclerView) findViewById(R.id.SelCatRecView);
        recyclerView_cat.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView_cat.setLayoutManager(staggeredGridLayoutManager);
        catAdapter = new CollectionAdapter(this);
        getSupportLoaderManager().initLoader(LOADER_ID,null, contactsLoader);
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


    private LoaderManager.LoaderCallbacks<Cursor> contactsLoader =
            new LoaderManager.LoaderCallbacks<Cursor>(){

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {

                    CursorLoader cursorLoader = new CursorLoader(CollectionActivity.this,
                            CanvasDownloadTable.CONTENT_URI, // URI
                            null, // projection fields
                            null, // the selection criteria
                            null, // the selection args
                            null // the sort order
                    );

                    return cursorLoader;
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                    result = CanvasDownloadTable.getRows(cursor, true);
                    catAdapter.setHitList(result);
                    recyclerView_cat.setAdapter(catAdapter);
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    result = new ArrayList<>();
                    catAdapter.setHitList(result);
                    recyclerView_cat.setAdapter(catAdapter);
                }
            };
}




