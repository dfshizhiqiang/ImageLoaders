package com.imzhiqiang.imageloaders;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by Zech on 2015/12/31.
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        setupImageLoader();
        setupFresco();
    }

    private void setupImageLoader(){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(4)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void setupFresco(){
        Fresco.initialize(this);
    }
}
