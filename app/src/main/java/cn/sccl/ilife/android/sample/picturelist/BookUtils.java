package cn.sccl.ilife.android.sample.picturelist;

public class BookUtils {
    public static float countingBookRating(DouBanBook book) {
        float max = Float.valueOf(book.getRating().getMax());
        float average = Float.valueOf(book.getRating().getAverage());
        return average / max * 5;
    }
}
