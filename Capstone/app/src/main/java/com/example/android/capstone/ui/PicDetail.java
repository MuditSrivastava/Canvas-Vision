package com.example.android.capstone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.capstone.model.Hit;
import com.example.android.capstone.R;
import com.squareup.picasso.Picasso;

/**
 * Created by DELL on 12/30/2016.
 */

public class PicDetail extends AppCompatActivity {

    public static final String EXTRA_PIC = "picture";

    private Hit hit;
    private ImageView wallp;
    private TextView tag_title;
    private String title;


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
        String firstWord = null;
        title=hit.getTags();
        if(title.contains(",")){
            firstWord= title.substring(0, title.indexOf(","));
        }
        tag_title.setText(firstWord);

        wallp = (ImageView) findViewById(R.id.wallpaper_detail);

        Picasso.with(this)
                .load(hit.getWebformatURL())
                .into(wallp);

    }
}