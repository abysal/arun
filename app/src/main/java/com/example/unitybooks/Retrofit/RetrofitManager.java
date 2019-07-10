package com.example.unitybooks.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
private static Retrofit retrofit;
public static final String BASE_URL="http:/10.0.2.2:3100/";

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
