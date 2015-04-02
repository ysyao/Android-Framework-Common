package cn.sccl.ilife.android.sample.picturelist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DouBanBookList {
    @SerializedName("count")
    private int count;
    @SerializedName("start")
    private int start;
    @SerializedName("total")
    private int total;
    @SerializedName("books")
    private List<DouBanBook> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DouBanBook> getBooks() {
        return books;
    }

    public void setBooks(List<DouBanBook> books) {
        this.books = books;
    }
}
