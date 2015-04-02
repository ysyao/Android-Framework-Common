package cn.sccl.ilife.android;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import cn.sccl.ilife.android.core.httpclient.ILifeConstants;

/**
 * Created by Administrator on 2015/3/27.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        configImageCache();
    }
    private void configImageCache(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCache(new WeakMemoryCache())
                .diskCache(new UnlimitedDiscCache(setImageDiskCacheDirectory()))
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);



    }

    private File setImageDiskCacheDirectory(){
        File cache = null;
        if(Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())){

            cache = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + ILifeConstants.SD_IMAGE_CACHE_DIRECTORY);
            if(!cache.exists()){cache.mkdirs();}
        }else{
            cache = getCacheDir();
        }
           return cache;
    }
}
