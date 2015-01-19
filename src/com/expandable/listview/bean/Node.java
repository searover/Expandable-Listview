package com.expandable.listview.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baiguang on 2015/1/15.
 */
public class Node {
    /** 节点ID */
    private int id;
    /** 节点父ID */
    private int parentId;
    /** 节点名称 */
    private String name;
    /** 节点层级 */
    private int level;
    /** 是否可伸缩 */
    private boolean expand;
    /** 父节点 */
    private Node parent;
    /** 是否处于选中状态 */
    private boolean selected;
    /** 节点的孩子们 */
    private List<Node> children = new ArrayList<Node>();
    /** 当前节点附加的属性 */
    private Object additional;

    public Node(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getParentId() {return parentId;}
    public void setParentId(int parentId) {this.parentId = parentId;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}
    public boolean isExpand() {return expand;}
    public void setExpand(boolean expand) {this.expand = expand;}
    public Node getParent() {return parent;}
    public void setParent(Node parent) {this.parent = parent;}
    public boolean isSelected() {return selected;}
    public void setSelected(boolean selected) {this.selected = selected;}
    public List<Node> getChildren() {return children;}
    public void setChildren(List<Node> children) {this.children = children;}
    public Object getAdditional() {return additional;}
    public void setAdditional(Object additional) {this.additional = additional;}
}
