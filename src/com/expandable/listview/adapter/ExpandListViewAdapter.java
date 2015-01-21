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
 * 可伸缩ListView适配器 抽象类
 *
 */
public abstract class ExpandListViewAdapter<T> extends BaseAdapter {

    protected Context mContext;

    protected List<Node> mVisibleNodes;

    protected LayoutInflater mInflater;

    protected List<Node> mAllNodes;

    /**
     * 定义点击Node节点的处理程序接口
     */
    public interface OnExpandNodeClickListener {
        void onClick(Node node, List<Node> visibleNodes, View view, int position);
    }

    /**
     * 定义接口类型变量
     */
    private OnExpandNodeClickListener onExpandNodeClickListener;

    /**
     * 定义接口变量 set 方法，方便子类自定义处理方法
     * @param listener
     */
    public void setOnExpandNodeClickListener(OnExpandNodeClickListener listener){
        this.onExpandNodeClickListener = listener;
    }

    /**
     * 适配器构造函数
     * @param listView ListView控件
     * @param context 程序上下文
     * @param data 数据集合
     * @param defaultExpandLevel 默认展开层级数
     * @throws IllegalAccessException
     */
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
                // 如果 listener不为null，说明用户自定义的处理程序，则调用之，让事件传播
                if(onExpandNodeClickListener != null){
                    onExpandNodeClickListener.onClick(mVisibleNodes.get(position),mVisibleNodes,view,position);
                }
            }
        });
    }

    /**
     * 更新当前节点展开或收缩状态
     * @param position
     */
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
        return mVisibleNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mVisibleNodes.get(position);
        convertView = getConvertView(node,position,convertView,parent);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);
}
