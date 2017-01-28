package com.example.android.capstone.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.example.android.capstone.MyApplication;
import com.example.android.capstone.R;
import com.example.android.capstone.model.Pic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class FetchCatTask extends AsyncTask<Void,Void,Pic> {

    private Pic picResult= new Pic();
    private Context context;
    private AsyncResponse output;
    private int index;
    private String type;

    public FetchCatTask(Context context, AsyncResponse output, int index, String type){

        this.context=context;
        this.output=output;
        this.index=index;
        this.type=type;
    }

    @Override
    protected Pic doInBackground(Void... params) {

               Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.pixabay_api_link))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService client = retrofit.create(ApiService.class);
        Call<Pic> call;
        call=client.getCatPic(type,index);
        call.enqueue(new Callback<Pic>() {
            @Override
            public void onResponse(retrofit2.Response<Pic > response) {

                try{
                    if(!response.isSuccess()){
                        Log.d(context.getResources().getString(R.string.No_Success),response.errorBody().string());
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
                Toast toast = Toast.makeText(context, context.getResources().getString(R.string.wrong_message), Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        return picResult;

    }
}





