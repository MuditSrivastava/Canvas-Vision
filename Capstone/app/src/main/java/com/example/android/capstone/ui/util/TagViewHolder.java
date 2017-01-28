package com.example.android.capstone.ui.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.capstone.R;

/**
 * Created by DELL on 1/7/2017.
 */

public class TagViewHolder extends RecyclerView.ViewHolder {

    public TextView tag;

    public TagViewHolder(View itemView){

        super(itemView);
        tag =(TextView) itemView.findViewById(R.id.tag_item);
    }
}
