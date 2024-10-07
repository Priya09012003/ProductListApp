package com.example.productlistingapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlistingapp.Model.Product
import com.example.productlistingapp.R

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.textViewProductName)
        val productPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
        val productCategory: TextView = itemView.findViewById(R.id.textViewProductCategory)
        val productBrand: TextView = itemView.findViewById(R.id.textViewBrand)
        val productDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val productInStock: TextView = itemView.findViewById(R.id.textViewInStock)
        val productImage: ImageView = itemView.findViewById(R.id.imageViewProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        Log.d("ProductAdapter", "Inflated item_product layout")
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]


        holder.productName.text = product.name
        holder.productPrice.text = "$${product.basePrice}"
        holder.productCategory.text = product.productCategory
        holder.productBrand.text = product.brand
        holder.productDescription.text = product.description
        holder.productInStock.text = if (product.inStock) "In Stock (${product.stock})" else "Out of Stock"

        Glide.with(holder.itemView.context)
        Glide.with(holder.itemView.context)
            .load(product.featuredImage)
            .into(holder.productImage)


    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
