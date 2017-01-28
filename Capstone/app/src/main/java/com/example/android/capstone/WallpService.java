package com.example.android.capstone;

import android.content.Context;

/**
 * Created by DELL on 12/14/2016.
 */

public class WallpService {

    private final NetworkUtilities networkUtilities;
    private Context context;
    public static Pic picResult;
    private AsyncResponse output;
    private int index;

    public WallpService( NetworkUtilities networkUtilities,Context context,AsyncResponse output,int index){
        this.networkUtilities=networkUtilities;
        this.context=context;
        this.output=output;
        this.index=index;
    }

    public void loadWallp(){
        if(networkUtilities.isInternetConnectionPresent()) {
            FetchWallpTask fwt = new FetchWallpTask(context, networkUtilities, output,index);
//            fwt.progressDialog.show();
            fwt.execute();
        }

    }

    public Pic getpicResult(){

        return picResult;
    }


}
