package com.expandable.listview.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baiguang on 2015/1/15.
 */
public class Node {
    private int id;
    private int parentId;
    private String name;
    private int level;
    private boolean expand;
    private Node parent;
    private boolean selected;
    private List<Node> children = new ArrayList<Node>();
    private Object what;

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
    public Object getWhat() {return what;}
    public void setWhat(Object what) {this.what = what;}
}
