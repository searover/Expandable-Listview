package com.expandable.listview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.expandable.listview.bean.Node;

import java.util.List;

/**
 * Created by Baiguang on 2015/1/21.
 */
public class SimpleExpandListViewAdapter<T> extends ExpandListViewAdapter<T> {
    /**
     * 适配器构造函数
     *
     * @param listView           ListView控件
     * @param context            程序上下文
     * @param data               数据集合
     * @param defaultExpandLevel 默认展开层级数
     * @throws IllegalAccessException
     */
    public SimpleExpandListViewAdapter(ListView listView, Context context, List<T> data,
            int defaultExpandLevel) throws IllegalAccessException {
        super(listView, context, data, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        return null;
    }
}
