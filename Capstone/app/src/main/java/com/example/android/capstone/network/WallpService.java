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


            switch (type){
                case "latest": FetchWallpTask fwt = new FetchWallpTask(context, networkUtilities, output,index);
//            fwt.progressDialog.show();
                    fwt.execute();break;

                case "popular":  FetchNavTask fnt = new FetchNavTask(context, networkUtilities, output,index);
//            fwt.progressDialog.show();
                    fnt.execute();break;

                case "editor": FetchEditTask fet = new FetchEditTask(context,networkUtilities,output,index);
                    fet.execute();break;

                default: FetchCatTask fct = new FetchCatTask(context, networkUtilities, output,index,type);
//            fwt.progressDialog.show();
                    fct.execute();break;

            }


            }

        }




    public Pic getpicResult(){

        return picResult;
    }


}
