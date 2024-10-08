package com.example.productlistingapp.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlistingapp.Model.Product
import com.example.productlistingapp.ProductDetailActivity
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
        val buttonAddToCart: Button = itemView.findViewById(R.id.buttonAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        Log.d("ProductAdapter", "Inflated item_product layout")
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.productName.text = product.name
        holder.productPrice.text =product.basePrice.toString()
        holder.productCategory.text = product.productCategory
        holder.productBrand.text = product.brand
        holder.productDescription.text = product.description
        holder.productInStock.text = if (product.inStock) "In Stock (${product.stock})" else "Out of Stock"

        Glide.with(holder.itemView.context)
            .load(product.featuredImage)
            .error(R.drawable.ic_launcher_background) // replace with your placeholder image
            .into(holder.productImage)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("PRODUCT_ID", product.id)
                putExtra("PRODUCT_NAME", product.name)
                putExtra("PRODUCT_PRICE", product.basePrice)
                putExtra("PRODUCT_CATEGORY", product.productCategory)
                putExtra("PRODUCT_BRAND", product.brand)
                putExtra("PRODUCT_DESCRIPTION", product.description)
                putExtra("PRODUCT_IN_STOCK", product.inStock)
                putExtra("PRODUCT_FEATURED_IMAGE", product.featuredImage)
            }
            context.startActivity(intent)
        }

        holder.buttonAddToCart.setOnClickListener {
            // Handle Add to Cart action
        }
    }
}
