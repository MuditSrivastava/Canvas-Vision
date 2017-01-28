
package com.example.android.capstone.model;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;

@SimpleSQLTable(table = "canvasDownload", provider = "PicProvider")
public class Hit implements Parcelable
{
    @SimpleSQLColumn("col_previewHeight")
    private int previewHeight;
    @SimpleSQLColumn("col_likes")
    private int likes;
    @SimpleSQLColumn("col_favorites")
    private int favorites;
    @SimpleSQLColumn("col_tags")
    private String tags;
    @SimpleSQLColumn("col_webformatHeight")
    private int webformatHeight;
    @SimpleSQLColumn("col_views")
    private int views;
    @SimpleSQLColumn("col_webformatWidth")
    private int webformatWidth;
    @SimpleSQLColumn("col_previewWidth")
    private int previewWidth;
    @SimpleSQLColumn("col_comments")
    private int comments;
    @SimpleSQLColumn("col_downloads")
    private int downloads;
    @SimpleSQLColumn("col_pageURL")
    private String pageURL;
    @SimpleSQLColumn("col_previewURL")
    private String previewURL;
    @SimpleSQLColumn("col_webformatURL")
    private String webformatURL;
    @SimpleSQLColumn("col_imageWidth")
    private int imageWidth;
    @SimpleSQLColumn("col_userId")
    private int userId;
    @SimpleSQLColumn("col_user")
    private String user;
    @SimpleSQLColumn("col_type")
    private String type;
    @SimpleSQLColumn("col_id")
    private int id;
    @SimpleSQLColumn("col_userImageURL")
    private String userImageURL;
    @SimpleSQLColumn("col_imageHeight")
    private int imageHeight;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Hit> CREATOR = new Creator<Hit>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Hit createFromParcel(Parcel in) {
            Hit instance = new Hit();
            instance.previewHeight = ((int) in.readValue((int.class.getClassLoader())));
            instance.likes = ((int) in.readValue((int.class.getClassLoader())));
            instance.favorites = ((int) in.readValue((int.class.getClassLoader())));
            instance.tags = ((String) in.readValue((String.class.getClassLoader())));
            instance.webformatHeight = ((int) in.readValue((int.class.getClassLoader())));
            instance.views = ((int) in.readValue((int.class.getClassLoader())));
            instance.webformatWidth = ((int) in.readValue((int.class.getClassLoader())));
            instance.previewWidth = ((int) in.readValue((int.class.getClassLoader())));
            instance.comments = ((int) in.readValue((int.class.getClassLoader())));
            instance.downloads = ((int) in.readValue((int.class.getClassLoader())));
            instance.pageURL = ((String) in.readValue((String.class.getClassLoader())));
            instance.previewURL = ((String) in.readValue((String.class.getClassLoader())));
            instance.webformatURL = ((String) in.readValue((String.class.getClassLoader())));
            instance.imageWidth = ((int) in.readValue((int.class.getClassLoader())));
            instance.userId = ((int) in.readValue((int.class.getClassLoader())));
            instance.user = ((String) in.readValue((String.class.getClassLoader())));
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.userImageURL = ((String) in.readValue((String.class.getClassLoader())));
            instance.imageHeight = ((int) in.readValue((int.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Hit[] newArray(int size) {
            return (new Hit[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1966043808408472832L;

    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(int webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(int webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(previewHeight);
        dest.writeValue(likes);
        dest.writeValue(favorites);
        dest.writeValue(tags);
        dest.writeValue(webformatHeight);
        dest.writeValue(views);
        dest.writeValue(webformatWidth);
        dest.writeValue(previewWidth);
        dest.writeValue(comments);
        dest.writeValue(downloads);
        dest.writeValue(pageURL);
        dest.writeValue(previewURL);
        dest.writeValue(webformatURL);
        dest.writeValue(imageWidth);
        dest.writeValue(userId);
        dest.writeValue(user);
        dest.writeValue(type);
        dest.writeValue(id);
        dest.writeValue(userImageURL);
        dest.writeValue(imageHeight);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
