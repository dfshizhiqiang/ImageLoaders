package com.imzhiqiang.imageloaders;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Zech on 2015/12/31.
 */
public class MyApp extends Application {
    private static MyApp sInstance;
    private RequestQueue mQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        setupVolley();
        setupImageLoader();
        setupFresco();

    }

    public static MyApp getInstance() {
        return sInstance;
    }

    public RequestQueue getmQueue() {
        return mQueue;
    }

    private void setupVolley() {
        mQueue = Volley.newRequestQueue(this);
    }

    private void setupImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(4)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .diskCacheSize(50 * 1024 * 1024)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void setupFresco() {
        Fresco.initialize(this);
    }
}
