package com.example.android.capstone.ui;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.capstone.MyApplication;
import com.example.android.capstone.model.Category;
import com.example.android.capstone.R;
import com.example.android.capstone.ui.adapter.ExploreAdapter;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.capstone.R.drawable.science;


public class ExploreFragment extends Fragment {

    public RecyclerView recyclerView;
    public List<Category> categoryList= new ArrayList<>();
    public ExploreAdapter exploreAdapter;
    public int column_no;

    public ExploreFragment() {
    }

    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView(getResources().getString(R.string.explorefragment));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.expRecView);
        recyclerView.setHasFixedSize(true);
        checkScreenSize();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), column_no));
        exploreAdapter= new ExploreAdapter(getActivity());
        populate();
        exploreAdapter.setCategoryList(categoryList);
        recyclerView.setAdapter(exploreAdapter);
        return view;
    }

    public void populate(){
        categoryList.add(new Category(getResources().getString(R.string.Animals),getResources().getString(R.string.animals)));
        categoryList.add(new Category(getResources().getString(R.string.Textures),getResources().getString(R.string.backgrounds)));
        categoryList.add(new Category(getResources().getString(R.string.Architecture),getResources().getString(R.string.buildings)));
        categoryList.add(new Category(getResources().getString(R.string.Business),getResources().getString(R.string.business)));
        categoryList.add(new Category(getResources().getString(R.string.Communication),getResources().getString(R.string.computer)));
        categoryList.add(new Category(getResources().getString(R.string.Education),getResources().getString(R.string.education)));
        categoryList.add(new Category(getResources().getString(R.string.Fashion),getResources().getString(R.string.fashion)));
        categoryList.add(new Category(getResources().getString(R.string.Emotions),getResources().getString(R.string.feelings)));
        categoryList.add(new Category(getResources().getString(R.string.Food),getResources().getString(R.string.food)));
        categoryList.add(new Category(getResources().getString(R.string.Health),getResources().getString(R.string.health)));
        categoryList.add(new Category(getResources().getString(R.string.Craft),getResources().getString(R.string.industry)));
        categoryList.add(new Category(getResources().getString(R.string.Music),getResources().getString(R.string.music)));
        categoryList.add(new Category(getResources().getString(R.string.Nature),getResources().getString(R.string.nature)));
        categoryList.add(new Category(getResources().getString(R.string.People),getResources().getString(R.string.people)));
        categoryList.add(new Category(getResources().getString(R.string.Places),getResources().getString(R.string.places)));
        categoryList.add(new Category(getResources().getString(R.string.Religion),getResources().getString(R.string.religion)));
        categoryList.add(new Category(getResources().getString(R.string.Technology),getResources().getString(R.string.science)));
        categoryList.add(new Category(getResources().getString(R.string.Sports),getResources().getString(R.string.sports)));
        categoryList.add(new Category(getResources().getString(R.string.Transportation),getResources().getString(R.string.transportation)));
        categoryList.add(new Category(getResources().getString(R.string.Travel),getResources().getString(R.string.travel)));
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
