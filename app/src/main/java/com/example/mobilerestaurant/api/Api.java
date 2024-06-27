package com.example.mobilerestaurant.api;

import com.example.mobilerestaurant.model.DefaultResponse;
import com.example.mobilerestaurant.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
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

//    @GET("/api/get-all-users")
//    Call<UserResponse> getAllUsers (
//            @Field("userId") String userId
//    );


}
