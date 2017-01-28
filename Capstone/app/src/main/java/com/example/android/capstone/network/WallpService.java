package com.example.android.capstone.network;

import android.content.Context;

public class WallpService {

    private final NetworkUtilities networkUtilities;
    private Context context;
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
                case "latest": FetchNavTask fwt = new FetchNavTask(context,output,index);
                    fwt.execute();break;

                case "popular":  FetchWallpTask fnt = new FetchWallpTask(context, output,index);
                    fnt.execute();break;

                case "editors_choice": FetchEditTask fet = new FetchEditTask(context,output,index);
                    fet.execute();break;

                default: FetchCatTask fct = new FetchCatTask(context, output,index,type);
                    fct.execute();break;

            }
        }
    }
}
