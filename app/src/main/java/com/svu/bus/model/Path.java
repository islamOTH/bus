package com.svu.bus.model;

import java.util.ArrayList;

public class Path {
    private String id;
    private String name;
    private double cost;
    private ArrayList<Maintenance> maintenances;

    public Path() {
        maintenances=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Path setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Path setName(String name) {
        this.name = name;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Path setCost(double cost) {
        this.cost = cost;
        return this;
    }
}
