package com.example.layout;

import java.util.Date;

public class Item {
    private int id;
    private String name;
    private String dob;
    private int gender;
    private String position;
    private float salary;

    public Item(int id, String name, String dob, int gender, String position, float salary) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.position = position;
        this.salary = salary;
    }

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
