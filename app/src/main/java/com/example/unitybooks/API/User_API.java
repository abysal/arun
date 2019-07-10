package com.example.unitybooks.API;

import com.example.unitybooks.Models.Token_Gen;
import com.example.unitybooks.Models.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface User_API {
    @FormUrlEncoded
    @POST("users/Login")
    Call<Token_Gen> login(@Field("Username") String username, @Field("Password") String pass);

    @POST("users/registeruser")
    Call<String> register(@Body Users users);
}
