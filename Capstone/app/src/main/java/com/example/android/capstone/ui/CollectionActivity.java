package com.example.android.capstone.ui;

import android.database.Cursor;
import android.graphics.Movie;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.AsyncResponse;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.WallpService;
import com.example.android.capstone.ui.adapter.CollectionAdapter;
import com.example.android.capstone.ui.adapter.WallpAdapter;
import com.example.android.capstone.ui.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.capstone.ui.NavActivity.Extra_id;
import static java.security.AccessController.getContext;

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
    public static final int LOADER_ID = 1;

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

        getSupportLoaderManager().initLoader(LOADER_ID,null, contactsLoader);
        // result = CanvasDownloadTable.getRows(this.getContentResolver().query(CanvasDownloadTable.CONTENT_URI, null, null, null, null), true);
         // catAdapter.setHitList(result);
         //recyclerView_cat.setAdapter(catAdapter);


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



    private LoaderManager.LoaderCallbacks<Cursor> contactsLoader =
            new LoaderManager.LoaderCallbacks<Cursor>(){

                // Create and return the actual cursor loader for the contacts data
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    // Define the columns to retrieve
                   // String[] projectionFields = new String[] { CanvasDownloadTable.};
                    // Construct the loader
                    CursorLoader cursorLoader = new CursorLoader(CollectionActivity.this,
                            CanvasDownloadTable.CONTENT_URI, // URI
                            null, // projection fields
                            null, // the selection criteria
                            null, // the selection args
                            null // the sort order
                    );
                    // Return the loader for use
                    return cursorLoader;
                }

                // When the system finishes retrieving the Cursor through the CursorLoader,
                // a call to the onLoadFinished() method takes place.
                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                    // The swapCursor() method assigns the new Cursor to the adapter
                    //adapter.swapCursor(cursor);
                    result = CanvasDownloadTable.getRows(cursor, true);
                    catAdapter.setHitList(result);
                    recyclerView_cat.setAdapter(catAdapter);
                }

                // This method is triggered when the loader is being reset
                // and the loader data is no longer available. Called if the data
                // in the provider changes and the Cursor becomes stale.
                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    // Clear the Cursor we were using with another call to the swapCursor()
                    result = new ArrayList<>();
                    catAdapter.setHitList(result);
                    recyclerView_cat.setAdapter(catAdapter);
                }
            };
}




