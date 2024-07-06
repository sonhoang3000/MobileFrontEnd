package com.example.mobilerestaurant.model;

public class User {

    private String _id;
    private String email,name;

    public User(String _id, String email, String name) {
        this._id = _id;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
