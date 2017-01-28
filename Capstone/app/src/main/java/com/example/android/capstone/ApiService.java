package com.example.android.capstone;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by DELL on 12/13/2016.
 */

public interface ApiService {

    @GET("?key=3636029-93137fb4794230a77220a0a42&order=popular")
    Call<Pic> getLatestPic();
}
