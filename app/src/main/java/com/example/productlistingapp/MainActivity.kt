package com.example.productlistingapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
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
    private lateinit var progressBar: ProgressBar // Declare ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar) // Initialize ProgressBar
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchProducts()
    }

    private fun fetchProducts() {
        progressBar.visibility = View.VISIBLE // Show loading indicator

        RetrofitInstance.api.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                progressBar.visibility = View.GONE // Hide loading indicator
                if (response.isSuccessful) {
                    response.body()?.let { productList ->
                        productAdapter = ProductAdapter(productList)
                        recyclerView.adapter = productAdapter
                    } ?: run {
                        Toast.makeText(this@MainActivity, "No products found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to retrieve products", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                progressBar.visibility = View.GONE // Hide loading indicator
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
