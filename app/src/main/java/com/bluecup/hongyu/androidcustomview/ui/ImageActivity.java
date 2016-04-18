package com.bluecup.hongyu.androidcustomview.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.bluecup.hongyu.androidcustomview.R;
import com.bluecup.hongyu.androidcustomview.util.ImageHelper;

/**
 * 图片处理
 */
public class ImageActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ImageView img_handle;
    private SeekBar seekbar_hue;
    private SeekBar seekbar_saturation;
    private SeekBar seekbar_lum;

    private final static int MAX_VALUE = 255;
    private final static int MID_VALUE = 127;

    private float hue, saturation, lum = 1.0F;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        setTitle("图像处理");
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);
        initView();
        img_handle.setImageBitmap(bitmap);
    }

    private void initView() {
        img_handle = (ImageView) findViewById(R.id.img_handle);
        seekbar_hue = (SeekBar) findViewById(R.id.seekbar_hue);
        seekbar_saturation = (SeekBar) findViewById(R.id.seekbar_saturation);
        seekbar_lum = (SeekBar) findViewById(R.id.seekbar_lum);

        seekbar_hue.setMax(MAX_VALUE);
        seekbar_saturation.setMax(MAX_VALUE);
        seekbar_lum.setMax(MAX_VALUE);

        seekbar_hue.setProgress(MID_VALUE);
        seekbar_lum.setProgress(MID_VALUE);
        seekbar_saturation.setProgress(MID_VALUE);

        seekbar_hue.setOnSeekBarChangeListener(this);
        seekbar_saturation.setOnSeekBarChangeListener(this);
        seekbar_lum.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbar_hue:
                hue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seekbar_saturation:
                saturation = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seekbar_lum:
                lum = progress * 1.0F / MID_VALUE;
                break;
        }
        Log.e("test", "hue = " + hue + ";saturatio = " + saturation + ";lum=" + lum);
        img_handle.setImageBitmap(ImageHelper.handleImageEffect(bitmap, hue, saturation, lum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
