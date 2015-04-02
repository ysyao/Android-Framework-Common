package cn.sccl.ilife.android.sample.photoview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class PhotoUtils {
    public static Bitmap transFile2Bitmap(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(file.getPath(), options);
    }
}
