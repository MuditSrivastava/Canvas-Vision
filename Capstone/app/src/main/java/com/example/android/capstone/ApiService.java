package com.example.android.capstone;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.R.attr.order;

/**
 * Created by DELL on 12/13/2016.
 */

public interface ApiService {

    @GET("?key="+BuildConfig.API_KEY+"&order=popular")
    Call<Pic> getLatestPic(@Query("page") int index);
}
