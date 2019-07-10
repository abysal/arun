package com.example.unitybooks.Retrofit;

import com.example.unitybooks.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    RetrofitManager Intance = new RetrofitManager();

    private RetrofitManager() {
    }

    public RetrofitManager getInstance(){
        return Intance;
    }

    private static Retrofit retrofit;

public static final String BASE_URL="http:/10.0.2.2:3100/";
    public static final String IMAGE_URL="http:/10.0.2.2:3100/";


public static String token="";
public static Retrofit createinstance(){
    if (retrofit == null){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    return retrofit;
}
}
