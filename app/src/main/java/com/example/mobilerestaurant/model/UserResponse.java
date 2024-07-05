package com.example.mobilerestaurant.model;

import java.util.List;

public class UserResponse {

    private int errCode;
    private List<User> users;

    private String errMessage;

    public UserResponse(int errCode, List<User> users, String errMessage) {
        this.errCode = errCode;
        this.users = users;
        this.errMessage = errMessage;
    }

    public int getErrCode() {
        return errCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
