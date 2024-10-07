package com.example.productlistingapp.Model

import android.graphics.Camera

data class Product(
    val id: Int,
    val productCategory: String,
    val name: String,
    val brand: String,
    val description: String,
    val basePrice: Double,
    val inStock: Boolean,
    val stock: Int,
    val featuredImage: String,
    val thumbnailImage: String,
    val storageOptions: List<String>,
    val colorOptions: List<String>,
    val display: String,
    val CPU: String,
    val camera: Camera
)