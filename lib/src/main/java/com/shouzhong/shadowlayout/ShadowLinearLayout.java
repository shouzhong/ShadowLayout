package com.shouzhong.shadowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2019/04/29.
 */

public class ShadowLinearLayout extends LinearLayout {

    private Paint paint;
    private int shadowWidth;
    private int radius;
    private int startColor;
    private int endColor;

    public ShadowLinearLayout(Context context) {
        this(context, null);
    }

    public ShadowLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        if (attrs == null) return;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ShadowLinearLayout, defStyleAttr, 0);
        if (array == null || array.length() == 0) return;
        shadowWidth = array.getDimensionPixelSize(R.styleable.ShadowLinearLayout_sllWidth, shadowWidth);
        radius = array.getDimensionPixelSize(R.styleable.ShadowLinearLayout_sllRadius, radius);
        startColor = array.getColor(R.styleable.ShadowLinearLayout_sllStartColor, startColor);
        endColor = array.getColor(R.styleable.ShadowLinearLayout_sllEndColor, endColor);
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
