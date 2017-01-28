package com.example.android.capstone;

import android.os.Parcelable;
import android.os.Parcel;
import com.google.gson.annotations.SerializedName;
import java.util.List;


/**
 * Created by DELL on 12/13/2016.
 */

//setPreviewURL("https://cdn.pixabay.com/photo/2016/12/09/11/55/rose-1894760_150.jpg");

public class Pic implements Parcelable {

    @SerializedName("previewHeight")
    private int previewHeight;

    @SerializedName("favorites")
    private int favorites;

    @SerializedName("tags")
    private String tags;

    @SerializedName("previewWidth")
    private int previewWidth;

    @SerializedName("downloads")
    private int downloads;

    @SerializedName("previewURL")
    private String previewURL;

    @SerializedName("webformatURL")
    private String webformatURL;

    @SerializedName("user")
    private String user;

    public Pic(){}

    protected Pic(Parcel in){

        previewHeight= in.readInt();
        favorites=in.readInt();
        tags=in.readString();
        previewWidth=in.readInt();
        downloads=in.readInt();
        previewURL=in.readString();
        webformatURL=in.readString();
        user=in.readString();
    }

    public static final Creator<Pic> CREATOR = new Creator<Pic>() {
        @Override
        public Pic createFromParcel(Parcel in) {
            return new Pic(in);
        }

        @Override
        public Pic[] newArray(int size) {
            return new Pic[size];
        }
    };

    public int getPreviewHeight(){ return previewHeight;}

    public void setPreviewHeight(int previewHeight){ this.previewHeight=previewHeight;}

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites){
        this.favorites=favorites;
    }

    public String getTags(){
        return tags;
    }

    public void setTags(String tags){
        this.tags=tags;}

    public int getPreviewWidth(){return previewWidth;}

    public void setPreviewWidth(int previewWidth){this.previewWidth=previewWidth;}

    public int getDownloads(){
        return downloads;
    }

    public void setDownloads(int downloads){
        this.downloads=downloads;
    }

    public String getPreviewURL(){
        return previewURL;
    }

    public void setPreviewURL(String previewURL){
        this.previewURL=previewURL;
    }

    public String getWebformatURL(){
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL){
        this.webformatURL=webformatURL;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user=user;
    }


    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(previewHeight);
        parcel.writeInt(favorites);
        parcel.writeString(tags);
        parcel.writeInt(previewWidth);
        parcel.writeInt(downloads);
        parcel.writeString(previewURL);
        parcel.writeString(webformatURL);
        parcel.writeString(user);
    }

    public static class PicResult {

        private List<Pic> results ;

        public List<Pic> getResults(){
            return results;
        }

        PicResult(List<Pic> p){results=p; }
    }
}


