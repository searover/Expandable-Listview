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

    private ExpandListViewAdapter mAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mListView = (ListView) findViewById(R.id.list_view);
        try {
            mAdapter = new StaffListViewAdapter(mListView,this,getStaffList(),1);
            mAdapter.setOnExpandNodeClickListener(new ExpandListViewAdapter.OnExpandNodeClickListener() {
                @Override
                public void onClick(Node node, List<Node> visibleNodes, View view, int position) {
                    if(node.isLeaft()){
                        for (Node n : visibleNodes){
                            n.setSelected(false);
                        }
                        node.setSelected(true);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            });
            mListView.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            Toast.makeText(this,"实例化适配器失败",Toast.LENGTH_LONG).show();
        }
    }

    private List<Staff> getStaffList(){
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        staffList.add(new Staff(1,"经理-1",0));
        staffList.add(new Staff(2,"经理-2",0));
        staffList.add(new Staff(3,"经理-3",0));

        staffList.add(new Staff(4,"组长-1",1));
        staffList.add(new Staff(5,"组长-2",1));
        staffList.add(new Staff(6,"组长-3",1));
        staffList.add(new Staff(7,"组长-4",2));
        staffList.add(new Staff(8,"组长-5",2));
        staffList.add(new Staff(9,"组长-6",2));
        staffList.add(new Staff(10,"组长-7",3));

        staffList.add(new Staff(11,"李雷",4));
        staffList.add(new Staff(12,"小川",4));
        staffList.add(new Staff(13,"布斯",5));
        staffList.add(new Staff(14,"xiaoming",5));
        staffList.add(new Staff(14,"willian",5));

        staffList.add(new Staff(14,"永生",6));
        staffList.add(new Staff(14,"jerry",6));
        staffList.add(new Staff(14,"haidao",6));

        staffList.add(new Staff(14,"formmer",7));
        staffList.add(new Staff(14,"compo",7));

        staffList.add(new Staff(14,"irils",8));

        staffList.add(new Staff(14,"王小明",9));
        staffList.add(new Staff(14,"王明",9));
        staffList.add(new Staff(14,"李右键",9));
        staffList.add(new Staff(14,"李左键",9));

        return staffList;
    }
}
