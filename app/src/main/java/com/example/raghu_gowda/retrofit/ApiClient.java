package com.example.raghu_gowda.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String END_POINT = "https://api.meetup.com";
    public static Retrofit retrofit=null;

    public Retrofit getClient(){
        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

//Adding a new comment
        }
        return retrofit;
    }

}
