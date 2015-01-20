package com.expandable.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.expandable.listview.Common.ExpandHelper;
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

    public ExpandListViewAdapter(ListView listView, Context context, List<T> data, int defaultExpandLevel)
            throws IllegalAccessException {
        mContext = context;
        mAllNodes = ExpandHelper.getSortedNodes(data,defaultExpandLevel);
        mVisibleNodes = ExpandHelper.filterVisibleNodes(mAllNodes);
        mInflater = LayoutInflater.from(mContext);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggleExpand(position);
                if(onExpandNodeClickListener != null){
                    onExpandNodeClickListener.onClick(mVisibleNodes.get(position),mVisibleNodes,view,position);
                }
            }
        });
    }

    public void toggleExpand(int position){
        Node node = mVisibleNodes.get(position);
        if(node != null && !node.isLeaft()){
            node.setExpand(!node.isExpand());
            mVisibleNodes = ExpandHelper.filterVisibleNodes(mAllNodes);
            notifyDataSetChanged();
        }
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
