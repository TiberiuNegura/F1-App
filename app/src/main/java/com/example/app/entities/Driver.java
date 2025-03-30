package com.example.app.entities;

public class Driver {

    String name;

    int number;

    String imagePath;

    public Driver(String name, int number, String imagePath) {
        this.name = name;
        this.number = number;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
