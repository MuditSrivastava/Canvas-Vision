package com.example.android.capstone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.capstone.model.Hit;
import com.example.android.capstone.R;
import com.example.android.capstone.ui.adapter.TagAdapter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static android.R.id.empty;


/**
 * Created by DELL on 12/30/2016.
 */

public class PicDetail extends AppCompatActivity {

    public static final String EXTRA_PIC = "picture";

    private Hit hit;
    private ImageView wallp;
    private TextView tag_title;
    private TextView fav;
    private TextView downloads;
    private ImageView user_image;
    private String title;
    private TextView user_id;
    private List<String> tags=new ArrayList<String>();
    int first =0;
    public RecyclerView recyclerView;
    public TagAdapter tagAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        tag_title=(TextView)findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra(EXTRA_PIC)) {
            hit = getIntent().getParcelableExtra(EXTRA_PIC);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a Hit parcelable");
        }
     //   String firstWord = null;
        title=hit.getTags();
       /* if(title.contains(",")){
            firstWord= title.substring(0, title.indexOf(","));
        }
        tag_title.setText(firstWord);
tags.add(firstWord);*/

        while(title.contains(",")){
            String f=title.substring(0,title.indexOf(","));
            tags.add(f);
           first=title.indexOf(",");
            title=title.substring(++first);

        }
        tags.add(title);
        tag_title.setText(tags.get(0));
        wallp = (ImageView) findViewById(R.id.wallpaper_detail);
        fav=(TextView)findViewById(R.id.fav);
        user_id=(TextView)findViewById(R.id.user_name);
        user_image=(ImageView)findViewById(R.id.user_image);
        downloads=(TextView)findViewById(R.id.down);
        recyclerView=(RecyclerView)findViewById(R.id.tagsRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tagAdapter= new TagAdapter(this);
        tagAdapter.setTagList(tags);
        recyclerView.setAdapter(tagAdapter);

        Picasso.with(this)
                .load(hit.getWebformatURL())
                .into(wallp);
        user_id.setText(hit.getUser());
        downloads.setText(String.valueOf(hit.getDownloads()));
        fav.setText(String.valueOf(hit.getFavorites()));
if(!hit.getUserImageURL().isEmpty()) {
    Picasso.with(this)
            .load(hit.getUserImageURL())
            .transform(new CropCircleTransformation())
            .into(user_image);


}
        else {
    Picasso.with(this)
            .load(R.drawable.memb)
            .transform(new CropCircleTransformation())
            .into(user_image);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}