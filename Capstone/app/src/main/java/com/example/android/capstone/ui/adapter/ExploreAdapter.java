package com.example.android.capstone.ui.adapter;

import android.support.v7.widget.RecyclerView;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.android.capstone.ui.util.ExploreViewHolder;
import com.example.android.capstone.R;
import com.example.android.capstone.ui.SelectCategory;
import com.squareup.picasso.Picasso;
import com.example.android.capstone.model.Category;
import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by DELL on 1/2/2017.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreViewHolder> {

    private List<Category> categoryList;
    private Context context;

    public ExploreAdapter(Context context) {

        this.context = context;
        this.categoryList = new ArrayList<>();
    }


    @Override
    public com.example.android.capstone.ui.util.ExploreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_explore, null);
        final ExploreViewHolder rcv = new ExploreViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = rcv.getAdapterPosition();
                Intent intent = new Intent(context, SelectCategory.class);
                intent.putExtra(SelectCategory.EXTRA_CAT,categoryList.get(position).getCategory_draw_id());
                context.startActivity(intent);
            }
        });


        return rcv;
    }

    @Override
    public void onBindViewHolder(ExploreViewHolder holder, int position) {

        Category category = categoryList.get(position);
        int id = context.getResources().getIdentifier("com.example.android.capstone:drawable/" + category.getCategory_draw_id(), null, null);

        holder.category_name.setText(category.getCategory_name());
        holder.category.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return (categoryList == null) ? 0 : categoryList.size();
    }

    public void setCategoryList(List<Category> categories) {
        if (categories != null) {
            this.categoryList.clear();
            this.categoryList.addAll(categories);
            // The adapter needs to know that the data has changed. If we don't call this, app will crash.
            notifyDataSetChanged();
        }

    }
}
