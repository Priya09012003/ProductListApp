package com.example.productlistingapp.Api


import com.example.productlistingapp.Model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}
