package qianfeng.a7_5listviewfoot_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list;
    private List<String> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.lv);

        initData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);


        final ListView foot_listView = new ListView(this);
        // 脚布局的高度是一定要是固定的高度，多少多少dp，否则显示不出来，这是在xml文件中就是这样规定的
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list2);
        foot_listView.setLayoutParams(layoutParams);
        foot_listView.setAdapter(adapter2);
        lv.addFooterView(foot_listView);

        foot_listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN :
                        // 如果没有禁止父容器的拦截事件，那么李四的那个list2就滑不下去了！
                    foot_listView.getParent().requestDisallowInterceptTouchEvent(true); // 试试注释这行代码
                    break;

                    case MotionEvent.ACTION_UP:
                    foot_listView.getParent().requestDisallowInterceptTouchEvent(false);
                        break;

                }
                return false;
            }
        });


        // 添加头布局一定要在父ListView的setAdapter之前就加上
        lv.setAdapter(adapter);

    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new String("张三:" + i ));
        }
        list2 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list2.add(new String(":李四" + i ));
        }

    }

}
