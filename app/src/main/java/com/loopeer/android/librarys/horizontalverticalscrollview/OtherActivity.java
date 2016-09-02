package com.loopeer.android.librarys.horizontalverticalscrollview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loopeer.android.librarys.scrolltable.CustomTableView;
import com.loopeer.android.librarys.scrolltable.ScrollTableView;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/23.
 */
public class OtherActivity extends Activity {

    private ScrollTableView scrollTableView;
    private CustomTableView content_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_table);
        content_view = (CustomTableView) findViewById(R.id.content_view);
        ArrayList<ArrayList<String>> rowColum = createLeftTitle();
        ArrayList<ArrayList<String>> content = createContent(rowColum.size(), rowColum.size());
        content_view.setDatas(content);

        Button btn_circular= (Button) findViewById(R.id.btn_circular);
        btn_circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherActivity.this,CircularActivity.class));
            }
        });
    }

    private ArrayList<ArrayList<String>> createContent(int row, int column) {
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            ArrayList<String> strings = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                strings.add("$" + i + "" + j);
            }
            results.add(strings);
        }
        return results;
    }

    private ArrayList<ArrayList<String>> createLeftTitle() {
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 900; i < 920; i++) {
            list.add(i + "");
            results.add(list);
        }
        return results;
    }

}
