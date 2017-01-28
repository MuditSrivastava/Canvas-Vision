package com.example.android.capstone.ui;



import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toast.*;
import com.example.android.capstone.model.Hit;
import com.example.android.capstone.R;
import com.example.android.capstone.ui.adapter.TagAdapter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;



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

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        if (item.getItemId() == R.id.action_down) {
            //  DownTask dt= new DownTask(this,hit.getWebformatURL());
            // dt.execute();
            Uri image_uri = Uri.parse(hit.getWebformatURL());
            DownloadData(image_uri);



        }
        return super.onOptionsItemSelected(item);
    }

    private long DownloadData (Uri uri) {

        long downloadReference;

        // Create request for android download manager
        DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        //Setting title of request
        request.setTitle(tags.get(0)+".jpg Download");

        //Setting description of request
        request.setDescription("Downloading from Canvas Vision");

       // request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_PICTURES,"AndroidTutorialPoint.jpg");
        request.setDestinationInExternalPublicDir("/Canvas Vision",tags.get(0)+".jpg");

        //Enqueue download and save into referenceId
        downloadReference = downloadManager.enqueue(request);

        return downloadReference;
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            //check if the broadcast message is for our enqueued download
           // long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


                Toast toast = Toast.makeText(context,tags.get(0)+".jpg Download Complete", Toast.LENGTH_LONG);
               // toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();

        }
    };

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub

        try{
            if(downloadReceiver!=null)
                unregisterReceiver(downloadReceiver);
        }catch(Exception e)
        {
        e.printStackTrace();
        }
        super.onDestroy();

    }


}