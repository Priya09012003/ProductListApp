package com.example.productlistingapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productCategory: TextView
    private lateinit var productBrand: TextView
    private lateinit var productDescription: TextView
    private lateinit var productInStock: TextView
    private lateinit var productImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // Initialize views using findViewById
        productName = findViewById(R.id.textViewProductName)
        productPrice = findViewById(R.id.textViewProductPrice)
        productCategory = findViewById(R.id.textViewProductCategory)
        productBrand = findViewById(R.id.textViewBrand)
        productDescription = findViewById(R.id.textViewDescription)
        productInStock = findViewById(R.id.textViewInStock)
        productImage = findViewById(R.id.imageViewProduct)

        // Get data from intent
        val name = intent.getStringExtra("PRODUCT_NAME")
        val price = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)
        val category = intent.getStringExtra("PRODUCT_CATEGORY")
        val brand = intent.getStringExtra("PRODUCT_BRAND")
        val description = intent.getStringExtra("PRODUCT_DESCRIPTION")
        val inStock = intent.getBooleanExtra("PRODUCT_IN_STOCK", false)
        val featuredImage = intent.getStringExtra("PRODUCT_FEATURED_IMAGE")

        // Set data to views
        productName.text = name
        productPrice.text = "$$price"
        productCategory.text = category
        productBrand.text = brand
        productDescription.text = description
        productInStock.text = if (inStock) "In Stock" else "Out of Stock"

        // Load product image using Glide
        Glide.with(this)
            .load(featuredImage)
            .into(productImage)
    }
}
