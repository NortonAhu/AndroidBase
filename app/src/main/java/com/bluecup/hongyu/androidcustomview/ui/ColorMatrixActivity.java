package com.bluecup.hongyu.androidcustomview.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bluecup.hongyu.androidcustomview.R;

import java.util.Arrays;

public class ColorMatrixActivity extends AppCompatActivity implements View.OnClickListener {

    private Bitmap bitmap;
    private ImageView initImage;
    private GridLayout gridGroup;
    private Button changeBtn;
    private Button resetBtn;
    public int mEditHeigt;
    public int mEditWidth;

    private EditText[] mEdTxt = new EditText[20];
    private float[] colorMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);

        initView();
        initImage.setImageBitmap(bitmap);
        // 计算gridlayout的宽度和高度在oncreate中还没绘制图形
        gridGroup.post(new Runnable() {

            @Override
            public void run() {
                mEditWidth = gridGroup.getWidth() / 5;
                mEditHeigt = gridGroup.getHeight() / 4;
                initMatrix();
                addEditText();
            }
        });
    }

    private void addEditText() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(ColorMatrixActivity.this);
            mEdTxt[i] = editText;
            editText.setText(String.valueOf((int) colorMatrix[i]));
            gridGroup.addView(editText, mEditWidth, mEditHeigt);
        }
    }

    private void initMatrix() {
        Arrays.fill(colorMatrix, 0);
        for (int i = 0; i < 20; i++) {

            if (i % 6 == 0) {
                colorMatrix[i] = 1;
            }
        }
    }

    private void initView() {
        initImage = (ImageView) findViewById(R.id.image_primaryimage);

        gridGroup = (GridLayout) findViewById(R.id.grid_number);

        changeBtn = (Button) findViewById(R.id.btn_change);
        changeBtn.setOnClickListener(this);
        resetBtn = (Button) findViewById(R.id.btn_reset);
        resetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change:
                btnChange();
                break;
            case R.id.btn_reset:
                btnReset();
                break;
            default:
                break;
        }
    }

    private void btnReset() {
        initMatrix();
        handleImageEffect(bitmap, colorMatrix);
    }

    private void btnChange() {
        for (int i = 0; i < 20; i++) {
            colorMatrix[i] = Float.valueOf(mEdTxt[i].getText().toString());
        }
        handleImageEffect(bitmap, colorMatrix);
    }

    private void handleImageEffect(Bitmap bm, float[] data) {
        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix colorMatrix1 = new ColorMatrix();
        colorMatrix1.set(data);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix1));

        canvas.drawBitmap(bm, 0, 0, paint);
        initImage.setImageBitmap(bitmap);
    }
}
