package com.ederfaria.stockquotemanager.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static Retrofit getClientApi() {
        OkHttpClient okClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).build();

        Retrofit client = new Retrofit.Builder().baseUrl(getUrlServer()).client(okClient)
                .addConverterFactory(GsonConverterFactory.create()).build();

        return client;
    }

    public static String getUrlServer() {
        return "http://localhost:8080";
    }
}
