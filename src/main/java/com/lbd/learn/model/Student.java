package com.lbd.learn.model;

import java.io.Serializable;
import java.util.Map;

public class Student implements Serializable{
    private static final long serialVersionUID = 3358099594343455083L;
    //学生id
    private int id;
    //学生姓名
    private String stuName;
    //学生年龄
    private int age;
    //学生性别
    private int gender;
    //学生住址
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                '}';
    }

    public Student() {

    }

    public Student(int id, String stuName, int age, int gender, String address) {
        this.id = id;
        this.stuName = stuName;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public Student(Map<String, Object> map) {
        this.id = (int)map.get("id");
        this.stuName = (String) map.get("stu_name");
        this.age = (int)map.get("age");
        this.gender = (int)map.get("gender");
        this.address = (String) map.get("address");
    }
}
