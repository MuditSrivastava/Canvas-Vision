package com.example.android.capstone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.capstone.R;
import com.example.android.capstone.ui.util.TagViewHolder;
import java.util.ArrayList;
import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {

    private List<String> tags;
    private Context context;

    public TagAdapter (Context context){

        this.context=context;
        this.tags=new ArrayList<>();

    }
    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
        notifyDataSetChanged();
    }
}
