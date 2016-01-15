package com.imzhiqiang.imageloaders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class PhotoActivity extends AppCompatActivity {

    @Bind(R.id.photo)
    ImageViewTouch photo;

    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        url = getIntent().getStringExtra("url");
        Glide.with(this)
                .load(url)
                .into(photo);
    }
}
