package com.loopeer.android.librarys.horizontalverticalscrollview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loopeer.android.librarys.scrolltable.ScrollTableView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String[] topTitles = new String[] {"场一", "场二", "场三", "场四", "场五", "场六", "场七", "场八", "场九", "场十", "场十一","场十二","场十三"};

    private ScrollTableView scrollTableView;
    private Button btn_other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollTableView = (ScrollTableView) findViewById(R.id.scroll_table_view);
        btn_other= (Button) findViewById(R.id.other);
        btn_other.setOnClickListener(this);
        ArrayList<String> leftTitle = createLeftTitle();
        ArrayList<String> topTitles = createTopTitles();
        scrollTableView.setDatas(createTopTitles(), createLeftTitle(), createContent(leftTitle.size(), topTitles.size()));
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

    private ArrayList<String> createTopTitles() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : topTitles) {
            results.add(string);
        }
        return results;
    }

    private ArrayList<String> createLeftTitle() {
        ArrayList<String> results = new ArrayList<>();
        for (int i = 900; i < 920; i++) {
            results.add(i+"");
        }
        return results;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,OtherActivity.class));
    }
}
