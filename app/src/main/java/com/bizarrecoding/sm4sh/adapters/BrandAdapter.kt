package com.bizarrecoding.sm4sh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizarrecoding.sm4sh.adapters.BrandAdapter.BrandViewHolder
import com.bizarrecoding.sm4sh.models.Brand
import com.bizarrecoding.sm4sh.databinding.BrandItemBinding
import kotlinx.android.synthetic.main.brand_item.view.*

class BrandAdapter (private val onClickListener: OnClickListener):
    ListAdapter<Brand, BrandViewHolder>(DiffCallBack) {

    var selectedIndex = 0

    companion object DiffCallBack: DiffUtil.ItemCallback<Brand>() {
        override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem.name == newItem.name
        }
    }
    inner class BrandViewHolder(private var binding: BrandItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(brand: Brand){
            binding.brand = brand
            binding.brandBtn.isChecked = selectedIndex==brand.hashCode()
            binding.executePendingBindings()
        }
    }
    class OnClickListener(val clickListener: (brand: Brand) -> Unit) {
        fun onClick(brand: Brand) = clickListener(brand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(BrandItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = getItem(position)
        holder.itemView.brandBtn.setOnClickListener {
            onClickListener.onClick(brand)
            selectedIndex = brand.hashCode()
            notifyDataSetChanged()
        }
        holder.bind(brand)
    }

}