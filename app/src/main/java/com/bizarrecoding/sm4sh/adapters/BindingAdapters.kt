package com.bizarrecoding.sm4sh.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizarrecoding.sm4sh.database.Product
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, listData: List<Product>?) {
    val adapter = recyclerView.adapter as ProductAdapter
    adapter.submitList(listData)
}

@BindingAdapter("listNewData")
fun bindNewRecyclerView(recyclerView: RecyclerView, listData: List<Product>?) {
    val adapter = recyclerView.adapter as NewProductAdapter
    adapter.submitList(listData)
}
@BindingAdapter("listPopularData")
fun bindPopularRecyclerView(recyclerView: RecyclerView, listData: List<Product>?) {
    val adapter = recyclerView.adapter as PopularProductAdapter
    adapter.submitList(listData)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(android.R.color.transparent)
                .error(android.R.drawable.stat_notify_error))
            .into(imgView)
    }
}