package com.example.android.capstone.network;

import android.content.Context;

import com.example.android.capstone.model.Pic;
import com.example.android.capstone.network.AsyncResponse;
import com.example.android.capstone.network.FetchWallpTask;
import com.example.android.capstone.network.NetworkUtilities;

/**
 * Created by DELL on 12/14/2016.
 */

public class WallpService {

    private final NetworkUtilities networkUtilities;
    private Context context;
    public static Pic picResult;
    private AsyncResponse output;
    private int index;
    private String type;

    public WallpService(NetworkUtilities networkUtilities, Context context, AsyncResponse output, int index, String type){
        this.networkUtilities=networkUtilities;
        this.context=context;
        this.output=output;
        this.index=index;
        this.type=type;
    }

    public void loadWallp(){
        if(networkUtilities.isInternetConnectionPresent()) {
            FetchWallpTask fwt = new FetchWallpTask(context, networkUtilities, output,index,type);
//            fwt.progressDialog.show();
            fwt.execute();
        }

    }

    public Pic getpicResult(){

        return picResult;
    }


}
