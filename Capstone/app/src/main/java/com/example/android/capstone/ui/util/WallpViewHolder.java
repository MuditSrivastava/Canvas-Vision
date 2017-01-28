package com.example.android.capstone.ui.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.android.capstone.R;
import com.example.android.capstone.ui.util.DynamicHeightImageView;

/**
 * Created by DELL on 12/13/2016.
 */

public class WallpViewHolder extends RecyclerView.ViewHolder {

    public DynamicHeightImageView discWallp;

    public WallpViewHolder(View itemView){

        super(itemView);
        discWallp =(DynamicHeightImageView)itemView.findViewById(R.id.wallpView);
    }
}
