package com.bizarrecoding.sm4sh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bizarrecoding.sm4sh.adapters.UniverseFilterAdapter.FilterHolder
import com.bizarrecoding.sm4sh.databinding.BrandFilterItemBinding
import com.bizarrecoding.sm4sh.models.Brand

class UniverseFilterAdapter(private val onClickListener: OnClickListener):
    ListAdapter<Brand, FilterHolder>(DiffCallBack) {
    var selectedBrand = 0

    companion object DiffCallBack: DiffUtil.ItemCallback<Brand>() {
        override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class FilterHolder(private var binding: BrandFilterItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(universe: Brand){
            binding.brand = universe
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (universe: Brand) -> Unit) {
        fun onClick(universe: Brand) = clickListener(universe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        return FilterHolder(BrandFilterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        val universe = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(universe)
        }
        holder.bind(universe)
    }
}