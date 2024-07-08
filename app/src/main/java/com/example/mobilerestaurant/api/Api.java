package com.example.mobilerestaurant.api;

import com.example.mobilerestaurant.model.DefaultResponse;
import com.example.mobilerestaurant.model.LoginResponse;
import com.example.mobilerestaurant.model.ProductResponse;
import com.example.mobilerestaurant.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    // user
    @FormUrlEncoded
    @POST("/api/create-new-user")
    Call<DefaultResponse> createNewUser(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/user-login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("/api/get-all-users")
    Call<UserResponse> getUsers ();

    @FormUrlEncoded
    @PUT("/api/edit-user/{userId}")
        Call<LoginResponse> editUser(
            @Path("userId") String userId,
            @Field("name") String name
    );

    @DELETE("/api/delete-user/{userId}")
    Call<DefaultResponse> deleteUser(
            @Path("userId") String userId
    );

    //product
    @FormUrlEncoded
    @POST("/api/createProduct")
    Call<DefaultResponse> createNewProduct(
            @Field("name") String name,
            @Field("price") String price,
            @Field("unit") String unit
    );

    @GET("/api/get-all-products")
    Call<ProductResponse> getProducts ();

}
