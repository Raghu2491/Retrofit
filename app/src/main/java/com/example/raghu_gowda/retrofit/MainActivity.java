package com.example.raghu_gowda.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public MeetupApi api;

    public Category category;

    public static final String API_KEY="2f43365b380316d3a234157ae5e31";

    public String group_urlname="ny-tech";

    public List<Category.ResultsBean> mResult;

    public static ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiClient=new ApiClient();





//Using Retrofit and RxJava////////////////////////////////////////////////////////
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.meetup.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();







        api=apiClient.getClient().create(MeetupApi.class);
        Observable<Category>observable=api.getCategory(API_KEY,group_urlname);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Category>() {
                    @Override
                    public void call(Category category) {
                        mResult=category.getResults();
                        Log.d("--->",mResult.get(mResult.size()-1).getName());
                    }
                });
//
////////////////////////////////////////////////////////////////////////////////////

//Using Only Retrofit///////////////////////////////////////////////////////////////
//        api=retrofit.create(MeetupApi.class);
//
//        Call<Category> categoryCall=api.getData(API_KEY,group_urlname);
//
//        categoryCall.enqueue(new Callback<ParseCategory>() {
//            @Override
//            public void onResponse(Call<ParseCategory> call, Response<ParseCategory> response) {
//                parseCategory=response.body();0000011000
//                System.out.println("-------------------->"+parseCategory.getResults().get(0).getName());
//            }
//
//            @Override
//            public void onFailure(Call<ParseCategory> call, Throwable t) {
//
//            }
//        });
////////////////////////////////////////////////////////////////////////////////////


    }
}
