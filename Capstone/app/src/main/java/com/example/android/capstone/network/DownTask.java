package com.example.android.capstone.network;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.android.capstone.model.Pic;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import static android.R.attr.type;
import static com.example.android.capstone.network.WallpService.picResult;

/**
 * Created by DELL on 1/9/2017.
 */

public class DownTask extends AsyncTask<Void,Void,Bitmap> {

    private Context context;
    public ProgressDialog progressDialog;
    private String url;
    private Bitmap result;




    public DownTask(Context context, String url){

        this.context=context;
        this.progressDialog = new ProgressDialog(context);
        this.url=url;

    }


    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Just a sec ...");
    }

    @Override
    protected void onPostExecute(Bitmap result) {

        progressDialog.dismiss();

    }

    @Override
    protected Bitmap doInBackground(Void... params) {




                try { result= Picasso.with(context)
                        .load(url)
                        .get();

                    WallpaperManager wallpaperManager ;
wallpaperManager= WallpaperManager.getInstance(context);

                    wallpaperManager.setBitmap(result);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }




        return result;

    }


}







