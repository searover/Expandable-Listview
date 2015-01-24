package com.expandable.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.expandable.listview.adapter.ExpandListViewAdapter;
import com.expandable.listview.adapter.StaffListViewAdapter;
import com.expandable.listview.bean.Node;
import com.expandable.listview.bean.Staff;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mListView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mListView = (ListView) findViewById(R.id.list_view);
        try {
            StaffListViewAdapter adapter = new StaffListViewAdapter(mListView,this,getStaffList(),1);
            adapter.setOnExpandNodeClickListener(new ExpandListViewAdapter.OnExpandNodeClickListener() {
                @Override
                public void onClick(Node node, List<Node> visibleNodes, View view, int position) {
                    if(node.isLeaft()){
                        // ...
                    }
                }
            });
            mListView.setAdapter(adapter);
        } catch (IllegalAccessException e) {
            Toast.makeText(this,"实例化适配器失败",Toast.LENGTH_LONG).show();
        }
    }

    private List<Staff> getStaffList(){
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        staffList.add(new Staff(1,"Jack",0));
        staffList.add(new Staff(2,"Mark",0));
        staffList.add(new Staff(3,"小明",0));

        staffList.add(new Staff(4,"张三",1));
        staffList.add(new Staff(5,"李四",1));
        staffList.add(new Staff(6,"王小强",1));
        staffList.add(new Staff(7,"李自明",2));
        staffList.add(new Staff(8,"赵小北",2));
        staffList.add(new Staff(9,"无名。。",2));
        staffList.add(new Staff(10,"Herry",3));

        staffList.add(new Staff(11,"李雷",4));
        staffList.add(new Staff(12,"小川",4));
        staffList.add(new Staff(13,"布斯",10));

        return staffList;
    }
}
