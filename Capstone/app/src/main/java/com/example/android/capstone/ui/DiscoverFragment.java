package com.example.android.capstone.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.capstone.MyApplication;
import com.example.android.capstone.ui.util.EndlessRecyclerViewScrollListener;
import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.WallpService;
import com.example.android.capstone.R;
import com.example.android.capstone.ui.adapter.WallpAdapter;
import com.example.android.capstone.network.AsyncResponse;


public class DiscoverFragment extends Fragment implements AsyncResponse {

    public WallpAdapter wallpAdapter;
    public RecyclerView recyclerView;
    public NetworkUtilities networkUtilities;
    public EndlessRecyclerViewScrollListener scrollListener;
    public WallpService wallpService;
    public int column_no;


    public DiscoverFragment() {
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
    public void onResume() {
        super.onResume();

        MyApplication.getInstance().trackScreenView(getResources().getString(R.string.discoverfragment));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            scrollListener = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    loadNextDataFromApi(page);
                }
            };
            scrollListener.resetState();
            recyclerView.addOnScrollListener(scrollListener);
            wallpAdapter = new WallpAdapter(getActivity());
            recyclerView.setAdapter(wallpAdapter);
            return view;
        }
    }

    @Override
    public void processFinish(Pic output) {

        if (output.getHits() != null) {
            wallpAdapter.setPicList(output);
        }

    }

    public void loadNextDataFromApi(int offset) {
        String type=getResources().getString(R.string.popular);
        wallpService = new WallpService(networkUtilities, getActivity(), this,offset,type);
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
