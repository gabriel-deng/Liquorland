package com.example.liquorland.Networking;

import com.example.liquorland.Networking.POJO.ProductRequests;
import com.example.liquorland.Networking.POJO.ProductRespose;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Endpoints {

    @GET("/drinks")
    Call<List<ProductRespose>> getDrinks();

    @GET("/drinks/{id}")
    Call<ProductRespose> getDrinksDetails(@Path("id") int id);

    @GET("/drinks/create")
    Call<ProductRespose> CreateNewDrink(ProductRequests requests);

}
