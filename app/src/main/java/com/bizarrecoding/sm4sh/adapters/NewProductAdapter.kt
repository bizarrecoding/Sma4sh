package com.bizarrecoding.sm4sh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizarrecoding.sm4sh.databinding.NewProductItemBinding
import com.bizarrecoding.sm4sh.database.Product

class NewProductAdapter(val onClickListener: OnClickListener):
    ListAdapter<Product,NewProductAdapter.NewProductViewHolder>(DiffCallBack) {

    companion object DiffCallBack: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.objectId == newItem.objectId
        }
    }

    class NewProductViewHolder(private var binding: NewProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product){
            binding.product = product
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product: Product) = clickListener(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewProductViewHolder {
        return NewProductViewHolder(NewProductItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(product)
        }
        holder.bind(product)
    }
}