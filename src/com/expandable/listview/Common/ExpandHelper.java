package com.expandable.listview.Common;

import com.expandable.listview.bean.Node;
import com.expandable.listview.bean.NodeId;
import com.expandable.listview.bean.NodeName;
import com.expandable.listview.bean.NodeParentId;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baiguang on 2015/1/19.
 * 将任意数据集合转换成Node集合的帮助类
 */
public class ExpandHelper {

    /**
     * 当传入的集合转换成Nodes集合，并按照层级排序
     * @param data
     * @param defaultExpendLevel
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static <T> List<Node> getSortedNodes(List<T> data, int defaultExpendLevel)
            throws IllegalAccessException {
        List<Node> sortedNodes = new ArrayList<Node>();
        List<Node> nodes = convertDataToNodes(data);
        List<Node> topLevelNodes = getTopLevelNodes(nodes);
        for (Node node : topLevelNodes){
            addNode(sortedNodes,node,defaultExpendLevel,1);
        }
        return sortedNodes;
    }

    /**
     * 过滤筛选出所有可见状态的Nodes
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> nodes){
        List<Node> visibleNodes = new ArrayList<Node>();
        for (Node node : nodes){
            // 如果当前节点为顶层节点，或 其父节点是展开状态，则当前节点可见
            if(node.isTopLevelNode() || node.getParent().isExpand()){
                visibleNodes.add(node);
            }
        }
        return nodes;
    }

    /**
     * 重置Node状态，将选中状态置为false，展开状态置为false
     * @param nodes
     * @return
     */
    public static List<Node> resetNodesStatus(List<Node> nodes){
        for (Node node : nodes){
            node.setSelected(false);
            if(node.getLevel() > 1){
                node.setExpand(false);
            }
        }
        return nodes;
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
                if(field.getAnnotation(NodeId.class) != null){
                    field.setAccessible(true);
                    id = field.getInt(t);
                }
                if(field.getAnnotation(NodeName.class) != null){
                    field.setAccessible(true);
                    name = (String) field.get(t);
                }
                if(field.getAnnotation(NodeParentId.class) != null){
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

    /**
     * 获取第一层节点集合
     * @param data
     * @return
     */
    private static List<Node> getTopLevelNodes(List<Node> data){
        List<Node> roots = new ArrayList<Node>();
        for (Node n : data){
            if(n.isTopLevelNode()){
                roots.add(n);
            }
        }
        return roots;
    }

    /**
     * 递归添加节点
     * @param nodes
     * @param node
     * @param defaultExpandLevel
     * @param currentLevel
     */
    private static void addNode(List<Node> nodes, Node node, int defaultExpandLevel, int currentLevel){
        nodes.add(node);
        if(defaultExpandLevel >= currentLevel){
            node.setExpand(true);
        }
        if(node.isLeaft()){
            return;
        }
        for (int i = 0; i < node.getChildren().size(); i ++){
            addNode(nodes,node.getChildren().get(i),defaultExpandLevel,currentLevel + 1);
        }
    }
}
