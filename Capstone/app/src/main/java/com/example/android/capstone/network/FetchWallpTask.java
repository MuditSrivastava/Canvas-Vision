package com.example.android.capstone.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.android.capstone.MyApplication;
import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.NetworkUtilities;
import com.example.android.capstone.network.ApiService;
import com.example.android.capstone.network.AsyncResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by DELL on 12/14/2016.
 */

public class FetchWallpTask extends AsyncTask <Void,Void,Pic> {



    private final NetworkUtilities networkUtilities;
    private Pic picResult= new Pic();
    private Context context;
    public ProgressDialog progressDialog;
    private AsyncResponse output;
    private int index;
    private String type;



    public FetchWallpTask(Context context, NetworkUtilities networkUtilities, AsyncResponse output, int index){

        this.context=context;
        this.networkUtilities=networkUtilities;
        this.progressDialog = new ProgressDialog(context);
        this.output=output;
        this.index=index;
    }


    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Just a sec ...");
    }

    @Override
    protected void onPostExecute(Pic result) {

        progressDialog.dismiss();

    }

    @Override
    protected Pic doInBackground(Void... params) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ApiService client = retrofit.create(ApiService.class);
        Call<Pic> call;
        call = client.getPopularPic(index);



        call.enqueue(new Callback<Pic>() {
            @Override
            public void onResponse(retrofit2.Response<Pic > response) {

                try{
                    if(!response.isSuccess()){
                        Log.d("No Success",response.errorBody().string());
                    }
                    else{
                        picResult=response.body();
                        if(picResult!=null)
                        {
                            output.processFinish(picResult);
                        }

                    }
                }
                catch(Exception e) {
                    MyApplication.getInstance().trackException(e);

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        return picResult;

    }


}





