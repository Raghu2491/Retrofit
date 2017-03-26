package com.example.raghu_gowda.retrofit;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MeetupApi {
//what u did not change get(parameters)....comme
    @GET("/2/categories?&sign=true")
    Observable<Category> getCategory(@Query("key") String api_key, @Query("group_urlname") String url_name);



    @GET("/maps/api/geocode/json")
    Call<Location> getCategory2(@Query("latlng") String path, @Query("key") String key);
}
