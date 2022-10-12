package com.example.liquorland.Networking;

import android.text.Editable;

import com.example.liquorland.Networking.POJO.CartResponse;
import com.example.liquorland.Networking.POJO.CategoryResponse;
import com.example.liquorland.Networking.POJO.ProductRespose;
import com.example.liquorland.Networking.POJO.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoints {

    @GET("/api/products")
    Call<List<ProductRespose>> getDrinks();

    @GET("/products/details/{id}")
    Call<ProductRespose> getDrinksDetails(@Path("id") int id);

    @GET("/api/product_categories")
    Call<List<CategoryResponse>> getCategory();

    @GET("/api/products/category/{pk}")
    Call<List<ProductRespose>> getDrinksInCategory(@Path("pk") int pk);

    @FormUrlEncoded
    @POST("api/auth/login/")
    Call<UserResponse> loginUser(@Field("username") String username,
                                 @Field("password") Editable password);

    @FormUrlEncoded
    @POST("/api/auth/register/")
    Call<UserResponse> registerUser(@Field("name") String name,
                                    @Field("number") Editable number,
                                     @Field("email") String email,
                                    @Field("password") String password);

    @Headers("Accept: application/json")
    @GET("api/cart")
    Call<CartResponse> getproducts(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @POST("api/add/to/cart")
    Call<CartResponse>  addtocart(@Header("Authorization") String token, @Body() int product_id);

    @Headers("Accept: application/json")
    @GET("api/remove/from/cart/{cart_product_id}")
    Call<CartResponse> removefromcart(@Path("cart_product_id") int id,@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @POST("api/checkout/")
    Call<CartResponse> checkout(@Header("Authorization") String token);

}
