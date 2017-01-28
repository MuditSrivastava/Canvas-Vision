package com.example.android.capstone.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.android.capstone.R;
import com.example.android.capstone.model.Hit;
import com.example.android.capstone.model.Pic;
import com.example.android.capstone.ui.PicDetail;
import com.example.android.capstone.ui.util.TagViewHolder;
import com.example.android.capstone.ui.util.WallpViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.tag;
import static android.media.CamcorderProfile.get;

/**
 * Created by DELL on 1/7/2017.
 */

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {

    private List<String> tags;
    private Context context;

    public TagAdapter (Context context){

        this.context=context;
        this.tags=new ArrayList<>();

    }
    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallp_item, null);

        View itemView = LayoutInflater.from(context).inflate(R.layout.tag_item, null);
        return new TagViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {

        String tag = this.tags.get(position);

        holder.tag.setText(tag);

    }

    @Override
    public int getItemCount()
    {
        return (tags == null) ? 0 : tags.size();
    }
    public void setTagList(List<String> tags)
    {
        this.tags.clear();

            this.tags.addAll(tags);
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged();
    }



}
