package com.example.android.capstone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    public NetworkUtilities networkUtilities = new NetworkUtilities(getContext());

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



        WallpService wallpService = new WallpService(networkUtilities,getActivity(),this);
      wallpService.loadWallp();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_discover, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.discRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,1);
        for(int i=0;i<30;i++) {
            hit.add(new Hit());
        }
        pic= new Pic();
        wallpAdapter = new WallpAdapter(getActivity());
        pic.setHits(hit);
        wallpAdapter.setPicList(pic);

        recyclerView.setAdapter(wallpAdapter);
        return view;
    }

    @Override
    public void processFinish(Pic output){

        if(output.getHits()!=null)
        {

            wallpAdapter.setPicList(output);
        }

    }

}
