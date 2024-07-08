package com.example.mobilerestaurant.model;

import java.util.List;

public class ProductResponse {

    private int errCode;
    private List<Product> products;
    private String errMessage;

    public ProductResponse(int errCode, List<Product> products, String errMessage) {
        this.errCode = errCode;
        this.products = products;
        this.errMessage = errMessage;
    }

    public int getErrCode() {
        return errCode;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
