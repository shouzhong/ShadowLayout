package com.shouzhong.shadowlayout;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by Administrator on 2019/04/29.
 */

public class ShadowUtils {
    public static void setShadow(Canvas canvas, Paint paint, int width, int height, int shadowWidth, int startColor, int endColor, int ltRadius, int lbRadius, int rtRadius, int rbRadius) {
        if (width < 2 * shadowWidth || height < 2 * shadowWidth) return;
        if (width < height) {
            if (ltRadius + rtRadius + 2 * shadowWidth > width) {
                ltRadius = ltRadius * (width - 2 * shadowWidth) / (ltRadius + rtRadius);
                rtRadius = width - 2 * shadowWidth - ltRadius;
            }
            if (lbRadius + rbRadius + 2 * shadowWidth > width) {
                lbRadius = lbRadius * (width - 2 * shadowWidth) / (lbRadius + rbRadius);
                rbRadius = width - 2 * shadowWidth - lbRadius;
            }
        } else {
            if (ltRadius + lbRadius + 2 * shadowWidth > height) {
                ltRadius = ltRadius * (height - 2 * shadowWidth) / (ltRadius + lbRadius);
                lbRadius = height - 2 * shadowWidth - ltRadius;
            }
            if (rtRadius + rbRadius + 2 * shadowWidth > height) {
                rtRadius = rtRadius * (height - 2 * shadowWidth) / (rtRadius + rbRadius);
                rbRadius = height - 2 * shadowWidth - rtRadius;
            }
        }
        float endStop = (endColor & 0xff000000) == 0 ? 1f : (shadowWidth - 1) * 1.0f / shadowWidth;
        int[] colors = {startColor, endColor, 0};
        // 上下左右公用参数：颜色和位置
        float[] positions = {0, endStop, 1f};
        Rect rect = new Rect();
        // 上
        if (width - 2 * shadowWidth - ltRadius - rtRadius > 0) {
            rect.left = 0;
            rect.top = 0;
            rect.right = width - 2 * shadowWidth - ltRadius - rtRadius;
            rect.bottom = shadowWidth;
            canvas.save();
            canvas.translate(shadowWidth + ltRadius, 0);
            paint.setShader(new LinearGradient(0, shadowWidth, 0, 0, colors, positions, Shader.TileMode.CLAMP));
            canvas.drawRect(rect, paint);
            canvas.restore();
        }
        // 下
        if (width - 2 * shadowWidth - lbRadius - rbRadius > 0) {
            rect.left = 0;
            rect.top = 0;
            rect.right = width - 2 * shadowWidth - lbRadius - rbRadius;
            rect.bottom = shadowWidth;
            canvas.save();
            canvas.translate(shadowWidth + lbRadius, height - shadowWidth);
            paint.setShader(new LinearGradient(0, 0, 0, shadowWidth, colors, positions, Shader.TileMode.CLAMP));
            canvas.drawRect(rect, paint);
            canvas.restore();
        }
        // 左
        if (height - 2 * shadowWidth - ltRadius - lbRadius > 0) {
            rect.left = 0;
            rect.top = 0;
            rect.right = shadowWidth;
            rect.bottom = height - 2 * shadowWidth - ltRadius - lbRadius;
            canvas.save();
            canvas.translate(0, shadowWidth + ltRadius);
            paint.setShader(new LinearGradient(shadowWidth, 0, 0, 0, colors, positions, Shader.TileMode.CLAMP));
            canvas.drawRect(rect, paint);
            canvas.restore();
        }
        // 右
        if (height - 2 * shadowWidth - rtRadius - rbRadius > 0) {
            rect.left = 0;
            rect.top = 0;
            rect.right = shadowWidth;
            rect.bottom = height - 2 * shadowWidth - rtRadius - rbRadius;
            canvas.save();
            canvas.translate(width - shadowWidth, shadowWidth + rtRadius);
            paint.setShader(new LinearGradient(0, 0, shadowWidth, 0, colors, positions, Shader.TileMode.CLAMP));
            canvas.drawRect(rect, paint);
            canvas.restore();
        }
        // 左上
        rect.left = 0;
        rect.top = 0;
        rect.right = shadowWidth + ltRadius;
        rect.bottom = shadowWidth + ltRadius;
        float rate = ltRadius * 1.0f / (shadowWidth + ltRadius);
        endStop = (endColor & 0xff000000) == 0 ? 1f : (shadowWidth + ltRadius - 1) * 1.0f / (shadowWidth + ltRadius);
        colors = new int[] {0, 0, startColor, endColor, 0};
        positions = new float[] {0, rate, rate, endStop, 1f};
        canvas.save();
        canvas.translate(0, 0);
        paint.setShader(new RadialGradient(shadowWidth + ltRadius, shadowWidth + ltRadius, shadowWidth + ltRadius, colors, positions, Shader.TileMode.CLAMP));
        canvas.drawRect(rect, paint);
        canvas.restore();
        // 右上
        rect.left = 0;
        rect.top = 0;
        rect.right = shadowWidth + rtRadius;
        rect.bottom = shadowWidth + rtRadius;
        rate = rtRadius * 1.0f / (shadowWidth + rtRadius);
        endStop = (endColor & 0xff000000) == 0 ? 1f : (shadowWidth + rtRadius - 1) * 1.0f / (shadowWidth + rtRadius);
        colors = new int[] {0, 0, startColor, endColor, 0};
        positions = new float[] {0, rate, rate, endStop, 1f};
        canvas.save();
        canvas.translate(width - shadowWidth - rtRadius, 0);
        paint.setShader(new RadialGradient(0, shadowWidth + rtRadius, shadowWidth + rtRadius, colors, positions, Shader.TileMode.CLAMP));
        canvas.drawRect(rect, paint);
        canvas.restore();
        // 左下
        rect.left = 0;
        rect.top = 0;
        rect.right = shadowWidth + lbRadius;
        rect.bottom = shadowWidth + lbRadius;
        rate = lbRadius * 1.0f / (shadowWidth + lbRadius);
        endStop = (endColor & 0xff000000) == 0 ? 1f : (shadowWidth + lbRadius - 1) * 1.0f / (shadowWidth + lbRadius);
        colors = new int[] {0, 0, startColor, endColor, 0};
        positions = new float[] {0, rate, rate, endStop, 1f};
        canvas.save();
        canvas.translate(0, height - shadowWidth - lbRadius);
        paint.setShader(new RadialGradient(shadowWidth + lbRadius, 0, shadowWidth + lbRadius, colors, positions, Shader.TileMode.CLAMP));
        canvas.drawRect(rect, paint);
        canvas.restore();
        // 右下
        rect.left = 0;
        rect.top = 0;
        rect.right = shadowWidth + rbRadius;
        rect.bottom = shadowWidth + rbRadius;
        rate = rbRadius * 1.0f / (shadowWidth + rbRadius);
        endStop = (endColor & 0xff000000) == 0 ? 1f : (shadowWidth + rbRadius - 1) * 1.0f / (shadowWidth + rbRadius);
        colors = new int[] {0, 0, startColor, endColor, 0};
        positions = new float[] {0, rate, rate, endStop, 1f};
        canvas.save();
        canvas.translate(width - shadowWidth - rbRadius, height - shadowWidth - rbRadius);
        paint.setShader(new RadialGradient(0, 0, shadowWidth + rbRadius, colors, positions, Shader.TileMode.CLAMP));
        canvas.drawRect(rect, paint);
        canvas.restore();
    }
}
