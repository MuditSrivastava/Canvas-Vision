package com.example.android.capstone.ui.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.android.capstone.R;

public class WallpViewHolder extends RecyclerView.ViewHolder {

    public DynamicHeightImageView discWallp;

    public WallpViewHolder(View itemView){

        super(itemView);
        discWallp =(DynamicHeightImageView)itemView.findViewById(R.id.wallpView);
    }
}
