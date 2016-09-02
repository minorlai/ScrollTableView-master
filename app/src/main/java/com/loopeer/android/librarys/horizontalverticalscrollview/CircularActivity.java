package com.loopeer.android.librarys.horizontalverticalscrollview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loopeer.android.librarys.horizontalverticalscrollview.ui.PriceOrderActivity;

/**
 * Created by laiyingtang on 2016/8/24.
 */
public class CircularActivity extends Activity {
    private Button btn_style;
    private Button btn_number;
    private Button btn_threed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circular_layout);
        btn_style= (Button) findViewById(R.id.btn_style);
        btn_number= (Button) findViewById(R.id.btn_number);
        btn_threed= (Button) findViewById(R.id.btn_threed);
        btn_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CircularActivity.this,XRecyclerViewActivity.class));
            }
        });

        btn_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CircularActivity.this,GridViewActivity.class));
            }
        });
        btn_threed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CircularActivity.this, PriceOrderActivity.class));
            }
        });
    }
}
