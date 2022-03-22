package com.example.liquorland.Networking;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerGenerator {
    private static   ServerGenerator mInstance;
    public static  final  String Baseurl= URLs.MAIN_URL;
    private Retrofit retrofit;

    public ServerGenerator() {
        Gson gson= new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();

        retrofit= new Retrofit.Builder()
                .baseUrl(Baseurl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
    public static  synchronized ServerGenerator getInstance(){
        if(mInstance ==null){

            return new ServerGenerator();

        }
        else{

            return mInstance;
        }
    }
    public  Endpoints getApiConnetor(){
        return retrofit.create(Endpoints.class);
    }

}
