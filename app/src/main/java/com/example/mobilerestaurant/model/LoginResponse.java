package com.example.mobilerestaurant.model;

public class LoginResponse {

    private int errCode;
    private String message;
    private User user;

    public LoginResponse(int errCode, String message, User user) {
        this.errCode = errCode;
        this.message = message;
        this.user = user;
    }

    public int isErrCode() {
        return errCode;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
