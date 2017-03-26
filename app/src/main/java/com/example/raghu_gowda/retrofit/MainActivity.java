package com.example.raghu_gowda.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public MeetupApi api;

    public Category category;
    public Gson gson;

    Location location;
    public static final String API_KEY="AIzaSyBGZxSSFFEI5x_LC4F4DMJ_oPe3bJ9Tkms";

    public String latlng="30.0444,31.2357";
    public String city;
    public String country;
    public List<Category.ResultsBean> mResult;

    public static ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiClient=new ApiClient();
        api=apiClient.getClient().create(MeetupApi.class);




/////
//Using Retrofit and RxJava////////////////////////////////////////////////////////



//        Observable<Category>observable=api.getCategory(API_KEY,group_urlname);
//        observable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(category1 -> category1.getResults().get(0).getName());


                //
////////////////////////////////////////////////////////////////////////////////////

//Using Only Retrofit///////////////////////////////////////////////////////////////

        Call<Location> categoryCall=api.getCategory2(latlng,API_KEY);

        categoryCall.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                gson=new Gson();
                List<Location.ResultsBean.AddressComponentsBean> address_components=response.body().getResults().get(0).getAddress_components();
                //System.out.println("------>"+response.body().getResults().get(0).getAddress_components());
                System.out.println("------>"+address_components);

                for (Location.ResultsBean.AddressComponentsBean comp:address_components ) {
                        List<String> types = comp.getTypes();

                    if(types.get(0).equals("locality")) {

                        System.out.println("===========================================  " + comp.getLong_name());
                    }
                    if(types.get(0).equals("country")) {

                        System.out.println("===========================================  " + comp.getLong_name());
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {

            }
        });
////////////////////////////////////////////////////////////////////////////////////


    }
    }