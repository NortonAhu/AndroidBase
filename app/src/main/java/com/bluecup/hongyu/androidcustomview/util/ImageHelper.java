package com.bluecup.hongyu.androidcustomview.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Des: 图片处理类
 * Created by hongyu
 * Date:16/4/17_下午8:43
 */
public class ImageHelper {


    public static Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);

        return bmp;
    }

    /**
     * 底色效果
     * @param bm
     * @return
     */
    public static Bitmap handleImageNegative(Bitmap bm) {
        int widthPixels = bm.getWidth();
        int heightPixels = bm.getHeight();
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);

        int color;
        int r, g, b, a;

        int[] oldPix = new int[widthPixels * heightPixels];
        int[] newPix = new int[widthPixels * heightPixels];        // 保存像素点到数组, oldPix 存储, offset 起点偏移的量, slid 行距多少个算一行使用width, xy表示第一次读取像素点的坐标,
        // 后面两个就是范围
        bm.getPixels(oldPix, 0, widthPixels, 0, 0, widthPixels, heightPixels);

        for (int i = 0; i < oldPix.length; i++) {
            color = oldPix[i];
            // 通过Color来获取像素点的RGBA的值
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            newPix[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPix, 0, widthPixels, 0, 0, widthPixels, heightPixels);
        return bmp;
    }

    public static Bitmap handlePixesEffectOldPhoto(Bitmap bm) {
        int widthPixels = bm.getWidth();
        int heightPixels = bm.getHeight();
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);

        int color;
        int r, g, b, a, r1, g2, b3;

        int[] oldPix = new int[widthPixels * heightPixels];
        int[] newPix = new int[widthPixels * heightPixels];        // 保存像素点到数组, oldPix 存储, offset 起点偏移的量, slid 行距多少个算一行使用width, xy表示第一次读取像素点的坐标,
        // 后面两个就是范围
        bm.getPixels(oldPix, 0, widthPixels, 0, 0, widthPixels, heightPixels);

        for (int i = 0; i < oldPix.length; i++) {
            color = oldPix[i];
            // 通过Color来获取像素点的RGBA的值
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g2 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b3 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r1 > 255) {
                r1 = 255;
            }

            if (g2 > 255) {
                g2 = 255;
            }


            if (b3 > 255) {
                b3 = 255;
            }

            newPix[i] = Color.argb(a, r1, g2, b3);
        }
        bmp.setPixels(newPix, 0, widthPixels, 0, 0, widthPixels, heightPixels);
        return bmp;
    }

}
