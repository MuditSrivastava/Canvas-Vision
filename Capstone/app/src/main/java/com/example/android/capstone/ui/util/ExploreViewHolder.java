package com.example.android.capstone.ui.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.capstone.R;
import com.google.gson.annotations.Expose;

/**
 * Created by DELL on 1/2/2017.
 */

public class ExploreViewHolder extends RecyclerView.ViewHolder {
    public ImageView category;
    public TextView category_name;

    public ExploreViewHolder(View itemView){
        super(itemView);
        category=(ImageView)itemView.findViewById(R.id.explore_view);
        category_name=(TextView)itemView.findViewById(R.id.exptext);
    }
}
