
package com.example.android.capstone.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;


public class Pic implements Serializable, Parcelable
{

    private int totalHits;
    private List<Hit> hits = null;
    private int total;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Pic> CREATOR = new Creator<Pic>() {


        public Pic createFromParcel(Parcel in) {
            Pic instance = new Pic();
            instance.totalHits = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.hits, (com.example.android.capstone.model.Hit.class.getClassLoader()));
            instance.total = ((int) in.readValue((int.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Pic[] newArray(int size) {
            return (new Pic[size]);
        }

    }
    ;
    private final static long serialVersionUID = -9220870410965157070L;

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalHits);
        dest.writeList(hits);
        dest.writeValue(total);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
