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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.capstone.MyApplication;
import com.example.android.capstone.model.CanvasDownloadTable;
import com.example.android.capstone.model.Hit;
import com.example.android.capstone.R;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.ui.adapter.TagAdapter;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class PicDetail extends AppCompatActivity {

    public static final String EXTRA_PIC = "picture";
    public static final String origin = "caller";
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
    public NetworkUtilities networkUtilities;
    public RecyclerView recyclerView;
    public TagAdapter tagAdapter;
    public boolean isDownloaded=false;
    public boolean isCallerCollection =false;
    private Menu menu;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkUtilities = new NetworkUtilities(this);
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
        title=hit.getTags();
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
        file = new File(Environment.getExternalStoragePublicDirectory("/Canvas Vision"), hit.getId() + ".jpg");
        if(getIntent().hasExtra(origin)){

            Picasso.with(this)
                    .load(file)
                    .placeholder(R.drawable.plh)
                    .into(wallp);
            isCallerCollection=true;
        }
        else {
            Picasso.with(this)
                    .load(hit.getWebformatURL())
                    .placeholder(R.drawable.plh)
                    .into(wallp);
        }
        user_id.setText(hit.getUser());
        downloads.setText(String.valueOf(hit.getDownloads()));
        fav.setText(String.valueOf(hit.getFavorites()));
        if(!networkUtilities.isInternetConnectionPresent()){
            Picasso.with(this)
                    .load(R.drawable.memb)
                    .transform(new CropCircleTransformation())
                    .into(user_image);
        }
        else {
            if (!hit.getUserImageURL().isEmpty()) {
                Picasso.with(this)
                        .load(hit.getUserImageURL())
                        .transform(new CropCircleTransformation())
                        .into(user_image);
            } else {
                Picasso.with(this)
                        .load(R.drawable.memb)
                        .transform(new CropCircleTransformation())
                        .into(user_image);
            }
        }
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(!isCallerCollection){
            this.menu=menu;
            getMenuInflater().inflate(R.menu.menu_details, menu);}
        else{
            this.menu=menu;
            getMenuInflater().inflate(R.menu.menu_details_collection, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        if (item.getItemId() == R.id.action_down) {
            if(!fileExistance()) {
                String uri=hit.getWebformatURL();
                uri=uri.replaceAll("_640","_960");
                Uri image_uri = Uri.parse(uri);
                DownloadData(image_uri);
                item.setEnabled(false);

            }
            else{
                Toast toast = Toast.makeText(this,"Image Already Downloaded", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if(item.getItemId()==R.id.action_set){

            if(fileExistance()) {
                Uri sendUri2 = Uri.fromFile(file);
                Log.d("URI:", sendUri2.toString());
                Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
                intent.setDataAndType(sendUri2, "image/jpg");
                intent.putExtra("mimeType", "image/jpg");
                startActivityForResult(Intent.createChooser(intent, "Set As"), 200);
            }
            else{
                Toast toast = Toast.makeText(this,"Please Download First", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private long DownloadData (Uri uri) {

        long downloadReference;
        DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(tags.get(0)+".jpg Download");
        request.setDescription("Downloading from Canvas Vision");
        request.setDestinationInExternalPublicDir("/Canvas Vision",hit.getId()+".jpg");
        downloadReference = downloadManager.enqueue(request);
        getContentResolver().insert(CanvasDownloadTable.CONTENT_URI,CanvasDownloadTable.getContentValues(hit,false));
        getContentResolver().notifyChange(CanvasDownloadTable.CONTENT_URI, null);

        return downloadReference;
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast toast = Toast.makeText(context,tags.get(0)+".jpg Download Complete", Toast.LENGTH_SHORT);
            toast.show();
            isDownloaded=true;
            MenuItem menuItem=menu.findItem( R.id.action_down);
            menuItem.setEnabled(true);
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
            MyApplication.getInstance().trackException(e);

            e.printStackTrace();
        }
        super.onDestroy();
    }

    public boolean fileExistance(){
        return file.exists();
    }

}