package com.example.unitybooks.API;

import com.example.unitybooks.Models.Books;
import com.example.unitybooks.Models.LoginResponse;
import com.example.unitybooks.Models.LoginSignupResponse;
import com.example.unitybooks.Models.Token_Gen;
import com.example.unitybooks.Models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface User_API {
    @FormUrlEncoded
    @POST("users/Login")
    Call<LoginSignupResponse> checkUser(@Field("Username") String username, @Field("Password") String pass);

    @POST("users/registeruser")
    Call<LoginResponse> register(@Body Users users);

    @POST("books/addbook")
    Call<Void> addNewBook(@Body Books item);

    @GET("books/showbook")
    Call<List<Books>>getAllBook(
            @Header("Authorization") String token
    );


}
