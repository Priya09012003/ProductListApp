package com.example.productlistingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productlistingapp.Adapter.ProductAdapter
import com.example.productlistingapp.Instance.RetrofitInstance
import com.example.productlistingapp.Model.Product

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        RetrofitInstance.api.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    response.body()?.let { productList ->
                        productAdapter = ProductAdapter(productList)
                        recyclerView.adapter = productAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

            }
        })
    }
}
