package com.example.android.capstone.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.android.capstone.ui.util.EndlessRecyclerViewScrollListener;
import com.example.android.capstone.model.Hit;
import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.WallpService;
import com.example.android.capstone.R;

import java.util.ArrayList;
import java.util.List;
import com.example.android.capstone.ui.adapter.WallpAdapter;
import com.example.android.capstone.network.AsyncResponse;


public class DiscoverFragment extends Fragment implements AsyncResponse {

    public WallpAdapter wallpAdapter;
    public List<Hit> hit= new ArrayList<>();
    public RecyclerView recyclerView;
    public NetworkUtilities networkUtilities;
    public EndlessRecyclerViewScrollListener scrollListener;
    WallpService wallpService;
    public int column_no;


    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkUtilities = new NetworkUtilities(getActivity());

        loadNextDataFromApi(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(!networkUtilities.isInternetConnectionPresent()){
            return  inflater.inflate(R.layout.fragment_no_internet, container, false);

        }
        else{
            View view = inflater.inflate(R.layout.fragment_discover, container, false);
            recyclerView=(RecyclerView) view.findViewById(R.id.discRecView);
            recyclerView.setHasFixedSize(true);
            checkScreenSize();
          StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(column_no,StaggeredGridLayoutManager.VERTICAL);
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
            scrollListener.resetState();
            // Adds the scroll listener to RecyclerView
            recyclerView.addOnScrollListener(scrollListener);
             wallpAdapter = new WallpAdapter(getActivity());
            recyclerView.setAdapter(wallpAdapter);
            return view;
        }

    }

    @Override
    public void processFinish(Pic output){


        if(output.getHits()!=null)
        {
            wallpAdapter.setPicList(output);
        }

    }



    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`
        String type="latest";
        wallpService = new WallpService(networkUtilities, getActivity(), this,offset,type);
        wallpService.loadWallp();
    }
    public void checkScreenSize() {

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:

                column_no = 4;
                //set action

                break;
            case Configuration.SCREENLAYOUT_SIZE_UNDEFINED:
                column_no = 3;
                //set action
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                column_no = 3;
                //set action
                break;

            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                column_no = 2;
                //set action
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                //set action
                column_no = 2;
                break;
            default:
                column_no = 2;


        }


    }
}
