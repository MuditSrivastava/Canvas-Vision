package com.example.android.capstone;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;


/**
 * Created by DELL on 12/13/2016.
 */

public class WallpAdapter extends RecyclerView.Adapter<WallpViewHolder> {

    private List<Hit> hit;
    private Context context;

    public WallpAdapter (Context context){

        this.context=context;
        this.hit=new ArrayList<>();
    }

    @Override
    public WallpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallp_item, null);
        WallpViewHolder rcv = new WallpViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(WallpViewHolder holder, int position) {

        Hit photo = hit.get(position);

        // This is how we use Picasso to load images from the internet.
        Picasso.with(context)
                .load(photo.getWebformatURL())
                .resize(300,200)
                .placeholder(R.drawable.ph)
                .error(R.drawable.phe)
                .into(holder.discWallp);
    }

    @Override
    public int getItemCount()
    {
        return (hit == null) ? 0 : hit.size();
    }

    public void setPicList(Pic picList)
    {
        this.hit.clear();
        if(picList.getHits()!=null)
            this.hit.addAll(picList.getHits());
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged();
    }



}
