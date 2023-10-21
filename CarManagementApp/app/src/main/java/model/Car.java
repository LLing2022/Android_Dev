package model;

import androidx.annotation.NonNull;

public class Car {

    private int id;
    private String brand;
    private String status;
    private int price;

    public Car() {
    }

    public Car(int id, String brand, String status, int price) {
        this.id = id;
        this.brand = brand;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return brand;
    }
}
