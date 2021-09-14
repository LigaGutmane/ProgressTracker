package com.example.progresstracker;

public class Employee {
    String id = "";
    String name = "";
    String berryType = "";
    String berryAmount = "";
    String date = "";

    public Employee(String id, String name, String berryType, String berryAmount, String date) {
        this.id = id;
        this.name = name;
        this.berryType = berryType;
        this.berryAmount = berryAmount;
        this.date = date;
    }

    public Employee() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBerryType() {
        return berryType;
    }

    public void setBerryType(String berryType) {
        this.berryType = berryType;
    }

    public String getBerryAmount() {
        return berryAmount;
    }

    public void setBerryAmount(String berryAmount) {
        this.berryAmount = berryAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
