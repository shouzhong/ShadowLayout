package com.shouzhong.shadowlayout;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;

/**
 * Created by Administrator on 2019/04/29.
 */

public class ShadowUtils {

    public static void setShadow(Canvas canvas, Paint paint, int width, int height, int shadowWidth, int startColor, int endColor, int radius) {
        radius = Math.min(width, height) - 2 * shadowWidth - 2 * radius < 0 ? (Math.min(width, height) - 2 * shadowWidth) / 2 : radius;
        float endStop = (endColor & 0xff000000) == 0 ? 1f : (shadowWidth + radius - 1) * 1.0f / (shadowWidth + radius);
        // 上下左右公用参数：颜色和位置
        int[] colors = {startColor, endColor, 0};
        float[] stops = {0, endStop, 1f};
        // 上下
        if (width - 2 * shadowWidth - 2 * radius > 0) {
            Rect rect = new Rect(0, 0, width - 2 * shadowWidth - 2 * radius, shadowWidth);

            canvas.save();
            canvas.translate(shadowWidth + radius, 0);
            paint.setShader(new LinearGradient(0, shadowWidth, 0, 0, colors, stops, Shader.TileMode.CLAMP));
            canvas.drawRect(rect, paint);
            canvas.restore();

            canvas.save();
            canvas.translate(shadowWidth + radius, height - shadowWidth);
            paint.setShader(new LinearGradient(0, 0, 0, shadowWidth, colors, stops, Shader.TileMode.CLAMP));
            canvas.drawRect(rect, paint);
            canvas.restore();
        }
        // 左右
        if (height - 2 * shadowWidth - 2 * radius > 0) {
            Rect rect = new Rect(0, 0 , shadowWidth, height - 2 * shadowWidth - 2 * radius);

            canvas.save();
            canvas.translate(0, shadowWidth + radius);
            paint.setShader(new LinearGradient(shadowWidth, 0, 0, 0, colors, stops, Shader.TileMode.CLAMP));
            canvas.drawRect(rect, paint);
            canvas.restore();

            canvas.save();
            canvas.translate(width - shadowWidth, shadowWidth + radius);
            paint.setShader(new LinearGradient(0, 0, shadowWidth, 0, colors, stops, Shader.TileMode.CLAMP));
            canvas.drawRect(rect, paint);
            canvas.restore();
        }
        // 左上，右上，左下，右下公用参数：矩形框，颜色，位置
        Rect rect = new Rect(0, 0, shadowWidth + radius, shadowWidth + radius);
        float rate = radius * 1.0f / (shadowWidth + radius);
        colors = new int[] {0, 0, startColor, endColor, 0};
        stops = new float[] {0, rate, rate, endStop, 1f};
        // 左上
        canvas.save();
        canvas.translate(0, 0);
        paint.setShader(new RadialGradient(shadowWidth + radius, shadowWidth + radius, shadowWidth + radius, colors, stops, Shader.TileMode.CLAMP));
        canvas.drawRect(rect, paint);
        canvas.restore();
        // 右上
        canvas.save();
        canvas.translate(width - shadowWidth - radius, 0);
        paint.setShader(new RadialGradient(0, shadowWidth + radius, shadowWidth + radius, colors, stops, Shader.TileMode.CLAMP));
        canvas.drawRect(rect, paint);
        canvas.restore();
        // 左下
        canvas.save();
        canvas.translate(0, height - shadowWidth - radius);
        paint.setShader(new RadialGradient(shadowWidth + radius, 0, shadowWidth + radius, colors, stops, Shader.TileMode.CLAMP));
        canvas.drawRect(rect, paint);
        canvas.restore();
        // 右下
        canvas.save();
        canvas.translate(width - shadowWidth - radius, height - shadowWidth - radius);
        paint.setShader(new RadialGradient(0, 0, shadowWidth + radius, colors, stops, Shader.TileMode.CLAMP));
        canvas.drawRect(rect, paint);
        canvas.restore();
    }

}
