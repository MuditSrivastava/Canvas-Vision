package com.example.android.capstone.ui.util;

import android.widget.ImageView;



import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import static android.R.attr.width;


public class DynamicHeightImageView extends ImageView {
    private float whRatio = 0;

    public DynamicHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicHeightImageView(Context context) {
        super(context);
    }

    public void setRatio(float ratio) {
        whRatio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (whRatio != 0) {
            int width = getMeasuredWidth();
            int height = (int)(whRatio * width);
            setMeasuredDimension(width, height);
        }
    }

}

