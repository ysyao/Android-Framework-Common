package cn.sccl.ilife.android.sample.picturelist;

import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("max")
    private int max;
    @SerializedName("numRaters")
    private int numRaters;
    @SerializedName("average")
    private String average;
    @SerializedName("min")
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNumRaters() {
        return numRaters;
    }

    public void setNumRaters(int numRaters) {
        this.numRaters = numRaters;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}