package com.expandable.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.expandable.listview.bean.Node;

import java.util.List;

/**
 * Created by Baiguang on 2015/1/19.
 * 可伸缩ListView适配器
 *
 */
public class ExpandListViewAdapter<T> extends BaseAdapter {

    protected Context mContext;

    protected List<Node> mVisibleNodes;

    protected LayoutInflater mInflater;

    protected List<Node> mAllNodes;

    public interface OnExpandNodeClickListener {
        void onClick(Node node, List<Node> visibleNodes, View view, int position);
    }

    private OnExpandNodeClickListener onExpandNodeClickListener;

    public void setOnExpandNodeClickListener(OnExpandNodeClickListener listener){
        this.onExpandNodeClickListener = listener;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
