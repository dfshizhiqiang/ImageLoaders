package com.imzhiqiang.imageloaders.widget;

import android.content.Context;
import android.util.AttributeSet;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * Created by Zech on 2016/1/17.
 */
public class TouchImageView extends ImageViewTouch {
    public TouchImageView(Context context) {
        this(context, null);
    }

    public TouchImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setDisplayType(DisplayType.FIT_TO_SCREEN);
    }

    @Override
    public float getMinScale() {
        return 1F;
    }

    @Override
    public float getMaxScale() {
        return Math.max(2f, super.getMaxScale());
    }
}
