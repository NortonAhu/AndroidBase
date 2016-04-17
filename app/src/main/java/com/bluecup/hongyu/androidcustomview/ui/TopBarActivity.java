package com.bluecup.hongyu.androidcustomview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bluecup.hongyu.androidcustomview.R;
import com.bluecup.hongyu.androidcustomview.view.TopBarView;

public class TopBarActivity extends AppCompatActivity {

    private TopBarView topView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar);
        topView = (TopBarView)findViewById(R.id.custom_top_view);
        topView.setClickListener(new TopBarView.ITopBarListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopBarActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TopBarActivity.this, "前进", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
