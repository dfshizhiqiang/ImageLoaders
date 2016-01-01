package com.imzhiqiang.imageloaders;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.imzhiqiang.imageloaders.util.LogTime;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    long startTime;
    String uri = "http://ww1.sinaimg.cn/large/7a8aed7bjw1ez9bkpuvipj20dw0kutb9.jpg";
    @Bind(R.id.img_uil)
    ImageView imgUIL;
    @Bind(R.id.img_picasso)
    ImageView imgPicasso;
    @Bind(R.id.img_volley)
    ImageView imgVolley;
    @Bind(R.id.img_fresco)
    ImageView imgFresco;
    @Bind(R.id.img_glide)
    ImageView imgGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ImageLoader.getInstance().displayImage(uri, imgUIL, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                startTime = LogTime.getLogTime();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Log.d(TAG, "onLoadingComplete:" + LogTime.getElapsedMillis(startTime) + "ms");
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

        Picasso.with(this).load(uri).into(imgPicasso, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}
