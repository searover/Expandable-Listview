package com.expandable.listview.bean;

import java.util.Date;

/**
 * Created by Baiguang on 2015/1/24.
 * 公司职员类
 */
public class Staff {

    /** 员工编号 */
    @NodeId
    private int employeeId;

    /** 员工姓名 */
    @NodeName
    private String employeeName;

    /** 上级编号 */
    @NodeParentId
    private int superiorId;


    private String position;

    /** 年龄 */
    private int age;

    /** 性别 */
    private boolean gender;

    /** 生日 */
    private Date birthday;

    public Staff(){}

    public Staff(int employeeId, String employeeName, int superiorId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.superiorId = superiorId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(int superiorId) {
        this.superiorId = superiorId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
