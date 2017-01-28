package com.example.android.capstone;

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

import java.util.ArrayList;
import java.util.List;

import static com.example.android.capstone.WallpService.picResult;


public class DiscoverFragment extends Fragment implements AsyncResponse {
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    public  WallpAdapter wallpAdapter;
    public Pic pic ;
    public List<Hit> hit= new ArrayList<>();
    public RecyclerView recyclerView;
    public NetworkUtilities networkUtilities;
    public  Fragment currentFragment ;
    private EndlessRecyclerViewScrollListener scrollListener;


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

      //  requestDataRefresh();

        loadNextDataFromApi(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(!networkUtilities.isInternetConnectionPresent()){
            View view = inflater.inflate(R.layout.fragment_no_internet, container, false);
            return view;
        }
        else{
            View view = inflater.inflate(R.layout.fragment_discover, container, false);
            recyclerView=(RecyclerView) view.findViewById(R.id.discRecView);
            recyclerView.setHasFixedSize(true);
          //  recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
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
       /*     for(int i=0;i<30;i++) {
                hit.add(new Hit());
            }
            pic= new Pic();*/
            wallpAdapter = new WallpAdapter(getActivity());
            //pic.setHits(hit);
           // wallpAdapter.setPicList(pic)

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

    public void requestDataRefresh(){

        final WallpService wallpService = new WallpService(networkUtilities, getActivity(), this,1);
        wallpService.loadWallp();

    }
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        final WallpService wallpService = new WallpService(networkUtilities, getActivity(), this,offset);
        wallpService.loadWallp();
    }

}
