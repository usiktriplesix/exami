package com.example.myapplication;

import java.io.Serializable;

public class Test implements Serializable {

    private int id;
    private String name;
    private String description;
    private String price;
    private String category;
    private String time_result;

    public Test(int id, String name, String description, String price, String category, String time_result, String preparation, String bio) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.time_result = time_result;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getTime_result() {
        return time_result;
    }

}