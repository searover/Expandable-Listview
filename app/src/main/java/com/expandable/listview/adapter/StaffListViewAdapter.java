package com.expandable.listview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.expandable.listview.R;
import com.expandable.listview.bean.Node;

import java.util.List;

/**
 * Created by Baiguang on 2015/1/21.
 * 实现 ExpandListViewAdapter
 */
public class StaffListViewAdapter<Staff> extends ExpandListViewAdapter<Staff> {
    /**
     * 适配器构造函数
     *
     * @param listView           ListView控件
     * @param context            程序上下文
     * @param data               数据集合
     * @param defaultExpandLevel 默认展开层级数
     * @throws IllegalAccessException
     */
    public StaffListViewAdapter(ListView listView, Context context, List<Staff> data,
            int defaultExpandLevel) throws IllegalAccessException {
        super(listView, context, data, defaultExpandLevel);
    }

    /**
     * 创建Listview Item 的view,
     * 要做到为不同层级的item指定不同的view，需要在该方法里判断Node层级
     * @param node
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
//        Log.d("Adapter","node.leven = " + node.getLevel());
        switch (node.getLevel()){
            case 1:
                convertView = mInflater.inflate(R.layout.lilst_item_first,null);
                TextView tv = (TextView) convertView.findViewById(R.id.textView);
                tv.setText(node.getName());
                break;
            case 2:
                convertView = mInflater.inflate(R.layout.list_item_second,null);
                tv = (TextView) convertView.findViewById(R.id.textView);
                tv.setText(node.getName());
                break;
            case 3:
                convertView = mInflater.inflate(R.layout.list_item_third,null);
                tv = (TextView) convertView.findViewById(R.id.textView);
                tv.setText(node.getName());
                break;

        }
        return convertView;
    }
}
