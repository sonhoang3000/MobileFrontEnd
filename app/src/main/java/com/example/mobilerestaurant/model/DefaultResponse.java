package com.example.mobilerestaurant.model;

public class DefaultResponse {

    private int errCode;
    private String message;


    public DefaultResponse(int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public int isErrCode() {
        return errCode;
    }

    public String getMessage() {
        return message;
    }

}
