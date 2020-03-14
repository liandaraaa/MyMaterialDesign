package com.android.mymaterialdesign.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mymaterialdesign.R
import com.android.mymaterialdesign.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(
    val products: MutableList<Product> = mutableListOf(),
    val listener:OnProductClickListener? = null
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutView =LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(layoutView)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }


    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Product) {
            with(itemView){
                Glide.with(context).load(data.imageUrl).into(productImage)
                tvTitle.text = data.title
                tvDescription.text = data.description

                setOnClickListener {
                    listener?.onProductClicked(itemView, data)
                }
            }
        }
    }


    interface OnProductClickListener{
        fun onProductClicked(view:View, product:Product)
    }
}