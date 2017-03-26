package com.example.raghu_gowda.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String END_POINT = "https://maps.googleapis.com/";
    public static Retrofit retrofit=null;

    public Retrofit getClient(){
        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
