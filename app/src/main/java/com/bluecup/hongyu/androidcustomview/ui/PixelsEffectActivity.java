package com.bluecup.hongyu.androidcustomview.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bluecup.hongyu.androidcustomview.R;
import com.bluecup.hongyu.androidcustomview.util.ImageHelper;

/**
 * 像素位图图片处理
 */
public class PixelsEffectActivity extends AppCompatActivity {

    private ImageView originImg;
    private ImageView diseImg;
    private ImageView oldImg;
    private ImageView fudiaoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);
        setContentView(R.layout.activity_pixels_effect);
        initView();
        originImg.setImageBitmap(bitmap);
        diseImg.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        oldImg.setImageBitmap(ImageHelper.handlePixesEffectOldPhoto(bitmap));
        fudiaoImg.setImageBitmap(ImageHelper.handlePixesEffectRelief(bitmap));
    }

    private void initView() {
        originImg = (ImageView) findViewById(R.id.image_origin);
        diseImg = (ImageView) findViewById(R.id.image_dise);
        oldImg = (ImageView) findViewById(R.id.image_old);
        fudiaoImg = (ImageView) findViewById(R.id.image_fudiao);
    }
}
