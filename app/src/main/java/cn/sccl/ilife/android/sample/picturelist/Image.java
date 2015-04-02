package cn.sccl.ilife.android.sample.picturelist;


import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("small")
    private String small;
    @SerializedName("large")
    private String large;
    @SerializedName("medium")
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
