package cn.sccl.ilife.android.core.ImageLoder;

import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import cn.sccl.ilife.android.core.httpclient.ILifeConstants;

/**
 * Created by yishiyao on 15/3/29.
 */
public class ImageLoaderUtils {

    public static ImageLoader getImageLoader(Context context) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCache(new WeakMemoryCache())
                .diskCache(new UnlimitedDiscCache(setImageDiskCacheDirectory(context)))
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        return imageLoader;
    }

    public static File setImageDiskCacheDirectory(Context context){
        File cache = null;
        if(Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())){

            cache = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + ILifeConstants.SD_IMAGE_CACHE_DIRECTORY);
            if(!cache.exists()){cache.mkdirs();}
        }else{
            cache = context.getCacheDir();
        }
        return cache;
    }
}
