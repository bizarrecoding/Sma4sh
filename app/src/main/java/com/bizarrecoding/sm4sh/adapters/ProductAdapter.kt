package com.bizarrecoding.sm4sh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizarrecoding.sm4sh.models.Product
import com.bizarrecoding.sm4sh.databinding.ProductItemBinding

class ProductAdapter(private val onClickListener: OnClickListener):
    ListAdapter<Product,ProductAdapter.ProductViewHolder>(DiffCallBack) {

    companion object DiffCallBack: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.objectId == newItem.objectId
        }
    }

    class ProductViewHolder(private var binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product){
            binding.product = product
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product: Product) = clickListener(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(product)
        }
        holder.bind(product)
    }
}