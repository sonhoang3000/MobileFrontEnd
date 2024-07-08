package com.example.mobilerestaurant.model;

public class Product {

    private String _id,name;

    private String unit,price;

    public Product(String _id, String name, String unit, String price) {
        this._id = _id;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public String getNameProduct() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getPrice() {
        return price;
    }
}
