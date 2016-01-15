package com.imzhiqiang.imageloaders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.imzhiqiang.imageloaders.entity.MeizhiEntity;
import com.imzhiqiang.imageloaders.util.BitmapCache;
import com.imzhiqiang.imageloaders.util.LogTime;
import com.imzhiqiang.imageloaders.util.PreferencesUtil;
import com.imzhiqiang.imageloaders.widget.VolleyImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    String uri = "http://ww1.sinaimg.cn/large/7a8aed7bjw1ez9bkpuvipj20dw0kutb9.jpg";

    PreferencesUtil p;

    @Bind(R.id.img_uil)
    ImageView imgUIL;
    @Bind(R.id.img_picasso)
    ImageView imgPicasso;
    @Bind(R.id.img_volley)
    VolleyImageView imgVolley;
    @Bind(R.id.img_fresco)
    SimpleDraweeView imgFresco;
    @Bind(R.id.img_glide)
    ImageView imgGlide;

    long startTimeUIL;
    long startTimePicasso;
    long startTimeVolley;
    long startTimeFresco;
    long startTimeGlide;

    @Bind(R.id.text_uil)
    TextView textUil;
    @Bind(R.id.text_picasso)
    TextView textPicasso;
    @Bind(R.id.text_volley)
    TextView textVolley;
    @Bind(R.id.text_fresco)
    TextView textFresco;
    @Bind(R.id.text_glide)
    TextView textGlide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        p = new PreferencesUtil(this);

        int page = p.getInt("page");
        String customUrl = p.getString("custom_url");
        if (TextUtils.isEmpty(customUrl)) {
            new APIClient().getService().getRxMeizhi(5, page)
                    .map(MeizhiEntity::getResults)
                    .flatMap(Observable::from)
                    .first()
                    .map(MeizhiEntity.ResultsEntity::getUrl)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(String s) {
                            uri = s;
                            Log.d(TAG, "onNext: url----->" + uri);
                            loadAll();
                        }
                    });
        } else {
            Observable.just(customUrl)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(String s) {
                            uri = s;
                            Log.d(TAG, "onNext: url----->" + uri);
                            loadAll();
                        }
                    });
        }

        imgUIL.setOnClickListener(this);
        imgVolley.setOnClickListener(this);
        imgPicasso.setOnClickListener(this);
        imgFresco.setOnClickListener(this);
        imgGlide.setOnClickListener(this);

    }

    private void loadAll() {
        startTimeUIL = LogTime.getLogTime();
        loadByUIL(uri);
        startTimePicasso = LogTime.getLogTime();
        loadByPicasso(uri);
        startTimeVolley = LogTime.getLogTime();
        loadByVolley(uri);
        startTimeFresco = LogTime.getLogTime();
        loadByFresco(uri);
        startTimeGlide = LogTime.getLogTime();
        loadByGlide(uri);
    }

    private void loadByGlide(String url) {
        Glide.with(this).load(url).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.d(TAG, "Glide----->onResourceReady: " + LogTime.getElapsedMillis(startTimeGlide) + "ms");
                textGlide.setText("Glide加载时间为" + LogTime.getElapsedMillis(startTimeGlide) + "ms");
                return false;
            }
        }).into(imgGlide);
    }

    private void loadByFresco(String url) {
        Uri uri = Uri.parse(url);
        ControllerListener listener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                Log.d(TAG, "Fresco----->onFinalImageSet: " + LogTime.getElapsedMillis(startTimeFresco) + "ms");
                textFresco.setText("Fresco加载时间为" + LogTime.getElapsedMillis(startTimeFresco) + "ms");
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(listener)
                .setUri(uri)
                .build();
        imgFresco.setController(controller);
    }

    private void loadByVolley(String url) {
        RequestQueue queue = MyApp.getInstance().getmQueue();
        com.android.volley.toolbox.ImageLoader loader = new com.android.volley.toolbox.ImageLoader(queue, new BitmapCache());
        imgVolley.setImageUrl(url, loader);
        imgVolley.setResponseObserver(new VolleyImageView.ResponseObserver() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess() {
                Log.d(TAG, "Volley----->onSuccess: " + LogTime.getElapsedMillis(startTimeVolley) + "ms");
                textVolley.setText("Volley加载时间为" + LogTime.getElapsedMillis(startTimeVolley) + "ms");
            }
        });
    }

    private void loadByPicasso(String url) {
        Picasso.with(this).load(url).into(imgPicasso, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Picasso----->onSuccess: " + LogTime.getElapsedMillis(startTimePicasso) + "ms");
                textPicasso.setText("Picasso加载时间为" + LogTime.getElapsedMillis(startTimePicasso) + "ms");
            }

            @Override
            public void onError() {

            }
        });
    }

    private void loadByUIL(String url) {
        ImageLoader.getInstance().displayImage(url, imgUIL, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Log.d(TAG, "UIL----->onLoadingComplete:" + LogTime.getElapsedMillis(startTimeUIL) + "ms");
                textUil.setText("UIL加载时间为" + LogTime.getElapsedMillis(startTimeUIL) + "ms");
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_select_img:
                showMeizhiDialog();
                return true;
            case R.id.action_custom_url:
                showCustomUrlDialog();
                return true;
            case R.id.action_about:
                startAboutActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMeizhiDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选个你喜欢的妹子把~")
                .setItems(R.array.array_meizhi, (dialog, which) -> {
                    saveWhichMeizhi(which + 1);
                })
                .show();
    }

    private void saveWhichMeizhi(int which) {
        p.saveInt("page", which);
        Toast.makeText(MainActivity.this, "您已选择" + which + "号妹子,下次她将为您服务", Toast.LENGTH_SHORT).show();
    }

    private void showCustomUrlDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom_url, null);
        EditText input = (EditText) dialogView.findViewById(R.id.et_custom_url);
        new AlertDialog.Builder(this)
                .setTitle("输入你喜欢的妹子地址~")
                .setView(dialogView)
                .setCancelable(true)
                .setPositiveButton("确定", (dialog, which) -> {
                    String value = input.getText().toString().trim();
                    p.saveString("custom_url", value);
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void startAboutActivity() {
        startActivity(new Intent().setClass(this, AboutActivity.class));
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent().setClass(this,PhotoActivity.class).putExtra("url",uri));
    }
}
