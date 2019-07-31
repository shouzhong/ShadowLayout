package com.shouzhong.shadowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2019/04/29.
 */

public class ShadowLayout extends FrameLayout {

    private Paint paint;
    private int shadowWidth;
    private int radius;
    private int startColor;
    private int endColor;

    public ShadowLayout(Context context) {
        this(context, null);
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        if (attrs == null) return;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ShadowLayout, defStyleAttr, 0);
        if (array == null || array.length() == 0) return;
        shadowWidth = array.getDimensionPixelSize(R.styleable.ShadowLayout_slWidth, shadowWidth);
        radius = array.getDimensionPixelSize(R.styleable.ShadowLayout_slRadius, radius);
        startColor = array.getColor(R.styleable.ShadowLayout_slStartColor, startColor);
        endColor = array.getColor(R.styleable.ShadowLayout_slEndColor, endColor);
        array.recycle();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (shadowWidth <= 0) return;
        int width = getWidth();
        int height = getHeight();
        ShadowUtils.setShadow(canvas, paint, width, height, shadowWidth, startColor, endColor, radius);
    }

    public void setShadowWidth(int shadowWidth) {
        this.shadowWidth = shadowWidth;
        postInvalidate();
    }

    public void setColor(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        postInvalidate();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        postInvalidate();
    }
}
