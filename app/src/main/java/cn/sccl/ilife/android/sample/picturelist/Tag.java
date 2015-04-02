package cn.sccl.ilife.android.sample.picturelist;

import com.google.gson.annotations.SerializedName;

public class Tag {
    @SerializedName("count")
    private int count;
    @SerializedName("name")
    private String name;
    @SerializedName("title")
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
