package com.bluecup.hongyu.androidcustomview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bluecup.hongyu.androidcustomview.ui.ColorMatrixActivity;
import com.bluecup.hongyu.androidcustomview.ui.ImageActivity;
import com.bluecup.hongyu.androidcustomview.ui.TopBarActivity;
import com.bluecup.hongyu.androidcustomview.view.TopBarView;

public class MainActivity extends AppCompatActivity {

    private TopBarView topView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"topBar","图像处理","colorMatrix"}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, TopBarActivity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, ImageActivity.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, ColorMatrixActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
