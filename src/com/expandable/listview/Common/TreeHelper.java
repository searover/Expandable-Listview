package com.expandable.listview.Common;

import com.expandable.listview.bean.Node;
import com.expandable.listview.bean.TreeNodeId;
import com.expandable.listview.bean.TreeNodeName;
import com.expandable.listview.bean.TreeNodeParentId;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baiguang on 2015/1/19.
 * 将任意数据集合转换成Node集合的帮助类
 */
public class TreeHelper {
    public static <T> List<Node> getSortedNodes(List<T> data, int defaultExpendLevel){
        return null;
    }

    public static List<Node> filterVisibleNodes(List<Node> nodes){
        return null;
    }

    public static List<Node> resetNodesStatus(List<Node> nodes){
        return null;
    }

    /**
     * 将数据集合转换成 Node 集合
     * @param data
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    private static <T> List<Node> convertDataToNodes(List<T> data) throws IllegalAccessException {
        List<Node> nodes = new ArrayList<Node>();
        // 循环遍历数据集合中每一个元素
        for (T t : data){
            // 设置Node主要字段的初始变量
            int id = -1;
            int parentId = -1;
            String name = null;
            // 通过反射的方法取出元素中饮食annotation的字段，并赋值于初始变量
            Class<? extends Object> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields){
                if(field.getAnnotation(TreeNodeId.class) != null){
                    field.setAccessible(true);
                    id = field.getInt(t);
                }
                if(field.getAnnotation(TreeNodeName.class) != null){
                    field.setAccessible(true);
                    name = (String) field.get(t);
                }
                if(field.getAnnotation(TreeNodeParentId.class) != null){
                    field.setAccessible(true);
                    parentId = field.getInt(t);
                }
                if(id != -1 && parentId != -1 && name != null){
                    break;
                }
            }
            // 新建Node对象，并将其加入到Node集合
            nodes.add(new Node(id,parentId,name));
        }
        // 遍历Node集合，设置全集中元素间的父子关系
        for (int i = 0; i < nodes.size(); i ++){
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j ++){
                Node m = nodes.get(j);
                if(m.getParentId() == n.getId()){
                    n.getChildren().add(m);
                    m.setParent(n);
                }
                if(n.getParentId() == m.getId()){
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }
        return nodes;
    }
}
