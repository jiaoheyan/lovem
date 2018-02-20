package com.example.lovej.jlovem.jishiben;

/**
 * Created by Administrator on 2018/2/20 0020.
 */

public class Student {
    private String name;
    private String sex;
    public Student(){
    }
    public Student(String name){
        this.name = name;
    }
    public Student(String name, String sex) {
        super();
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

}
